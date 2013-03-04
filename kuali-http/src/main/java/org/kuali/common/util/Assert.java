package org.kuali.common.util;

import org.apache.commons.lang3.StringUtils;

public abstract class Assert extends org.springframework.util.Assert {

	public static void isFalse(boolean condition) {
		isTrue(!condition);
	}

	public static void notBlank(String... strings) {
		for (String string : strings) {
			isFalse(StringUtils.isBlank(string));
		}
	}

}
