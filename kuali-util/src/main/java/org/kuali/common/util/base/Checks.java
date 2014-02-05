package org.kuali.common.util.base;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class Checks {

	private static final String NOT_NULL_MSG = "'%s' cannot be null";
	private static final String NOT_BLANK_MSG = "'%s' cannot be blank";
	private static final String POSITIVE_MSG = "%s not allowed. '%s' must be positive";

	public static void notNull(Object arg, String name) {
		checkNotNull(arg, NOT_NULL_MSG, name);
	}

	public static void notBlank(String arg, String name) {
		checkArgument(isBlank(arg), NOT_BLANK_MSG, name);
	}

	public static void positive(int arg, String name) {
		checkArgument(arg > 0, POSITIVE_MSG, arg, name);
	}

}
