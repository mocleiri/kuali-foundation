package org.kuali.common.devops.logic;

import java.lang.reflect.Constructor;

public class Exceptions {

	public static IllegalStateException ise(String msg) {
		return ise(msg, (Object[]) null);
	}

	public static IllegalStateException ise(String msg, Object... args) {
		return create(IllegalStateException.class, msg, args);
	}

	public static IllegalStateException ise(Throwable cause, String msg, Object... args) {
		return create(IllegalStateException.class, cause, msg, args);
	}

	public static IllegalArgumentException iae(String msg) {
		return iae(msg, (Object[]) null);
	}

	public static IllegalArgumentException iae(String msg, Object... args) {
		return create(IllegalArgumentException.class, msg, args);
	}

	public static IllegalArgumentException iae(Throwable cause, String msg, Object... args) {
		return create(IllegalArgumentException.class, cause, msg, args);
	}

	protected static String formatMessage(String msg, Object... args) {
		if (args == null || args.length == 0) {
			return msg;
		} else {
			return String.format(msg, args);
		}
	}

	public static <T extends Exception> T create(Class<T> type, String msg) {
		return create(type, msg, (Object[]) null);
	}

	public static <T extends Exception> T create(Class<T> type, Throwable cause, String msg, Object... args) {
		Class<?>[] parameterTypes = { String.class, Throwable.class };
		Object[] initArgs = { formatMessage(msg, args), cause };
		return create(type, parameterTypes, initArgs);
	}

	public static <T extends Exception> T create(Class<T> type, String msg, Object... args) {
		Class<?>[] parameterTypes = { String.class };
		Object[] initArgs = { formatMessage(msg, args) };
		return create(type, parameterTypes, initArgs);
	}

	public static <T extends Exception> T create(Class<T> type, Class<?>[] parameterTypes, Object[] initArgs) {
		try {
			Constructor<T> constructor = type.getConstructor(parameterTypes);
			return constructor.newInstance(initArgs);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
