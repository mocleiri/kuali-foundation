package org.kuali.common.util;

import org.apache.commons.lang.StringUtils;

public class Str {

	private static final String EMPTY_STRING = "";
	private static final String COMMA = ",";
	/**
	 * Split comma separated values into tokens, trimming as we go
	 */
	public static String[] splitAndTrimCSV(String csv) {
		return splitAndTrim(csv, COMMA);
	}

	/**
	 * Split the string trimming as we go
	 */
	public static String[] splitAndTrim(String s, String separator) {
		String[] tokens = StringUtils.split(s, separator);
		for (String token : tokens) {
			token = token.trim();
		}
		return tokens;
	}

	public static final String toDefault(String s, String defaultValue) {
		if (StringUtils.isBlank(s)) {
			return defaultValue;
		} else {
			return s;
		}
	}

	public static final String toEmpty(String s) {
		if (StringUtils.isBlank(s)) {
			return EMPTY_STRING;
		} else {
			return s;
		}
	}

}
