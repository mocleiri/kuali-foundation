package org.kuali.common.devops.logic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Exceptions {

	public static <T extends Exception> T create(Class<T> type, String msg) {
		return create(type, msg, (Object[]) null);
	}

	public static <T extends Exception> T create(Class<T> type, String msg, Object... args) {
		try {
			Constructor<T> constructor = type.getConstructor(String.class);
			return constructor.newInstance(getExceptionMessage(msg, args));
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException(e);
		} catch (InstantiationException e) {
			throw new IllegalStateException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		} catch (InvocationTargetException e) {
			throw new IllegalStateException(e);
		}
	}

	public static IllegalArgumentException iae(String msg) {
		return iae(msg, (Object[]) null);
	}

	public static IllegalArgumentException iae(String msg, Object... args) {
		return create(IllegalArgumentException.class, msg, args);
	}

	protected static String getExceptionMessage(String msg, Object... args) {
		if (args == null || args.length == 0) {
			return msg;
		} else {
			return String.format(msg, args);
		}
	}

}
