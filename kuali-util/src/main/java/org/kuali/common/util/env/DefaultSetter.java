package org.kuali.common.util.env;

import java.lang.reflect.Field;

public class DefaultSetter<T> implements Setter<T> {

	@Override
	public void set(T instance, Field field, Object value) {
		try {
			field.set(instance, value);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}
}
