package org.kuali.common.util.base;


/**
 * <p>
 * Create {@code IllegalStateException's} and {@code IllegaArgumentException's} with richly formatted error messages.
 * </p>
 * 
 * Typical usage:
 * 
 * <pre>
 * try {
 *   ...
 * } catch (FileNotFoundException e) {
 *   throw Exceptions.illegalArg(e, &quot;File [%s] does not exist&quot; file);
 * }
 * </pre>
 */
public class Exceptions {

	public static IllegalStateException illegalState(Throwable cause) {
		return new IllegalStateException(cause);
	}

	public static IllegalStateException illegalState(String msg) {
		return new IllegalStateException(msg);
	}

	public static IllegalStateException illegalState(String msg, Object... args) {
		return new IllegalStateException(formattedMessage(msg, args));
	}

	public static IllegalStateException illegalState(Throwable cause, String msg, Object... args) {
		return new IllegalStateException(formattedMessage(msg, args), cause);
	}

	public static IllegalArgumentException illegalArg(Throwable cause) {
		return new IllegalArgumentException(cause);
	}

	public static IllegalArgumentException illegalArg(String msg) {
		return new IllegalArgumentException(msg);
	}

	public static IllegalArgumentException illegalArg(String msg, Object... args) {
		return new IllegalArgumentException(formattedMessage(msg, args));
	}

	public static IllegalArgumentException illegalArg(Throwable cause, String msg, Object... args) {
		return new IllegalArgumentException(formattedMessage(msg, args), cause);
	}

	protected static String formattedMessage(String msg, Object... args) {
		if (args == null || args.length == 0) {
			return msg;
		} else {
			return String.format(msg, args);
		}
	}

}
