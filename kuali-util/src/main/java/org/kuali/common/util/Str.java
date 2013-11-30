/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.util;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * Operations on <code>String</code> that are <code>null</code> safe
 */
public class Str {

	public static final String EMPTY_STRING = "";
	public static final String UTF8 = "UTF-8";
	public static final String COMMA = ",";
	public static final String SPACE = " ";
	public static final String CR = "\r";
	public static final String LF = "\n";
	public static final String DOT = ".";
	public static final String COLON = ":";
	public static final String FORWARD_SLASH = "/";
	public static final char DOUBLE_QUOTE = '"';
	public static final String CDATA_PREFIX = "<![CDATA[";
	public static final String CDATA_SUFFIX = "]]>";
	public static final String[] EMPTY_ARRAY = new String[0];

	private static final String CONCEALED_PREFIX = "CNC(";
	private static final String CONCEALED_SUFFIX = ")";

	/**
	 * <p>
	 * A trivial algorithm to conceal <code>text</code>. Can be reversed using <code>reveal()</code>. Do <b>NOT</b> use this method in an attempt to obscure sensitive data. The
	 * algorithm is completely trivial and exceedingly simple to reverse engineer. Not to mention the <code>reveal()</code> method can reproduce the original string without
	 * requiring any secret knowledge.
	 * </p>
	 * <p>
	 * Don't use this method for anything more serious than concealing the combination to the cookie jar in the pantry from your 7 year old child. You have been warned :).
	 * </p>
	 * 
	 * @see reveal
	 */
	public static final String conceal(String text) {
		Assert.noBlanks(text);
		if (isConcealed(text)) {
			return text;
		}
		Assert.notConcealed(text);
		char[] chars = text.toCharArray();
		StringBuilder sb = new StringBuilder();
		sb.append(CONCEALED_PREFIX);
		for (char c : chars) {
			sb.append(Ascii.flip(c));
		}
		sb.append(CONCEALED_SUFFIX);
		return sb.toString();
	}

