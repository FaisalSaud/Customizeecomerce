/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.SupLib.eclipse/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package SupLib.Dx.dex.cf;

import SupLib.Dx.cf.code.ConcreteMethod;
import SupLib.Dx.cf.code.Ropper;
import SupLib.Dx.cf.direct.DirectClassFile;
import SupLib.Dx.cf.direct.StdAttributeFactory;
import SupLib.Dx.cf.iface.Field;
import SupLib.Dx.cf.iface.FieldList;
import SupLib.Dx.cf.iface.Method;
import SupLib.Dx.cf.iface.MethodList;
import SupLib.Dx.dex.DexOptions;
import SupLib.Dx.dex.code.DalvCode;
import SupLib.Dx.dex.code.PositionList;
import SupLib.Dx.dex.code.RopTranslator;
import SupLib.Dx.dex.file.ClassDefItem;
import SupLib.Dx.dex.file.EncodedField;
import SupLib.Dx.dex.file.EncodedMethod;
import SupLib.Dx.rop.annotation.Annotations;
import SupLib.Dx.rop.annotation.AnnotationsList;
import SupLib.Dx.rop.code.AccessFlags;
import SupLib.Dx.rop.code.LocalVariableExtractor;
import SupLib.Dx.rop.code.LocalVariableInfo;
import SupLib.Dx.rop.code.RopMethod;
import SupLib.Dx.rop.code.DexTranslationAdvice;
import SupLib.Dx.rop.code.TranslationAdvice;
import SupLib.Dx.rop.cst.Constant;
import SupLib.Dx.rop.cst.CstBoolean;
import SupLib.Dx.rop.cst.CstByte;
import SupLib.Dx.rop.cst.CstChar;
import SupLib.Dx.rop.cst.CstFieldRef;
import SupLib.Dx.rop.cst.CstInteger;
import SupLib.Dx.rop.cst.CstMethodRef;
import SupLib.Dx.rop.cst.CstShort;
import SupLib.Dx.rop.cst.CstString;
import SupLib.Dx.rop.cst.CstType;
import SupLib.Dx.rop.cst.TypedConstant;
import SupLib.Dx.rop.type.Type;
import SupLib.Dx.rop.type.TypeList;
import SupLib.Dx.ssa.Optimizer;
import SupLib.Dx.util.ExceptionWithContext;

/**
 * Static method that turns {@code byte[]}s containing Java
 * classfiles into {@link ClassDefItem} instances.
 */
public class CfTranslator {
    /** set to {@code true} to enable development-time debugging code */
    private static final boolean DEBUG = false;

    /**
     * This class is uninstantiable.
     */
    private CfTranslator() {
        // This space intentionally left blank.
    }

    /**
     * Takes a {@code byte[]}, interprets it as a Java classfile, and
     * translates it into a {@link ClassDefItem}.
     *
     * @param filePath {@code non-null;} the file path for the class,
     * excluding any base directory specification
     * @param bytes {@code non-null;} contents of the file
     * @param cfOptions options for class translation
     * @param dexOptions options for dex output
     * @return {@code non-null;} the translated class
     */
    public static ClassDefItem translate(String filePath, byte[] bytes,
            CfOptions cfOptions, DexOptions dexOptions) {
        try {
            return translate0(filePath, bytes, cfOptions, dexOptions);
        } catch (RuntimeException ex) {
            String msg = "...while processing " + filePath;
            throw ExceptionWithContext.withContext(ex, msg);
        }
    }

    /**
     * Performs the main act of translation. This method is separated
     * from {@link #translate} just to keep things a bit simpler in
     * terms of exception handling.
     *
     * @param filePath {@code non-null;} the file path for the class,
     * excluding any base directory specification
     * @param bytes {@code non-null;} contents of the file
     * @param cfOptions options for class translation
     * @param dexOptions options for dex output
     * @return {@code non-null;} the translated class
     */
    private static ClassDefItem translate0(String filePath, byte[] bytes,
            CfOptions cfOptions, DexOptions dexOptions) {
        DirectClassFile cf =
            new DirectClassFile(bytes, filePath, cfOptions.strictNameCheck);

        cf.setAttributeFactory(StdAttributeFactory.THE_ONE);
        cf.getMagic();

        OptimizerOptions.loadOptimizeLists(cfOptions.optimizeListFile,
                cfOptions.dontOptimizeListFile);

        // Build up a class to output.

        CstType thisClass = cf.getThisClass();
        int classAccessFlags = cf.getAccessFlags() & ~AccessFlags.ACC_SUPER;
        CstString sourceFile = (cfOptions.positionInfo == PositionList.NONE) ? null :
            cf.getSourceFile();
        ClassDefItem out =
            new ClassDefItem(thisClass, classAccessFlags,
                    cf.getSuperclass(), cf.getInterfaces(), sourceFile);

        Annotations classAnnotations =
            AttributeTranslator.getClassAnnotations(cf, cfOptions);
        if (classAnnotations.size() != 0) {
            out.setClassAnnotations(classAnnotations);
        }

        processFields(cf, out);
        processMethods(cf, cfOptions, dexOptions, out);

        return out;
    }

