package org.kuali.common.util;

public class ReflectionUtils extends org.springframework.util.ReflectionUtils {

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
