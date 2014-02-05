package org.kuali.common.util.base;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class Checks {

	private static final String NOT_NULL_MSG = "'%s' cannot be null";
	private static final String NOT_BLANK_MSG = "'%s' cannot be blank";
	private static final String IS_POSITIVE_MSG = "%s not allowed. '%s' must be positive";

	public static void notNull(Object arg, String name) {
		checkNotNull(arg, NOT_NULL_MSG, name);
	}

	public static void notBlank(String arg, String name) {
		checkArgument(isBlank(arg), NOT_BLANK_MSG, name);
	}

	/**
	 * Ass
	 * @param arg
	 * @param name
	 */
	public static void isPositive(int arg, String name) {
		checkArgument(arg > 0, IS_POSITIVE_MSG, arg, name);
	}

}
