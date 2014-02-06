package org.kuali.common.util.base;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isBlank;

import com.google.common.base.Optional;

/**
 * Strongly mimic's Guava's {@Preconditions} with a sensible default error message for common situations
 * 
 * <pre>
 * Guava:
 * String foo = checkArgument(!StringUtils.isBlank(foo), &quot;'%s' cannot be blank&quot;);
 * 
 * Kuali:
 * String foo = checkNotBlank(foo, &quot;foo&quot;);
 * </pre>
 */
public class Precondition {

	private static final String NOT_BLANK_MSG = "'%s' cannot be blank";
	private static final String MIN_MSG = "%s not allowed. '%s' must be greater than or equal to %s";

	/**
	 * Check that a String is not whitespace, empty ("") or null.
	 */
	public static String checkNotBlank(String arg, String argName) {
		checkArgument(!isBlank(arg), NOT_BLANK_MSG, argName);
		return arg;
	}

	/**
	 * If arg.isPresent(), check that the string it contains is not whitespace, empty ("") or null.
	 */
	public static Optional<String> checkNotBlank(Optional<String> arg, String argName) {
		if (arg.isPresent()) {
			checkArgument(!isBlank(arg.get()), argName);
		}
		return arg;
	}

	/**
	 * If arg.isPresent(), check that the Integer it contains is greater than or equal to min
	 */
	public static Optional<Integer> checkMin(Optional<Integer> arg, int min, String argName) {
		if (arg.isPresent()) {
			checkMin(arg.get(), min, argName);
		}
		return arg;
	}

	/**
	 * If arg.isPresent(), check that the Long it contains is greater than or equal to min
	 */
	public static Optional<Long> checkMin(Optional<Long> arg, long min, String argName) {
		if (arg.isPresent()) {
			checkMin(arg.get(), min, argName);
		}
		return arg;
	}

	/**
	 * Check that arg is greater than or equal to min.
	 */
	public static int checkMin(int arg, int min, String argName) {
		checkArgument(arg >= min, MIN_MSG, arg, min, argName);
		return arg;
	}

	/**
	 * Check that arg is greater than or equal to min.
	 */
	public static long checkMin(long arg, long min, String argName) {
		checkArgument(arg >= min, MIN_MSG, arg, min, argName);
		return arg;
	}

}
