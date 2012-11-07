package org.kuali.common.util;

import org.apache.commons.lang.StringUtils;

public class Str {

	public static final String EMPTY_STRING = "";

	public static final String toDefault(String s, String defaultValue) {
		if (StringUtils.isBlank(s)) {
			return defaultValue;
		} else {
			return s;
		}
	}

	public static final String toEmpty(Object o) {
		if (o == null) {
			return EMPTY_STRING;
		}
		String s = o.toString();
		if (StringUtils.isBlank(s)) {
			return EMPTY_STRING;
		} else {
			return s;
		}
	}

}