    /**
     * Processes the fields of the given class.
     *
     * @param cf {@code non-null;} class being translated
     * @param out {@code non-null;} output class
     */
    private static void processFields(DirectClassFile cf, ClassDefItem out) {
        CstType thisClass = cf.getThisClass();
        FieldList fields = cf.getFields();
        int sz = fields.size();

        for (int i = 0; i < sz; i++) {
            Field one = fields.get(i);
            try {
                CstFieldRef field = new CstFieldRef(thisClass, one.getNat());
                int accessFlags = one.getAccessFlags();

                if (AccessFlags.isStatic(accessFlags)) {
                    TypedConstant constVal = one.getConstantValue();
                    EncodedField fi = new EncodedField(field, accessFlags);
                    if (constVal != null) {
                        constVal = coerceConstant(constVal, field.getType());
                    }
                    out.addStaticField(fi, constVal);
                } else {
                    EncodedField fi = new EncodedField(field, accessFlags);
                    out.addInstanceField(fi);
                }

                Annotations annotations =
                    AttributeTranslator.getAnnotations(one.getAttributes());
                if (annotations.size() != 0) {
                    out.addFieldAnnotations(field, annotations);
                }
            } catch (RuntimeException ex) {
                String msg = "...while processing " + one.getName().toHuman() +
                    " " + one.getDescriptor().toHuman();
                throw ExceptionWithContext.withContext(ex, msg);
            }
        }
    }

    /**
     * Helper for {@link #processFields}, which translates constants into
     * more specific types if necessary.
     *
     * @param constant {@code non-null;} the constant in question
     * @param type {@code non-null;} the desired type
     */
    private static TypedConstant coerceConstant(TypedConstant constant,
            Type type) {
        Type constantType = constant.getType();

        if (constantType.equals(type)) {
            return constant;
        }

        switch (type.getBasicType()) {
            case Type.BT_BOOLEAN: {
                return CstBoolean.make(((CstInteger) constant).getValue());
            }
            case Type.BT_BYTE: {
                return CstByte.make(((CstInteger) constant).getValue());
            }
            case Type.BT_CHAR: {
                return CstChar.make(((CstInteger) constant).getValue());
            }
            case Type.BT_SHORT: {
                return CstShort.make(((CstInteger) constant).getValue());
            }
            default: {
                throw new UnsupportedOperationException("can't coerce " +
                        constant + " to " + type);
            }
        }
    }

