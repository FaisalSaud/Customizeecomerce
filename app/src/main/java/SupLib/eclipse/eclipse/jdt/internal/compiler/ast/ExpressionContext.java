/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others.
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
 *******************************************************************************/
package SupLib.eclipse.eclipse.jdt.internal.compiler.ast;

public interface ExpressionContext {
	
	/* Assignment context: potential poly-expressions are: method invocations, lambdas, reference expressions, 
	   conditional expressions and allocation expressions. This is the only Java 7 context where target type
	   influenced evaluation of some expression.
	   
	   Context induced by: ReturnStatement, ArrayInitializer, Assignment, FieldDeclaration and LocalDeclaration. 
	*/
	public static final ExpressionContext ASSIGNMENT_CONTEXT = 
								new ExpressionContext() {
									public String toString() {
										return "assignment context"; //$NON-NLS-1$
									}
								};
	
	/* Invocation context: potential poly-expressions are: method invocations, lambdas, reference expressions, 
	   conditional expressions and allocation expressions. At this point, we don't distinguish between strict 
	   and loose invocation contexts - we may have to cross the bridge some day.
	   
	   Context induced by: AllocationExpression, QualifiedAllocationExpression, ExplicitConstructorCall, MessageSend
	   CodeSnippetAllocationExpression and CodeSnippetMessageSend.
	*/													
	public static final ExpressionContext INVOCATION_CONTEXT = 
								new ExpressionContext() {
									public String toString() {
										return "invocation context"; //$NON-NLS-1$
									}
								};
	
	/* Casting context: potential poly-expressions are: lambdas and reference expressions
	   Context induced by: CastExpression.
	*/
	public static final ExpressionContext CASTING_CONTEXT = 
								new ExpressionContext() {
									public String toString() {
										return "casting context"; //$NON-NLS-1$
									}
								};
	
	/* Vanilla context (string, numeric): potential poly-expressions are: NONE. This is the nonpoly context in which 
	   expressions get evaluated, unless they feature in one of the above contexts. 
	*/
	public static final ExpressionContext VANILLA_CONTEXT = 
								new ExpressionContext() {
									public String toString() {
										return "vanilla context"; //$NON-NLS-1$
									}
								};
		
}