package org.kuali.common.util;

import org.apache.commons.lang.StringUtils;

public class Str {

	public static final String EMPTY_STRING = "";
	public static final String COMMA = ",";
	public static final String SPACE = " ";
	public static final String CR = "\r";
	public static final String LF = "\n";

	/**
	 * Surround the string with double quotes
	 */
	public static String quote(String s) {
		return '"' + s + '"';
	}

	/**
	 * Split comma separated values into tokens
	 */
	public static String[] splitCSV(String csv, boolean trim) {
		return split(csv, COMMA, trim);
	}

	/**
	 * Split comma separated values into tokens, trimming as we go
	 */
	public static String[] splitAndTrimCSV(String csv) {
		return splitCSV(csv, true);
	}

	/**
	 * Split the string into tokens, trimming as we go
	 */
	public static String[] splitAndTrim(String s, String separator) {
		return split(s, separator, true);
	}

	/**
	 * Split the string into tokens
	 */
	public static String[] split(String s, String separator, boolean trim) {
		String[] tokens = StringUtils.split(s, separator);
		for (String token : tokens) {
			token = trim ? token.trim() : token;
		}
		return tokens;
	}

	/**
	 * If the string is blank, return default value, otherwise return the original string
	 */
	public static final String toDefault(String s, String defaultValue) {
		if (StringUtils.isBlank(s)) {
			return defaultValue;
		} else {
			return s;
		}
	}

	/**
	 * If the string is blank, return the empty string otherwise return the original string.
	 */
	public static final String toEmpty(String s) {
		if (StringUtils.isBlank(s)) {
			return EMPTY_STRING;
		} else {
			return s;
		}
	}

	/**
	 * Replace all carriage returns and linefeeds with spaces
	 */
	public static final String flatten(String s) {
		return flatten(s, SPACE, SPACE);
	}

	/**
	 * Replace any carriage returns with <code>cr</code> and replace any linefeeds with <code>lf</code>.
	 */
	public static final String flatten(String s, String cr, String lf) {
		if (s == null) {
			return null;
		} else {
			return s.replace(CR, cr).replace(LF, lf);
		}
	}

}
