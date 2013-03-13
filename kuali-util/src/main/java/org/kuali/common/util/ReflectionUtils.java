package org.kuali.common.util;

public class ReflectionUtils extends org.springframework.util.ReflectionUtils {

	public static Object newInstance(String className) {
		try {
			Class<?> clazz = Class.forName(className);
			return newInstance(clazz);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
	}

	public static <T> T newInstance(Class<T> instanceClass) {
		try {
			return (T) instanceClass.newInstance();
		} catch (IllegalAccessException e) {
			throw new IllegalStateException("Unexpected error", e);
		} catch (InstantiationException e) {
			throw new IllegalStateException("Unexpected error", e);
		}
	}

}
