/*******************************************************************************
 * Copyright (c) 2000, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.SupLib.eclipse/legal/epl-v10.html
 *
 * This is an implementation of an early-draft specification developed under the Java
 * Community Process (JCP) and is made available for testing and evaluation purposes
 * only. The code is not compatible with any specification of the JCP.
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Stephan Herrmann - Contribution for
 *								bug 383368 - [compiler][null] syntactic null analysis for field references
 *								Bug 392238 - [1.8][compiler][null] Detect semantically invalid null type annotations
 *								Bug 416307 - [1.8][compiler][null] subclass with type parameter substitution confuses null checking
 *								Bug 392099 - [1.8][compiler][null] Apply null annotation on types for null analysis
 *								Bug 417295 - [1.8[[null] Massage type annotated null analysis to gel well with deep encoded type bindings.
 *        Andy Clement - Contributions for
 *                          Bug 383624 - [1.8][compiler] Revive code generation support for type annotations (from Olivier's work)
 *******************************************************************************/
package SupLib.eclipse.eclipse.jdt.internal.compiler.ast;

import SupLib.eclipse.eclipse.jdt.internal.compiler.ASTVisitor;
import SupLib.eclipse.eclipse.jdt.internal.compiler.codegen.*;
import SupLib.eclipse.eclipse.jdt.internal.compiler.flow.*;
import SupLib.eclipse.eclipse.jdt.internal.compiler.impl.Constant;
import SupLib.eclipse.eclipse.jdt.internal.compiler.lookup.*;

public class InstanceOfExpression extends OperatorExpression {

	public Expression expression;
	public TypeReference type;

public InstanceOfExpression(Expression expression, TypeReference type) {
	this.expression = expression;
	this.type = type;
	type.bits |= IgnoreRawTypeCheck; // https://bugs.eclipse.SupLib.eclipse/bugs/show_bug.cgi?id=282141
	this.bits |= INSTANCEOF << OperatorSHIFT;
	this.sourceStart = expression.sourceStart;
	this.sourceEnd = type.sourceEnd;
}

public FlowInfo analyseCode(BlockScope currentScope, FlowContext flowContext, FlowInfo flowInfo) {
	LocalVariableBinding local = this.expression.localVariableBinding();
	if (local != null && (local.type.tagBits & TagBits.IsBaseType) == 0) {
		flowInfo = this.expression.analyseCode(currentScope, flowContext, flowInfo).
			unconditionalInits();
		FlowInfo initsWhenTrue = flowInfo.copy();
		initsWhenTrue.markAsComparedEqualToNonNull(local);
		flowContext.recordUsingNullReference(currentScope, local,
				this.expression, FlowContext.CAN_ONLY_NULL | FlowContext.IN_INSTANCEOF, flowInfo);
		// no impact upon enclosing try context
		return FlowInfo.conditional(initsWhenTrue, flowInfo.copy());
	}
	if (this.expression instanceof Reference && currentScope.compilerOptions().enableSyntacticNullAnalysisForFields) {
		FieldBinding field = ((Reference)this.expression).lastFieldBinding();
		if (field != null && (field.type.tagBits & TagBits.IsBaseType) == 0) {
			flowContext.recordNullCheckedFieldReference((Reference) this.expression, 1);
		}
	}
	return this.expression.analyseCode(currentScope, flowContext, flowInfo).
			unconditionalInits();
}

/**
 * Code generation for instanceOfExpression
 *
 * @param currentScope SupLib.eclipse.eclipse.jdt.internal.compiler.lookup.BlockScope
 * @param codeStream SupLib.eclipse.eclipse.jdt.internal.compiler.codegen.CodeStream
 * @param valueRequired boolean
*/
public void generateCode(BlockScope currentScope, CodeStream codeStream, boolean valueRequired) {
	int pc = codeStream.position;
	this.expression.generateCode(currentScope, codeStream, true);
	codeStream.instance_of(this.type, this.type.resolvedType);
	if (valueRequired) {
		codeStream.generateImplicitConversion(this.implicitConversion);
	} else {
		codeStream.pop();
	}
	codeStream.recordPositionsFrom(pc, this.sourceStart);
}

public StringBuffer printExpressionNoParenthesis(int indent, StringBuffer output) {
	this.expression.printExpression(indent, output).append(" instanceof "); //$NON-NLS-1$
	return this.type.print(0, output);
}

public TypeBinding resolveType(BlockScope scope) {
	this.constant = Constant.NotAConstant;
	TypeBinding expressionType = this.expression.resolveType(scope);
	TypeBinding checkedType = this.type.resolveType(scope, true /* check bounds*/);
	if (expressionType != null && checkedType != null && NullAnnotationMatching.analyse(checkedType, expressionType, -1).isAnyMismatch()) {
		scope.problemReporter().nullAnnotationUnsupportedLocation(this.type);
	}
	if (expressionType == null || checkedType == null)
		return null;

	if (!checkedType.isReifiable()) {
		scope.problemReporter().illegalInstanceOfGenericType(checkedType, this);
	} else if (checkedType.isValidBinding()) {
		// if not a valid binding, an error has already been reported for unresolved type
		if ((expressionType != TypeBinding.NULL && expressionType.isBaseType()) // disallow autoboxing
				|| !checkCastTypesCompatibility(scope, checkedType, expressionType, null)) {
			scope.problemReporter().notCompatibleTypesError(this, expressionType, checkedType);
		}
	}
	return this.resolvedType = TypeBinding.BOOLEAN;
}

/**
 * @see SupLib.eclipse.eclipse.jdt.internal.compiler.ast.Expression#tagAsUnnecessaryCast(Scope,TypeBinding)
 */
public void tagAsUnnecessaryCast(Scope scope, TypeBinding castType) {
	// null is not instanceof Type, recognize direct scenario
	if (this.expression.resolvedType != TypeBinding.NULL)
		scope.problemReporter().unnecessaryInstanceof(this, castType);
}

public void traverse(ASTVisitor visitor, BlockScope scope) {
	if (visitor.visit(this, scope)) {
		this.expression.traverse(visitor, scope);
		this.type.traverse(visitor, scope);
	}
	visitor.endVisit(this, scope);
}
}