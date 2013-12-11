package org.kuali.common.util.builder;

import java.lang.reflect.Field;

import org.kuali.common.util.env.Settable;

public abstract class AbstractBuilder<T> implements Builder<T>, Settable<T> {

	@Override
	public final T build() {
		T instance = getInstance();
		validate(instance);
		return instance;
	}

	protected abstract T getInstance();

	protected abstract void validate(T instance);

	@Override
	public void set(Field field, Object value) {
		try {
			field.set(this, value);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

}
