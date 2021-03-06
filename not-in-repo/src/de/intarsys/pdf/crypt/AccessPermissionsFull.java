/*
 * Copyright (c) 2007, intarsys consulting GmbH
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *
 * - Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * - Neither the name of intarsys nor the names of its contributors may be used
 *   to endorse or promote products derived from this software without specific
 *   prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package de.intarsys.pdf.crypt;

/**
 * Provide full access to PDF features.
 * 
 */
public final class AccessPermissionsFull implements IAccessPermissions {

	final private static AccessPermissionsFull active = new AccessPermissionsFull();

	public static AccessPermissionsFull get() {
		return active;
	}

	private AccessPermissionsFull() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.intarsys.pdf.encryption.IAccessPermissions#mayAssemble()
	 */
	public final boolean mayAssemble() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.intarsys.pdf.encryption.IAccessPermissions#mayCopy()
	 */
	public final boolean mayCopy() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.intarsys.pdf.encryption.IAccessPermissions#mayExtract()
	 */
	public final boolean mayExtract() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.intarsys.pdf.encryption.IAccessPermissions#mayFillForm()
	 */
	public final boolean mayFillForm() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.intarsys.pdf.encryption.IAccessPermissions#mayModify()
	 */
	public final boolean mayModify() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.intarsys.pdf.encryption.IAccessPermissions#mayModifyAnnotation()
	 */
	public final boolean mayModifyAnnotation() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.intarsys.pdf.encryption.IAccessPermissions#mayPrint()
	 */
	public final boolean mayPrint() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.intarsys.pdf.encryption.IAccessPermissions#mayPrintHighQuality()
	 */
	public final boolean mayPrintHighQuality() {
		return true;
	}
}
