/**
 * Copyright 2010-2012 The Kuali Foundation
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
	public static final String FORWARD_SLASH = "/";
	public static final char DOUBLE_QUOTE = '"';

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
}
