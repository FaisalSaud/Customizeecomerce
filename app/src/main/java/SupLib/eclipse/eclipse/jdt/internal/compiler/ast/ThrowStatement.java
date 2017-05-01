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
 *     Stephan Herrmann - Contributions for
 *								bug 359334 - Analysis for resource leak warnings does not consider exceptions as method exit points
 *								bug 368546 - [compiler][resource] Avoid remaining false positives found when compiling the Eclipse SDK
 *								bug 345305 - [compiler][null] Compiler misidentifies a case of "variable can only be null"
 *******************************************************************************/
package SupLib.eclipse.eclipse.jdt.internal.compiler.ast;

import SupLib.eclipse.eclipse.jdt.internal.compiler.ASTVisitor;
import SupLib.eclipse.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;
import SupLib.eclipse.eclipse.jdt.internal.compiler.codegen.CodeStream;
import SupLib.eclipse.eclipse.jdt.internal.compiler.flow.FlowContext;
import SupLib.eclipse.eclipse.jdt.internal.compiler.flow.FlowInfo;
import SupLib.eclipse.eclipse.jdt.internal.compiler.lookup.BlockScope;
import SupLib.eclipse.eclipse.jdt.internal.compiler.lookup.TypeBinding;
import SupLib.eclipse.eclipse.jdt.internal.compiler.lookup.TypeIds;

public class ThrowStatement extends Statement {

	public Expression exception;
	public TypeBinding exceptionType;

public ThrowStatement(Expression exception, int sourceStart, int sourceEnd) {
	this.exception = exception;
	this.sourceStart = sourceStart;
	this.sourceEnd = sourceEnd;
}

public FlowInfo analyseCode(BlockScope currentScope, FlowContext flowContext, FlowInfo flowInfo) {
	this.exception.analyseCode(currentScope, flowContext, flowInfo);
	this.exception.checkNPE(currentScope, flowContext, flowInfo);
	// need to check that exception thrown is actually caught somewhere
	flowContext.checkExceptionHandlers(this.exceptionType, this, flowInfo, currentScope);
	currentScope.checkUnclosedCloseables(flowInfo, flowContext, this, currentScope);
	flowContext.recordAbruptExit();
	return FlowInfo.DEAD_END;
}

/**
 * Throw code generation
 *
 * @param currentScope SupLib.eclipse.eclipse.jdt.internal.compiler.lookup.BlockScope
 * @param codeStream SupLib.eclipse.eclipse.jdt.internal.compiler.codegen.CodeStream
 */
public void generateCode(BlockScope currentScope, CodeStream codeStream) {
	if ((this.bits & ASTNode.IsReachable) == 0)
		return;
	int pc = codeStream.position;
	this.exception.generateCode(currentScope, codeStream, true);
	codeStream.athrow();
	codeStream.recordPositionsFrom(pc, this.sourceStart);
}

public StringBuffer printStatement(int indent, StringBuffer output) {
	printIndent(indent, output).append("throw "); //$NON-NLS-1$
	this.exception.printExpression(0, output);
	return output.append(';');
}

public void resolve(BlockScope scope) {
	this.exceptionType = this.exception.resolveType(scope);
	if (this.exceptionType != null && this.exceptionType.isValidBinding()) {
		if (this.exceptionType == TypeBinding.NULL) {
			if (scope.compilerOptions().complianceLevel <= ClassFileConstants.JDK1_3){
				// if compliant with 1.4, this problem will not be reported
				scope.problemReporter().cannotThrowNull(this.exception);
			}
	 	} else if (this.exceptionType.findSuperTypeOriginatingFrom(TypeIds.T_JavaLangThrowable, true) == null) {
			scope.problemReporter().cannotThrowType(this.exception, this.exceptionType);
		}
		this.exception.computeConversion(scope, this.exceptionType, this.exceptionType);
	}
}

public void traverse(ASTVisitor visitor, BlockScope blockScope) {
	if (visitor.visit(this, blockScope))
		this.exception.traverse(visitor, blockScope);
	visitor.endVisit(this, blockScope);
}
}