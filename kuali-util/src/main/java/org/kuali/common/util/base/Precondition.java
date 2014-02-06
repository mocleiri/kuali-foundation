package org.kuali.common.util.base;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isBlank;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

/**
 * Strongly mimic's Guava's {@Preconditions} but with sensible default error messages
 */
public class Precondition {

	private static final String NOT_NULL_MSG = "'%s' cannot be null";
	private static final String NOT_BLANK_MSG = "'%s' cannot be blank";
	private static final String MIN_MSG = "%s not allowed. '%s' must be greater than or equal to %s";

	/**
	 * Ensures that an object reference passed as a parameter to the calling method is not null.
	 */
	public static <T> T checkNotNull(T arg, String name) {
		return Preconditions.checkNotNull(arg, NOT_NULL_MSG, name);
	}

	/**
	 * Checks that a String is not whitespace, empty ("") or null.
	 */
	public static String checkNotBlank(String arg, String name) {
		checkNotNull(arg, name);
		checkArgument(!isBlank(arg), NOT_BLANK_MSG, name);
		return arg;
	}

	/**
	 * If Optional.isPresent(), checks that the string it contains is not whitespace, empty ("") or null.
	 */
	public static Optional<String> checkNotBlank(Optional<String> arg, String name) {
		if (arg.isPresent()) {
			checkArgument(!isBlank(arg.get()), name);
		}
		return arg;
	}

	/**
	 * Assert that arg is greater than or equal to min.
	 */
	public static int checkMin(int arg, int min, String name) {
		checkArgument(arg >= min, MIN_MSG, arg, min, name);
		return arg;
	}

	/**
	 * Assert that arg is greater than or equal to min.
	 */
	public static long checkMin(long arg, long min, String name) {
		checkArgument(arg >= min, MIN_MSG, arg, min, name);
		return arg;
	}

	/**
	 * Assert that arg is greater than or equal to zero.
	 */
	public static int checkNotNegative(int arg, String name) {
		return checkMin(arg, 0, name);
	}

	/**
	 * Assert that arg is greater than zero. Zero does not count as positive.
	 */
	public static int checkPositive(int arg, String name) {
		return checkMin(arg, 1, name);
	}

	/**
	 * Assert that arg is greater than zero. Zero does not count as positive.
	 */
	public static long checkPositive(long arg, String name) {
		return checkMin(arg, 1, name);
	}

}
