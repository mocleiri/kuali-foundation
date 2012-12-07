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

import org.apache.commons.lang3.StringUtils;

/**
 * Operations on <code>String</code> that are <code>null</code> safe
 */
public class Str {

	public static final String EMPTY_STRING = "";
	public static final String COMMA = ",";
	public static final String SPACE = " ";
	public static final String CR = "\r";
	public static final String LF = "\n";
	public static final String DOT = ".";
	public static final String FORWARD_SLASH = "/";

	/**
	 * Combine <code>tokens</code> into a <code>String</code>
	 */
	public static String toString(String[] tokens) {
		if (tokens == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (String token : tokens) {
			if (token != null) {
				sb.append(token);
			}
		}
		return sb.toString();
	}

	/**
	 * Convert dots to forward slashes and trim.
	 */
	public static String getPath(String s) {
		return StringUtils.trim(StringUtils.replace(s, DOT, FORWARD_SLASH));
	}

	/**
	 * Surround the string with double quotes.
	 */
	public static String quote(String s) {
		return s == null ? null : '"' + s + '"';
	}

	/**
	 * Split comma separated values into tokens, optionally trimming the tokens.
	 */
	public static String[] splitCSV(String csv, boolean trim) {
		return split(csv, COMMA, trim);
	}

	/**
	 * Split comma separated values into tokens, trimming as we go.
	 */
	public static String[] splitAndTrimCSV(String csv) {
		return splitCSV(csv, true);
	}

	/**
	 * Split the string into tokens using the indicated separator, trimming as we go.
	 */
	public static String[] splitAndTrim(String s, String separatorChars) {
		return split(s, separatorChars, true);
	}

	/**
	 * Split the string into tokens using the indicated separator chars, optionally trimming the tokens.
	 */
	public static String[] split(String s, String separatorChars, boolean trim) {
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
	 * Replace carriage returns and linefeeds with spaces.
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
