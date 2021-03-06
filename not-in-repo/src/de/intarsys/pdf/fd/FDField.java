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
package de.intarsys.pdf.fd;

import java.util.List;

import de.intarsys.pdf.cos.COSArray;
import de.intarsys.pdf.cos.COSDictionary;
import de.intarsys.pdf.cos.COSName;
import de.intarsys.pdf.cos.COSObject;
import de.intarsys.pdf.cos.COSString;
import de.intarsys.pdf.pd.PDAcroFormNode;

public class FDField extends FDObject {
	public static final COSName DK_Kids = COSName.constant("Kids");

	public static final COSName DK_T = COSName.constant("T");

	public static final COSName DK_V = COSName.constant("V");

	public static final COSName DK_Ff = COSName.constant("Ff");

	public static final COSName DK_SetFf = COSName.constant("SetFf");

	public static final COSName DK_ClrFf = COSName.constant("ClrFf");

	public static final COSName DK_F = COSName.constant("F");

	public static final COSName DK_SetF = COSName.constant("SetF");

	public static final COSName DK_ClrF = COSName.constant("ClrF");

	public static final COSName DK_AP = COSName.constant("AP");

	public static final COSName DK_APRef = COSName.constant("APRef");

	public static final COSName DK_IF = COSName.constant("IF");

	public static final COSName DK_Opt = COSName.constant("Opt");

	public static final COSName DK_A = COSName.constant("A");

	public static final COSName DK_AA = COSName.constant("AA");

	public static final COSName DK_RV = COSName.constant("RV");

	/**
	 * The meta class implementation
	 */
	static public class MetaClass extends PDAcroFormNode.MetaClass {
		protected MetaClass(Class instanceClass) {
			super(instanceClass);
		}
	}

	/** The meta class instance */
	static public final MetaClass META = new MetaClass(MetaClass.class
			.getDeclaringClass());

	protected FDField(COSObject object) {
		super(object);
	}

	public List getKids() {
		return getFDObjects(DK_Kids, FDField.META);
	}

	public void setFields(List fields) {
		setFDObjects(DK_Kids, fields, true);
	}

	public void addField(FDField field) {
		cosAddKid(field.cosGetDict());
	}

	public void cosAddKid(COSDictionary field) {
		COSArray cosFields = cosGetField(DK_Kids).asArray();
		if (cosFields == null) {
			cosFields = COSArray.create();
			cosSetField(DK_Kids, cosFields);
		}
		cosFields.add(field);
	}

	public COSObject cosGetValue() {
		return cosGetField(DK_V);
	}

	public void cosSetValue(COSObject value) {
		cosSetField(DK_V, value);
	}

	public String getLocalName() {
		COSString cosObject = cosGetField(DK_T).asString();
		if (cosObject != null) {
			return cosObject.stringValue();
		}
		return null;
	}

	public void setLocalName(String name) {
		setFieldString(DK_T, name);
	}
}