	/**
	 * Reveal the original contents of a string concealed by the <code>conceal</code> method.
	 * 
	 * @see conceal
	 */
	public static final String reveal(String text) {
		Assert.noBlanks(text);
		if (!isConcealed(text)) {
			return text;
		}
		Assert.concealed(text);
		int start = CONCEALED_PREFIX.length();
		int end = text.length() - CONCEALED_SUFFIX.length();
		String substring = text.substring(start, end);
		char[] chars = substring.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char c : chars) {
			sb.append(Ascii.flip(c));
		}
		return sb.toString();
	}

	public static final boolean isConcealed(String s) {
		Assert.noBlanks(s);
		return StringUtils.startsWith(s, CONCEALED_PREFIX) && StringUtils.endsWith(s, CONCEALED_SUFFIX);
	}

	/**
	 * If <code>strings</code> are <code>null</code> return <code>EMPTY_ARRAY</code>, otherwise return <code>strings</code>.
	 */
	public static final String[] toEmptyArray(String[] strings) {
		if (strings == null) {
			return EMPTY_ARRAY;
		} else {
			return strings;
		}
	}

	/**
	 * Convert the tokens into a string delimited by the colon "<code>:</code>" character
	 * 
	 * <pre>
	 *   "foo","bar" ,"baz"  -> foo:bar:baz
	 *   "foo", null ,"baz"  -> foo::baz
	 *   "foo", ""   ,"baz"  -> foo::baz
	 *   "foo", null , null  -> foo::
	 *    null,"bar" , null  -> :bar:
	 * </pre>
	 */
	public static final String getId(String... tokens) {
		if (tokens == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tokens.length; i++) {
			if (i != 0) {
				sb.append(COLON);
			}
			sb.append(StringUtils.trimToEmpty(tokens[i]));
		}
		return sb.toString();
	}

	/**
	 * Turn the string into CDATA - http://en.wikipedia.org/wiki/CDATA
	 */
	public static final String cdata(String s) {
		if (s == null) {
			return null;
		} else {
			return CDATA_PREFIX + s + CDATA_SUFFIX;
		}
	}

	/**
	 * If <code>s</code> ends with <code>suffix</code>, remove it
	 */
	public static final String removeSuffix(String s, String suffix) {
		if (StringUtils.endsWith(s, suffix)) {
			int end = StringUtils.length(s) - StringUtils.length(suffix);
			return StringUtils.substring(s, 0, end);
		} else {
			return s;
		}
	}

	/**
	 * If s is null return "" otherwise return s
	 */
	public static final String toEmpty(String s) {
		if (s == null) {
			return "";
		} else {
			return s;
		}
	}

	public static final String getString(byte[] bytes, String encoding) {
		if (bytes == null) {
			return null;
		}
		if (encoding == null) {
			return new String(bytes);
		}
		try {
			return new String(bytes, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static final byte[] getUTF8Bytes(String s) {
		if (s == null) {
			return null;
		} else {
			return getBytes(s, UTF8);
		}
	}

	public static final byte[] getBytes(String s, String encoding) {
		if (s == null) {
			return null;
		}
		if (encoding == null) {
			return s.getBytes();
		}
		try {
			return s.getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static final boolean contains(List<String> tokens, String value, boolean caseSensitive) {
		for (String token : tokens) {
			if (equals(token, value, caseSensitive)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean equals(String s1, String s2, boolean caseSensitive) {
		if (caseSensitive) {
			return StringUtils.equals(s1, s2);
		} else {
			return StringUtils.equalsIgnoreCase(s1, s2);
		}
	}

	/**
	 * Combine <code>tokens</code> into a <code>String</code>
	 */
	public static final String toString(String[] tokens) {
		if (tokens == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (String token : tokens) {
			sb.append(token);
		}
		return sb.toString();
	}

	/**
	 * Convert dots to forward slashes and trim.
	 */
	public static final String getPath(String s) {
		return StringUtils.trim(StringUtils.replace(s, DOT, FORWARD_SLASH));
	}

	/**
	 * Surround the string with double quotes.
	 */
	public static final String quote(String s) {
		return s == null ? null : DOUBLE_QUOTE + s + DOUBLE_QUOTE;
	}

	/**
	 * Split comma separated values into tokens, optionally trimming the tokens.
	 */
	public static final String[] splitCSV(String csv, boolean trim) {
		return split(csv, COMMA, trim);
	}

	/**
	 * Split comma separated values into tokens, trimming as we go.
	 */
	public static final String[] splitAndTrimCSV(String csv) {
		return splitCSV(csv, true);
	}

	/**
	 * Split the string into tokens using the indicated separator, trimming as we go.
	 */
	public static final String[] splitAndTrim(String s, String separatorChars) {
		return split(s, separatorChars, true);
	}

	/**
	 * Split the string into tokens using the indicated separator chars, optionally trimming the tokens.
	 */
	public static final String[] split(String s, String separatorChars, boolean trim) {
		String[] tokens = StringUtils.split(s, separatorChars);
		if (tokens == null) {
			return null;
		}
		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = trim ? StringUtils.trim(tokens[i]) : tokens[i];
		}
		return tokens;
	}

	/**
	 * Replace carriage returns and linefeeds with a space
	 */
	public static final String flatten(String s) {
		return flatten(s, SPACE, SPACE);
	}

	/**
	 * Replace carriage returns with <code>cr</code> and linefeeds with <code>lf</code>.
	 */
	public static final String flatten(String s, String cr, String lf) {
		return StringUtils.replace(StringUtils.replace(s, CR, cr), LF, lf);
	}

	/**
	 * Replace <code>cr</code> with carriage return and <code>lf</code> with linefeed.
	 */
	public static final String inflate(String s, String cr, String lf) {
		return StringUtils.replace(StringUtils.replace(s, cr, CR), lf, LF);
	}
}
