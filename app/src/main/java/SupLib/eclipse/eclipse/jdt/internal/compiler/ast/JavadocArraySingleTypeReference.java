/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.SupLib.eclipse/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package SupLib.eclipse.eclipse.jdt.internal.compiler.ast;

import SupLib.eclipse.eclipse.jdt.internal.compiler.ASTVisitor;
import SupLib.eclipse.eclipse.jdt.internal.compiler.lookup.BlockScope;
import SupLib.eclipse.eclipse.jdt.internal.compiler.lookup.ClassScope;
import SupLib.eclipse.eclipse.jdt.internal.compiler.lookup.Scope;
import SupLib.eclipse.eclipse.jdt.internal.compiler.lookup.TypeBinding;


public class JavadocArraySingleTypeReference extends ArrayTypeReference {

	public JavadocArraySingleTypeReference(char[] name, int dim, long pos) {
		super(name, dim, pos);
		this.bits |= InsideJavadoc;
	}

	protected void reportInvalidType(Scope scope) {
		scope.problemReporter().javadocInvalidType(this, this.resolvedType, scope.getDeclarationModifiers());
	}
	protected void reportDeprecatedType(TypeBinding type, Scope scope) {
		scope.problemReporter().javadocDeprecatedType(type, this, scope.getDeclarationModifiers());
	}

	/* (non-Javadoc)
	 * Redefine to capture javadoc specific signatures
	 * @see SupLib.eclipse.eclipse.jdt.internal.compiler.ast.ASTNode#traverse(SupLib.eclipse.eclipse.jdt.internal.compiler.ASTVisitor, SupLib.eclipse.eclipse.jdt.internal.compiler.lookup.BlockScope)
	 */
	public void traverse(ASTVisitor visitor, BlockScope scope) {
		visitor.visit(this, scope);
		visitor.endVisit(this, scope);
	}

	public void traverse(ASTVisitor visitor, ClassScope scope) {
		visitor.visit(this, scope);
		visitor.endVisit(this, scope);
	}
}
