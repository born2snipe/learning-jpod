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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import de.intarsys.pdf.cos.COSBasedObject;
import de.intarsys.pdf.cos.COSName;
import de.intarsys.pdf.cos.COSNull;
import de.intarsys.pdf.cos.COSObject;
import de.intarsys.pdf.cos.COSString;
import de.intarsys.tools.string.StringTools;

/**
 * A logical button within an AcroForm.
 * 
 */
public class PDAFButtonField extends PDAcroFormField {
	public static class MetaClass extends PDAcroFormField.MetaClass {
		protected MetaClass(Class instanceClass) {
			super(instanceClass);
		}

		protected COSBasedObject doCreateCOSBasedObject(COSObject object) {
			return new PDAFButtonField(object);
		}
	}

	/** The meta class instance */
	public static final MetaClass META = new MetaClass(MetaClass.class
			.getDeclaringClass());

	static public final COSName DK_Opt = COSName.constant("Opt");

	protected PDAFButtonField(COSObject object) {
		super(object);
	}

	/**
	 * Return true when this is an object with checkbox behavior.
	 * 
	 * @return true when this is an object with checkbox behavior.
	 */
	public boolean isCheckbox() {
		return !isPushbutton() && !isRadio();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.intarsys.pdf.pd.PDAcroFormField#cosGetExpectedFieldType()
	 */
	public COSName cosGetExpectedFieldType() {
		return CN_FT_Btn;
	}

	/**
	 * Make this a push button.
	 * 
	 * @param f
	 */
	public void setPushbutton(boolean f) {
		getFieldFlags().setPushbutton(f);
	}

	/**
	 * <code>true</code> if this is a pushbutton.
	 * 
	 * @return <code>true</code> if this is a pushbutton.
	 */
	public boolean isPushbutton() {
		return getFieldFlags().isPushbutton();
	}

	/**
	 * <code>true</code> if this is a radio button.
	 * 
	 * @return <code>true</code> if this is a radio button.
	 */
	public boolean isRadio() {
		return getFieldFlags().isRadio();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.intarsys.pdf.pd.PDAcroFormField#isTypeBtn()
	 */
	public boolean isTypeBtn() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.intarsys.pdf.pd.PDAcroFormField#cosSetValue(de.intarsys.pdf.cos.COSObject)
	 */
	public COSObject cosSetValue(COSObject state) {
		if (isCheckbox() || isRadio()) {
			COSName newState;
			if (state instanceof COSName) {
				newState = (COSName) state;
			} else {
				if (state == null) {
					newState = PDWidgetAnnotation.CN_State_Off;
				} else {
					newState = COSName.create(state.stringValue());
				}
			}

			for (Iterator i = getAnnotations().iterator(); i.hasNext();) {
				PDWidgetAnnotation annot = (PDWidgetAnnotation) i.next();
				Set states = annot.getAppearanceStates();
				if (states.contains(newState)) {
					annot.setAppearanceState((COSName) newState.copyOptional());
				} else {
					annot.setAppearanceState(PDWidgetAnnotation.CN_State_Off);
				}
			}
			return super.cosSetValue(newState);
		}
		return COSNull.NULL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.intarsys.pdf.pd.PDAcroFormField#setValueString(java.lang.String)
	 */
	public void setValueString(String value) {
		if (value == null) {
			super.setValueString(value);
		}
		if (isCheckbox() || isRadio()) {
			COSName state = COSName.create(value);
			cosSetValue(state);
		}
	}

	/**
	 * set the correct appearance state in a button annotation
	 * 
	 * <p>
	 * This code applies to checkboxes only
	 * </p>
	 * 
	 * @param annot
	 *            the button annotation
	 * @param value
	 *            the state to be selected
	 * 
	 * @return the name of the state
	 */
	protected COSName setButtonAppearanceState(PDAnnotation annot, String value) {
		// TODO 2 refactor location
		COSName state = COSName.create(value);
		Set states = annot.getAppearanceStates();
		if (!states.contains(state)) {
			COSName offState = COSName.create("Off");
			state = offState;
			value = value.toLowerCase().trim();
			// todo 3 provide property "true" characters
			if (value.equals("1") || value.startsWith("t") || // true
					value.startsWith("y") || // yes
					value.startsWith("w") || // wahr
					value.startsWith("j") || // ja
					value.startsWith("x") // ankreuzen
			) {
				for (Iterator i = states.iterator(); i.hasNext();) {
					COSName aState = (COSName) i.next();
					if (!aState.equals(offState)) {
						state = (COSName) aState.copyOptional();
						break;
					}
				}
			}
		}
		annot.setAppearanceState(state);
		return state;
	}

	/**
	 * The {@link java.util.Set} of possible states the button can enter.
	 * 
	 * @return The {@link java.util.Set} of possible states the button can enter.
	 */
	public Set getAvailableButtonAppearanceStates() {
		// todo change signature
		Set availableStates = new HashSet();
		for (Iterator i = getAnnotations().iterator(); i.hasNext();) {
			PDAnnotation child = (PDAnnotation) i.next();
			availableStates.addAll(child.getAppearanceStates());
		}
		return availableStates;
	}

	/**
	 * The {@link java.util.Set} of possible non - off states the button can enter.
	 * 
	 * @return The {@link java.util.Set} of possible non - off states the button can
	 *         enter.
	 */
	public Set getAvailableButtonAppearanceStatesNoOff() {
		// todo change signature
		Set availableStates = getAvailableButtonAppearanceStates();
		COSName offState = COSName.create("Off");
		availableStates.remove(offState);
		return availableStates;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.intarsys.pdf.pd.PDAcroFormField#reset()
	 */
	public void reset() {
		//
		COSObject value = cosGetDefaultValue();
		if (value.isNull()) {
			value = COSString.create(StringTools.EMPTY);
		} else {
			value = value.copyOptional();
		}
		cosSetValue(value);
	}

	/**
	 * <code>true</code> if this is checked.
	 * 
	 * @return <code>true</code> if this is checked.
	 */
	public boolean isChecked() {
		if (isCheckbox()) {
			return !PDWidgetAnnotation.CN_State_Off.equals(getAnyAnnotation()
					.getAppearanceState());
		}
		return false;
	}
}
