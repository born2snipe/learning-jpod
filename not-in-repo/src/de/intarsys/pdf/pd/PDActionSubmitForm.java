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
package de.intarsys.pdf.pd;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.intarsys.pdf.cos.COSArray;
import de.intarsys.pdf.cos.COSDictionary;
import de.intarsys.pdf.cos.COSInteger;
import de.intarsys.pdf.cos.COSName;
import de.intarsys.pdf.cos.COSObject;

/**
 * The SubmitForm action.
 * <p>
 * When executed the action submit the documents AcroForm acording to the
 * defined flags.
 * 
 */
public class PDActionSubmitForm extends PDAction {
	/**
	 * The meta class implementation
	 */
	static public class MetaClass extends PDAction.MetaClass {
		protected MetaClass(Class instanceClass) {
			super(instanceClass);
		}
	}

	/** The meta class instance */
	static public final MetaClass META = new MetaClass(MetaClass.class
			.getDeclaringClass());

	static public final COSName CN_ActionType_SubmitForm = COSName
			.constant("SubmitForm");

	static public final COSName DK_F = COSName.constant("F");

	static public final COSName DK_Fields = COSName.constant("Fields");

	static public final COSName DK_Flags = COSName.constant("Flags");

	private SubmitFormFlags flags;

	protected PDActionSubmitForm(COSObject object) {
		super(object);
	}

	public COSName cosGetExpectedActionType() {
		return CN_ActionType_SubmitForm;
	}

	public void setCanonicalFormat(boolean flag) {
		getFlags().setCanonicalFormat(flag);
	}

	public boolean isCanonicalFormat() {
		return getFlags().isCanonicalFormat();
	}

	public void setEmbedForm(boolean flag) {
		getFlags().setEmbedForm(flag);
	}

	public boolean isEmbedForm() {
		return getFlags().isEmbedForm();
	}

	public void setExclFKey(boolean flag) {
		getFlags().setExclFKey(flag);
	}

	public boolean isExclFKey() {
		return getFlags().isExclFKey();
	}

	public void setExclNonUserAnnots(boolean flag) {
		getFlags().setExclNonUserAnnots(flag);
	}

	public boolean isExclNonUserAnnots() {
		return getFlags().isExclNonUserAnnots();
	}

	public void setExportFormat(boolean flag) {
		getFlags().setExportFormat(flag);
	}

	public boolean isExportFormat() {
		return getFlags().isExportFormat();
	}

	public void setGetMethod(boolean flag) {
		getFlags().setGetMethod(flag);
	}

	public boolean isGetMethod() {
		return getFlags().isGetMethod();
	}

	public void setInclude(boolean flag) {
		getFlags().setInclude(flag);
	}

	public boolean isInclude() {
		return getFlags().isInclude();
	}

	public void setIncludeAnnotations(boolean flag) {
		getFlags().setIncludeAnnotations(flag);
	}

	public boolean isIncludeAnnotations() {
		return getFlags().isIncludeAnnotations();
	}

	public void setIncludeAppendSaves(boolean flag) {
		getFlags().setIncludeAppendSaves(flag);
	}

	public boolean isIncludeAppendSaves() {
		return getFlags().isIncludeAppendSaves();
	}

	public void setIncludeNoValueFields(boolean flag) {
		getFlags().setIncludeNoValueFields(flag);
	}

	public boolean isIncludeNoValueFields() {
		return getFlags().isIncludeNoValueFields();
	}

	public void setNotDEFINED(boolean flag) {
		getFlags().setNotDEFINED(flag);
	}

	public boolean isNotDEFINED() {
		return getFlags().isNotDEFINED();
	}

	public void setSubmitCoordinates(boolean flag) {
		getFlags().setSubmitCoordinates(flag);
	}

	public boolean isSubmitCoordinates() {
		return getFlags().isSubmitCoordinates();
	}

	public void setSubmitPDF(boolean flag) {
		getFlags().setSubmitPDF(flag);
	}

	public boolean isSubmitPDF() {
		return getFlags().isSubmitPDF();
	}

	public void setUrl(URL url) {
		if (url != null) {
			setUrlSpecification(PDFileSpecificationURL.createNew(url));
		} else {
			setUrlSpecification(null);
		}
	}

	public URL getUrl() {
		PDFileSpecification fs = getUrlSpecification();
		if (fs != null) {
			return ((PDFileSpecificationURL) fs).getURL();
		}
		return null;
	}

	public void setXFDF(boolean flag) {
		getFlags().setXFDF(flag);
	}

	public boolean isXFDF() {
		return getFlags().isXFDF();
	}

	static public PDActionSubmitForm createNew(URL url) {
		PDActionSubmitForm result = (PDActionSubmitForm) PDActionSubmitForm.META
				.createNew();
		result.setUrl(url);
		return result;
	}

	protected SubmitFormFlags getFlags() {
		if (flags == null) {
			flags = new SubmitFormFlags(this);
		}
		return flags;
	}

	/**
	 * A list of field names (plain Java String) to be resetted or null.
	 * 
	 * @return A list of field names (plain Java String) to be resetted or null.
	 */
	public List getFields() {
		COSArray array = cosGetField(DK_Fields).asArray();
		if (array != null) {
			List result = new ArrayList();
			for (Iterator i = array.iterator(); i.hasNext();) {
				COSObject entry = (COSObject) i.next();
				if (entry instanceof COSDictionary) {
					PDAcroFormField field = (PDAcroFormField) PDAcroFormField.META
							.createFromCos(entry);
					result.add(field.getQualifiedName());
				} else {
					result.add(entry.stringValue());
				}
			}
			return result;
		}
		return null;
	}

	protected int basicGetFlags() {
		// inheritance doesn't make any sense, so we ignore it right now
		return getFieldInt(DK_Flags, 0);
	}

	protected PDFileSpecification getUrlSpecification() {
		COSObject cosObject = cosGetField(DK_F);
		if (cosObject.isNull()) {
			return null;
		}
		return (PDFileSpecificationURL) PDFileSpecificationURL.META
				.createFromCos(cosObject);
	}

	protected void setFields(List newFields) {
		// TODO 1 @wad cosSet type: array: PDFormField
	}

	protected void basicSetFlags(int newFlags) {
		if (newFlags != 0) { // default
			cosSetField(DK_Flags, COSInteger.create(newFlags));
		} else {
			cosRemoveField(DK_Flags);
		}
	}

	protected void setUrlSpecification(PDFileSpecification newUrl) {
		setFieldObject(DK_F, newUrl);
	}
}