    /**
     * Processes the methods of the given class.
     *
     * @param cf {@code non-null;} class being translated
     * @param cfOptions {@code non-null;} options for class translation
     * @param dexOptions {@code non-null;} options for dex output
     * @param out {@code non-null;} output class
     */
    private static void processMethods(DirectClassFile cf, CfOptions cfOptions,
            DexOptions dexOptions, ClassDefItem out) {
        CstType thisClass = cf.getThisClass();
        MethodList methods = cf.getMethods();
        int sz = methods.size();

        for (int i = 0; i < sz; i++) {
            Method one = methods.get(i);
            try {
                CstMethodRef meth = new CstMethodRef(thisClass, one.getNat());
                int accessFlags = one.getAccessFlags();
                boolean isStatic = AccessFlags.isStatic(accessFlags);
                boolean isPrivate = AccessFlags.isPrivate(accessFlags);
                boolean isNative = AccessFlags.isNative(accessFlags);
                boolean isAbstract = AccessFlags.isAbstract(accessFlags);
                boolean isConstructor = meth.isInstanceInit() ||
                    meth.isClassInit();
                DalvCode code;

                if (isNative || isAbstract) {
                    // There's no code for native or abstract methods.
                    code = null;
                } else {
                    ConcreteMethod concrete =
                        new ConcreteMethod(one, cf,
                                (cfOptions.positionInfo != PositionList.NONE),
                                cfOptions.localInfo);

                    TranslationAdvice advice;

                    advice = DexTranslationAdvice.THE_ONE;

                    RopMethod rmeth = Ropper.convert(concrete, advice);
                    RopMethod nonOptRmeth = null;
                    int paramSize;

                    paramSize = meth.getParameterWordCount(isStatic);

                    String canonicalName
                            = thisClass.getClassType().getDescriptor()
                                + "." + one.getName().getString();

                    if (cfOptions.optimize &&
                            OptimizerOptions.shouldOptimize(canonicalName)) {
                        if (DEBUG) {
                            System.err.println("Optimizing " + canonicalName);
                        }

                        nonOptRmeth = rmeth;
                        rmeth = Optimizer.optimize(rmeth,
                                paramSize, isStatic, cfOptions.localInfo, advice);

                        if (DEBUG) {
                            OptimizerOptions.compareOptimizerStep(nonOptRmeth,
                                    paramSize, isStatic, cfOptions, advice, rmeth);
                        }

                        if (cfOptions.statistics) {
                            CodeStatistics.updateRopStatistics(
                                    nonOptRmeth, rmeth);
                        }
                    }

                    LocalVariableInfo locals = null;

                    if (cfOptions.localInfo) {
                        locals = LocalVariableExtractor.extract(rmeth);
                    }

                    code = RopTranslator.translate(rmeth, cfOptions.positionInfo,
                            locals, paramSize, dexOptions);

                    if (cfOptions.statistics && nonOptRmeth != null) {
                        updateDexStatistics(cfOptions, dexOptions, rmeth, nonOptRmeth, locals,
                                paramSize, concrete.getCode().size());
                    }
                }

                // Preserve the synchronized flag as its "declared" variant...
                if (AccessFlags.isSynchronized(accessFlags)) {
                    accessFlags |= AccessFlags.ACC_DECLARED_SYNCHRONIZED;

                    /*
                     * ...but only native methods are actually allowed to be
                     * synchronized.
                     */
                    if (!isNative) {
                        accessFlags &= ~AccessFlags.ACC_SYNCHRONIZED;
                    }
                }

                if (isConstructor) {
                    accessFlags |= AccessFlags.ACC_CONSTRUCTOR;
                }

                TypeList exceptions = AttributeTranslator.getExceptions(one);
                EncodedMethod mi =
                    new EncodedMethod(meth, accessFlags, code, exceptions);

                if (meth.isInstanceInit() || meth.isClassInit() ||
                    isStatic || isPrivate) {
                    out.addDirectMethod(mi);
                } else {
                    out.addVirtualMethod(mi);
                }

                Annotations annotations =
                    AttributeTranslator.getMethodAnnotations(one);
                if (annotations.size() != 0) {
                    out.addMethodAnnotations(meth, annotations);
                }

                AnnotationsList list =
                    AttributeTranslator.getParameterAnnotations(one);
                if (list.size() != 0) {
                    out.addParameterAnnotations(meth, list);
                }
            } catch (RuntimeException ex) {
                String msg = "...while processing " + one.getName().toHuman() +
                    " " + one.getDescriptor().toHuman();
                throw ExceptionWithContext.withContext(ex, msg);
            }
        }
    }

    /**
     * Helper that updates the dex statistics.
     */
    private static void updateDexStatistics(CfOptions cfOptions, DexOptions dexOptions,
            RopMethod optRmeth, RopMethod nonOptRmeth,
            LocalVariableInfo locals, int paramSize, int originalByteCount) {
        /*
         * Run rop->dex again on optimized vs. non-optimized method to
         * collect statistics. We have to totally convert both ways,
         * since converting the "real" method getting added to the
         * file would corrupt it (by messing with its constant pool
         * indices).
         */

        DalvCode optCode = RopTranslator.translate(optRmeth,
                cfOptions.positionInfo, locals, paramSize, dexOptions);
        DalvCode nonOptCode = RopTranslator.translate(nonOptRmeth,
                cfOptions.positionInfo, locals, paramSize, dexOptions);

        /*
         * Fake out the indices, so code.getInsns() can work well enough
         * for the current purpose.
         */

        DalvCode.AssignIndicesCallback callback =
            new DalvCode.AssignIndicesCallback() {
                public int getIndex(Constant cst) {
                    // Everything is at index 0!
                    return 0;
                }
            };

        optCode.assignIndices(callback);
        nonOptCode.assignIndices(callback);

        CodeStatistics.updateDexStatistics(nonOptCode, optCode);
        CodeStatistics.updateOriginalByteCount(originalByteCount);
    }
}
