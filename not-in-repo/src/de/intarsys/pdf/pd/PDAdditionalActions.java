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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.intarsys.pdf.cos.COSName;
import de.intarsys.pdf.cos.COSObject;

/**
 * An object defining the association of an event to an action for a PDF
 * document object, for example a PDPage.
 * 
 */
public class PDAdditionalActions extends PDObject {
	/**
	 * The meta class implementation
	 */
	static public class MetaClass extends PDObject.MetaClass {
		protected MetaClass(Class instanceClass) {
			super(instanceClass);
		}
	}

	static public final COSName CN_trigger_E = COSName.constant("E");

	static public final COSName CN_trigger_X = COSName.constant("X");

	static public final COSName CN_trigger_D = COSName.constant("D");

	static public final COSName CN_trigger_U = COSName.constant("U");

	static public final COSName CN_trigger_Fo = COSName.constant("Fo");

	static public final COSName CN_trigger_Bl = COSName.constant("Bl");

	static public final COSName CN_trigger_PO = COSName.constant("PO");

	static public final COSName CN_trigger_PC = COSName.constant("PC");

	static public final COSName CN_trigger_PV = COSName.constant("PV");

	static public final COSName CN_trigger_PI = COSName.constant("PI");

	static public final COSName CN_trigger_O = COSName.constant("O");

	static public final COSName CN_trigger_K = COSName.constant("K");

	static public final COSName CN_trigger_F = COSName.constant("F");

	static public final COSName CN_trigger_V = COSName.constant("V");

	static public final COSName CN_trigger_C = COSName.constant("C");

	static public final COSName CN_trigger_DC = COSName.constant("DC");

	/** WC is the same as DC - not specified, but used by acrobat */
	static public final COSName CN_trigger_WC = COSName.constant("WC");

	static public final COSName CN_trigger_WS = COSName.constant("WS");

	static public final COSName CN_trigger_DS = COSName.constant("DS");

	static public final COSName CN_trigger_WP = COSName.constant("WP");

	static public final COSName CN_trigger_DP = COSName.constant("DP");

	static private Map reasonToName = new HashMap();

	static private Map reasonToType = new HashMap();

	static public final COSName CN_T_Mouse_Down = CN_trigger_D;

	/** The meta class instance */
	static public final MetaClass META = new MetaClass(MetaClass.class
			.getDeclaringClass());

	static {
		reasonToName.put(CN_trigger_K, "Keystroke");
		reasonToName.put(CN_trigger_V, "Validate");
		reasonToName.put(CN_trigger_Fo, "Focus");
		reasonToName.put(CN_trigger_Bl, "Blur");
		reasonToName.put(CN_trigger_F, "Format");
		reasonToName.put(CN_trigger_C, "Calculate");
		reasonToName.put(CN_trigger_U, "Mouse Up");
		reasonToName.put(CN_trigger_D, "Mouse Down");
		reasonToName.put(CN_trigger_E, "Mouse Enter");
		reasonToName.put(CN_trigger_X, "Mouse Exit");
		reasonToName.put(CN_trigger_WP, "WillPrint");
		reasonToName.put(CN_trigger_DP, "DidPrint");
		reasonToName.put(CN_trigger_WS, "WillSave");
		reasonToName.put(CN_trigger_DS, "DidSave");
	}

	static {
		reasonToType.put(CN_trigger_K, "Field");
		reasonToType.put(CN_trigger_V, "Field");
		reasonToType.put(CN_trigger_Fo, "Field");
		reasonToType.put(CN_trigger_Bl, "Field");
		reasonToType.put(CN_trigger_F, "Field");
		reasonToType.put(CN_trigger_C, "Field");
		reasonToType.put(CN_trigger_U, "Field");
		reasonToType.put(CN_trigger_D, "Field");
		reasonToType.put(CN_trigger_E, "Field");
		reasonToType.put(CN_trigger_X, "Field");
		reasonToType.put(CN_trigger_WP, "Doc");
		reasonToType.put(CN_trigger_DP, "Doc");
		reasonToType.put(CN_trigger_WS, "Doc");
		reasonToType.put(CN_trigger_DS, "Doc");
	}

	static public String getEventName(COSName reason) {
		String result = (String) reasonToName.get(reason);
		if (result == null) {
			return "";
		}
		return result;
	}

	static public String getEventType(COSName reason) {
		String result = (String) reasonToType.get(reason);
		if (result == null) {
			return "";
		}
		return result;
	}

	protected PDAdditionalActions(COSObject object) {
		super(object);
	}

	public void addAction(COSName trigger, PDAction action) {
		if ((trigger != null) && (action != null)) {
			PDAction a = getAction(trigger);
			if (a == null) {
				cosSetField(trigger, action.cosGetDict());
			} else {
				a.addNext(action);
			}
		}
	}

	public void clearAction(COSName trigger) {
		if (trigger != null) {
			cosRemoveField(trigger);
		}
	}

	protected void collectActions(PDAction current, List<PDAction> actions) {
		if (current == null) {
			return;
		}
		actions.add(current);
		List next = current.getNext();
		if (next != null) {
			for (Iterator i = next.iterator(); i.hasNext();) {
				PDAction nextAction = (PDAction) i.next();
				collectActions(nextAction, actions);
			}
		}
	}

	public PDAction getAction(COSName trigger) {
		COSObject cosObject = cosGetField(trigger);
		if (cosObject.isNull()) {
			return null;
		}
		return (PDAction) PDAction.META.createFromCos(cosObject);
	}

	public List<PDAction> getActions() {
		List<PDAction> actions = new ArrayList<PDAction>();
		for (Iterator i = cosGetDict().keySet().iterator(); i.hasNext();) {
			COSName trigger = ((COSObject) i.next()).asName();
			PDAction action = getAction(trigger);
			collectActions(action, actions);
		}
		return actions;
	}

	public void setAction(COSName trigger, PDAction action) {
		if (trigger == null) {
			return;
		}
		setFieldObject(trigger, action);
	}
}
