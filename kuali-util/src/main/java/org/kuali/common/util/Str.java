package org.kuali.common.util;

import org.apache.commons.lang.StringUtils;

public class Str {

	private static final String EMPTY_STRING = "";
	private static final String COMMA = ",";

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
	public static String[] splitAndTrim(String s, String separator, boolean trim) {
		return splitAndTrim(s, separator, true);
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

}
