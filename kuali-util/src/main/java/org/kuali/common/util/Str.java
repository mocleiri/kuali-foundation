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

	/**
	 * Convert <code>chars</code> to a <code>String</code>
	 */
	public static String toString(char[] chars) {
		if (chars == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (char c : chars) {
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * Convert dots to forward slashes and trim.
	 */
	public static String getPath(String s) {
		return StringUtils.trim(StringUtils.replace(s, ".", "/"));
	}

	/**
	 * Surround the string with double quotes.
	 */
	public static String quote(String s) {
		return s == null ? null : '"' + s + '"';
	}

	/**
	 * Split comma separated values into tokens.
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
		for (String token : tokens) {
			token = trim ? StringUtils.trim(token) : token;
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
