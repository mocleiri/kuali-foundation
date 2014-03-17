package org.kuali.common.util.base;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isBlank;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

/**
 * Strongly mimic's Guava's {@code Preconditions} class with a sensible default error message for common situations
 * 
 * <pre>
 * {@code Guava:}
 * checkArgument(!StringUtils.isBlank(foo), &quot;'foo' cannot be blank&quot;);
 * this.foo = foo;
 * 
 * {@code Kuali:}
 * this.foo = checkNotBlank(foo, &quot;foo&quot;);
 * </pre>
 */
public class Precondition {

	private static final String NOT_NULL_MSG = "'%s' cannot be null";
	private static final String NOT_BLANK_MSG = "'%s' cannot be blank";
	private static final String MIN_MSG = "%s not allowed. '%s' must be greater than or equal to %s";
	private static final String MAX_MSG = "%s not allowed. '%s' must be less than or equal to %s";
	private static final String ARG_NAME = "argName";

	/**
	 * Ensures that an object reference passed as an argument is not null
	 * 
	 * @param arg
	 *            an object reference passed as an argument
	 * @param argName
	 *            the name of the argument
	 * 
	 * @return the non-null object reference that was validated
	 * 
	 * @throws NullPointerException
	 *             If arg is null. The exception message contains the name of the argument that was null
	 * @throws IllegalArgumentException
	 *             If argName is blank
	 */
	public static <T> T checkNotNull(T arg, String argName) {
		checkNotBlank(argName, ARG_NAME);
		return Preconditions.checkNotNull(arg, NOT_NULL_MSG, argName);
	}

	/**
	 * Ensures that a String passed as an argument is not not whitespace, empty ("") or null
	 * 
	 * @param arg
	 *            a String passed as an argument
	 * @param argName
	 *            the name of the argument
	 * 
	 * @return the non-blank String that was validated
	 * 
	 * @throws IllegalArgumentException
	 *             If arg is blank. The exception message contains the name of the argument that was blank
	 * @throws IllegalArgumentException
	 *             If argName is blank
	 */
	public static String checkNotBlank(String arg, String argName) {
		checkArgument(!isBlank(argName), NOT_BLANK_MSG, ARG_NAME);
		checkArgument(!isBlank(arg), NOT_BLANK_MSG, argName);
		return arg;
	}

	/**
	 * Ensures that an {@code Optional<String>} passed as an argument does not contain a string that is whitespace or empty ("").
	 * 
	 * @param arg
	 *            an {@code Optional<String>} passed as an argument
	 * @param argName
	 *            the name of the argument
	 * 
	 * @return the non-blank {@code Optional<String>} that was validated
	 * 
	 * @throws IllegalArgumentException
	 *             If arg is blank. The exception message contains the name of the argument that was blank
	 * @throws IllegalArgumentException
	 *             If argName is blank
	 */
	public static Optional<String> checkNotBlank(Optional<String> arg, String argName) {
		if (arg.isPresent()) {
			checkNotBlank(arg.get(), argName);
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
	 * Check that arg is less than or equal to max
	 */
	public static int checkMax(int arg, int max, String argName) {
		checkNotBlank(argName, ARG_NAME);
		checkArgument(arg <= max, MAX_MSG, arg, argName, max);
		return arg;
	}

	/**
	 * Check that arg is greater than or equal to min.
	 */
	public static int checkMin(int arg, int min, String argName) {
		checkNotBlank(argName, ARG_NAME);
		checkArgument(arg >= min, MIN_MSG, arg, argName, min);
		return arg;
	}

	/**
	 * Check that arg is greater than or equal to min.
	 */
	public static long checkMin(long arg, long min, String argName) {
		checkNotBlank(argName, ARG_NAME);
		checkArgument(arg >= min, MIN_MSG, arg, argName, min);
		return arg;
	}

}
