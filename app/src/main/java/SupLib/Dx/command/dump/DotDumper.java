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

package SupLib.Dx.command.dump;

import SupLib.Dx.cf.code.ConcreteMethod;
import SupLib.Dx.cf.code.Ropper;
import SupLib.Dx.cf.direct.DirectClassFile;
import SupLib.Dx.cf.direct.StdAttributeFactory;
import SupLib.Dx.cf.iface.Member;
import SupLib.Dx.cf.iface.Method;
import SupLib.Dx.cf.iface.ParseObserver;
import SupLib.Dx.rop.code.BasicBlock;
import SupLib.Dx.rop.code.BasicBlockList;
import SupLib.Dx.rop.code.RopMethod;
import SupLib.Dx.rop.code.DexTranslationAdvice;
import SupLib.Dx.rop.code.TranslationAdvice;
import SupLib.Dx.rop.code.AccessFlags;
import SupLib.Dx.ssa.Optimizer;
import SupLib.Dx.util.ByteArray;
import SupLib.Dx.util.Hex;
import SupLib.Dx.util.IntList;

/**
 * Dumps the pred/succ graph of methods into a format compatible
 * with the popular graph utility "dot".
 */
public class DotDumper implements ParseObserver {
    private DirectClassFile classFile;

    private final byte[] bytes;
    private final String filePath;
    private final boolean strictParse;
    private final boolean optimize;
    private final Args args;

    static void dump(byte[] bytes, String filePath, Args args) {
        new DotDumper(bytes, filePath, args).run();
    }

    DotDumper(byte[] bytes, String filePath, Args args) {
        this.bytes = bytes;
        this.filePath = filePath;
        this.strictParse = args.strictParse;
        this.optimize = args.optimize;
        this.args = args;
    }

    private void run() {
        ByteArray ba = new ByteArray(bytes);

        /*
         * First, parse the file completely, so we can safely refer to
         * attributes, etc.
         */
        classFile = new DirectClassFile(ba, filePath, strictParse);
        classFile.setAttributeFactory(StdAttributeFactory.THE_ONE);
        classFile.getMagic(); // Force parsing to happen.

        // Next, reparse it and observe the process.
        DirectClassFile liveCf =
            new DirectClassFile(ba, filePath, strictParse);
        liveCf.setAttributeFactory(StdAttributeFactory.THE_ONE);
        liveCf.setObserver(this);
        liveCf.getMagic(); // Force parsing to happen.
    }

    /**
     * @param name method name
     * @return true if this method should be dumped
     */
    protected boolean shouldDumpMethod(String name) {
        return args.method == null || args.method.equals(name);
    }

    public void changeIndent(int indentDelta) {
        // This space intentionally left blank.
    }

    public void parsed(ByteArray bytes, int offset, int len, String human) {
        // This space intentionally left blank.
    }

    /** {@inheritDoc} */
    public void startParsingMember(ByteArray bytes, int offset, String name,
                                   String descriptor) {
        // This space intentionally left blank.
    }

    public void endParsingMember(ByteArray bytes, int offset, String name,
                                 String descriptor, Member member) {
        if (!(member instanceof Method)) {
            return;
        }

        if (!shouldDumpMethod(name)) {
            return;
        }

        ConcreteMethod meth = new ConcreteMethod((Method) member, classFile,
                                                 true, true);

        TranslationAdvice advice = DexTranslationAdvice.THE_ONE;
        RopMethod rmeth =
            Ropper.convert(meth, advice);

        if (optimize) {
            boolean isStatic = AccessFlags.isStatic(meth.getAccessFlags());
            rmeth = Optimizer.optimize(rmeth,
                    BaseDumper.computeParamWidth(meth, isStatic), isStatic,
                    true, advice);
        }

        System.out.println("digraph "  + name + "{");

        System.out.println("\tfirst -> n"
                + Hex.u2(rmeth.getFirstLabel()) + ";");

        BasicBlockList blocks = rmeth.getBlocks();

        int sz = blocks.size();
        for (int i = 0; i < sz; i++) {
            BasicBlock bb = blocks.get(i);
            int label = bb.getLabel();
            IntList successors = bb.getSuccessors();

            if (successors.size() == 0) {
                System.out.println("\tn" + Hex.u2(label) + " -> returns;");
            } else if (successors.size() == 1) {
                System.out.println("\tn" + Hex.u2(label) + " -> n"
                        + Hex.u2(successors.get(0)) + ";");
            } else {
                System.out.print("\tn" + Hex.u2(label) + " -> {");
                for (int j = 0; j < successors.size(); j++ ) {
                    int successor = successors.get(j);

                    if (successor != bb.getPrimarySuccessor()) {
                        System.out.print(" n" + Hex.u2(successor) + " ");
                    }

                }
                System.out.println("};");

                System.out.println("\tn" + Hex.u2(label) + " -> n"
                        + Hex.u2(bb.getPrimarySuccessor())
                        + " [label=\"primary\"];");


            }
        }

        System.out.println("}");
    }
}
