/*******************************************************************************
 * Copyright (c) 2000, 2012 IBM Corporation and others.
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

import SupLib.eclipse.eclipse.jdt.internal.compiler.ASTVisitor;
import SupLib.eclipse.eclipse.jdt.internal.compiler.impl.Constant;
import SupLib.eclipse.eclipse.jdt.internal.compiler.lookup.*;

public class JavadocImplicitTypeReference extends TypeReference {

	public char[] token;

	public JavadocImplicitTypeReference(char[] name, int pos) {
		super();
		this.token = name;
		this.sourceStart = pos;
		this.sourceEnd = pos;
	}
	
	public TypeReference augmentTypeWithAdditionalDimensions(int additionalDimensions, Annotation[][] additionalAnnotations, boolean isVarargs) {
		return null;
	}

	/* (non-Javadoc)
	 * @see SupLib.eclipse.eclipse.jdt.internal.compiler.ast.TypeReference#getTypeBinding(SupLib.eclipse.eclipse.jdt.internal.compiler.lookup.Scope)
	 */
	protected TypeBinding getTypeBinding(Scope scope) {
		this.constant = Constant.NotAConstant;
		return this.resolvedType = scope.enclosingReceiverType();
	}

	public char[] getLastToken() {
		return this.token;
	}

	/* (non-Javadoc)
	 * @see SupLib.eclipse.eclipse.jdt.internal.compiler.ast.TypeReference#getTypeName()
	 */
	public char[][] getTypeName() {
		if (this.token != null) {
			char[][] tokens = { this.token };
			return tokens;
		}
		return null;
	}
	public boolean isThis() {
		return true;
	}

	/*
	 * Resolves type on a Block, Class or CompilationUnit scope.
	 * We need to modify resoling behavior to avoid raw type creation.
	 */
	protected TypeBinding internalResolveType(Scope scope) {
		// handle the error here
		this.constant = Constant.NotAConstant;
		if (this.resolvedType != null) { // is a shared type reference which was already resolved
			if (this.resolvedType.isValidBinding()) {
				return this.resolvedType;
			} else {
				switch (this.resolvedType.problemId()) {
					case ProblemReasons.NotFound :
					case ProblemReasons.NotVisible :
						TypeBinding type = this.resolvedType.closestMatch();
						return type;
					default :
						return null;
				}
			}
		}
		boolean hasError;
		TypeBinding type = this.resolvedType = getTypeBinding(scope);
		if (type == null) {
			return null; // detected cycle while resolving hierarchy
		} else if ((hasError = !type.isValidBinding())== true) {
			reportInvalidType(scope);
			switch (type.problemId()) {
				case ProblemReasons.NotFound :
				case ProblemReasons.NotVisible :
					type = type.closestMatch();
					if (type == null) return null;
					break;
				default :
					return null;
			}
		}
		if (type.isArrayType() && ((ArrayBinding) type).leafComponentType == TypeBinding.VOID) {
			scope.problemReporter().cannotAllocateVoidArray(this);
			return null;
		}
		if (isTypeUseDeprecated(type, scope)) {
			reportDeprecatedType(type, scope);
		}
		// https://bugs.eclipse.SupLib.eclipse/bugs/show_bug.cgi?id=209936
		// raw convert all enclosing types when dealing with Javadoc references
		if (type.isGenericType() || type.isParameterizedType()) {
			type = scope.environment().convertToRawType(type, true /*force the conversion of enclosing types*/);
		}

		if (hasError) {
			// do not store the computed type, keep the problem type instead
			return type;
		}
		return this.resolvedType = type;
	}

	protected void reportInvalidType(Scope scope) {
		scope.problemReporter().javadocInvalidType(this, this.resolvedType, scope.getDeclarationModifiers());
	}
	protected void reportDeprecatedType(TypeBinding type, Scope scope) {
		scope.problemReporter().javadocDeprecatedType(type, this, scope.getDeclarationModifiers());
	}

	public void traverse(ASTVisitor visitor, BlockScope scope) {
		visitor.visit(this, scope);
		visitor.endVisit(this, scope);
	}

	public void traverse(ASTVisitor visitor, ClassScope scope) {
		visitor.visit(this, scope);
		visitor.endVisit(this, scope);
	}

	public StringBuffer printExpression(int indent, StringBuffer output) {
		return new StringBuffer();
	}
}
