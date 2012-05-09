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
package de.intarsys.pdf.font;

import de.intarsys.pdf.cos.COSName;
import de.intarsys.pdf.encoding.GlyphNameMap;

/**
 * A special map from a character code to a character name.
 * 
 */
public class CMapBFCharNameMap extends CMapCharMap {

	private final COSName destination;

	private final int destinationCode;

	public CMapBFCharNameMap(byte[] source, COSName destination) {
		super(source);
		this.destination = destination;
		this.destinationCode = GlyphNameMap.Standard.getUnicode(destination
				.stringValue());
	}

	public COSName getDestination() {
		return destination;
	}

	@Override
	public char[] toChars(int codepoint) {
		if (source == codepoint) {
			return new char[] { (char) destinationCode };
		} else {
			return null;
		}
	}

	@Override
	public int toCID(int codepoint) {
		if (source == codepoint) {
			return destinationCode;
		} else {
			return 0;
		}
	}

	@Override
	public int toCodepoint(int cid) {
		if (cid == destinationCode) {
			return source;
		} else {
			return 0;
		}
	}
}
