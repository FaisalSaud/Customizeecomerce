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
 *     Jesper S Moller - Contributions for
 *								Bug 378674 - "The method can be declared as static" is wrong
 *******************************************************************************/
package SupLib.eclipse.eclipse.jdt.internal.compiler.ast;

import SupLib.eclipse.eclipse.jdt.internal.compiler.ASTVisitor;
import SupLib.eclipse.eclipse.jdt.internal.compiler.impl.Constant;
import SupLib.eclipse.eclipse.jdt.internal.compiler.lookup.BlockScope;
import SupLib.eclipse.eclipse.jdt.internal.compiler.lookup.ReferenceBinding;
import SupLib.eclipse.eclipse.jdt.internal.compiler.lookup.TypeBinding;

public class SuperReference extends ThisReference {

	public SuperReference(int sourceStart, int sourceEnd) {

		super(sourceStart, sourceEnd);
	}

	public static ExplicitConstructorCall implicitSuperConstructorCall() {

		return new ExplicitConstructorCall(ExplicitConstructorCall.ImplicitSuper);
	}

	public boolean isImplicitThis() {

		return false;
	}

	public boolean isSuper() {

		return true;
	}

	public boolean isThis() {

		return false ;
	}

	public StringBuffer printExpression(int indent, StringBuffer output){

		return output.append("super"); //$NON-NLS-1$

	}

	public TypeBinding resolveType(BlockScope scope) {

		this.constant = Constant.NotAConstant;
		ReferenceBinding enclosingReceiverType = scope.enclosingReceiverType();
		if (!checkAccess(scope, enclosingReceiverType))
			return null;
		if (enclosingReceiverType.id == T_JavaLangObject) {
			scope.problemReporter().cannotUseSuperInJavaLangObject(this);
			return null;
		}
		return this.resolvedType = enclosingReceiverType.superclass();
	}

	public void traverse(ASTVisitor visitor, BlockScope blockScope) {
		visitor.visit(this, blockScope);
		visitor.endVisit(this, blockScope);
	}
}