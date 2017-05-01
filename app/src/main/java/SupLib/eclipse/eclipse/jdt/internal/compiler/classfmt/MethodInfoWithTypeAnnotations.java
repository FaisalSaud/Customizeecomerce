/*******************************************************************************
 * Copyright (c) 2013 GoPivotal, Inc. All Rights Reserved.
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
 *        Andy Clement (GoPivotal, Inc) aclement@gopivotal.com - Contributions for
 *            Bug 407191 - [1.8] Binary access support for type annotations
 *******************************************************************************/
package SupLib.eclipse.eclipse.jdt.internal.compiler.classfmt;

import SupLib.eclipse.eclipse.jdt.internal.compiler.env.IBinaryTypeAnnotation;

class MethodInfoWithTypeAnnotations extends MethodInfoWithParameterAnnotations {
	private TypeAnnotationInfo[] typeAnnotations;

MethodInfoWithTypeAnnotations(MethodInfo methodInfo, AnnotationInfo[] annotations, AnnotationInfo[][] parameterAnnotations, TypeAnnotationInfo[] typeAnnotations) {
	super(methodInfo, annotations, parameterAnnotations);
	this.typeAnnotations = typeAnnotations;
}
public IBinaryTypeAnnotation[] getTypeAnnotations() {
	return this.typeAnnotations;
}

protected void initialize() {
	for (int i = 0, l = this.typeAnnotations == null ? 0 : this.typeAnnotations.length; i < l; i++) {
		this.typeAnnotations[i].initialize();
	}
	super.initialize();
}
protected void reset() {
	for (int i = 0, l = this.typeAnnotations == null ? 0 : this.typeAnnotations.length; i < l; i++) {
		this.typeAnnotations[i].reset();
	}
	super.reset();
}
protected void toStringContent(StringBuffer buffer) {
	super.toStringContent(buffer);
	buffer.append("type annotations = \n");//$NON-NLS-1$
	for (int i = 0, l = this.typeAnnotations == null ? 0 : this.typeAnnotations.length; i < l; i++) {
		buffer.append(this.typeAnnotations[i].toString());
		buffer.append('\n');
	}
}
}