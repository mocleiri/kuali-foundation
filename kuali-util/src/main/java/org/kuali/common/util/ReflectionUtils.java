package org.kuali.common.util;

public class ReflectionUtils extends org.springframework.util.ReflectionUtils {

	public static Class<?> getClass(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static <T> T newInstance(String className) {
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) getClass(className);
		return (T) newInstance(clazz);
	}

	public static <T> T newInstance(Class<T> instanceClass) {
		try {
			return (T) instanceClass.newInstance();
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException("Unexpected error", e);
		} catch (InstantiationException e) {
			throw new IllegalArgumentException("Unexpected error", e);
		}
	}

}
