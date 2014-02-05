package org.kuali.common.util.base;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class Assertions {

	private static final String NOT_NULL_MSG = "'%s' cannot be null";
	private static final String NOT_BLANK_MSG = "'%s' cannot be blank";
	private static final String IS_POSITIVE_MSG = "%s not allowed. '%s' must be positive";

	public static <T> T assertNotNull(T arg, String name) {
		return checkNotNull(arg, NOT_NULL_MSG, name);
	}

	public static String assertNotBlank(String arg, String name) {
		checkArgument(isBlank(arg), NOT_BLANK_MSG, name);
		return arg;
	}

	/**
	 * Assert that arg is greater than zero. Zero itself does not count as positive.
	 */
	public static int assertPositive(int arg, String name) {
		checkArgument(arg > 0, IS_POSITIVE_MSG, arg, name);
		return arg;
	}

}
