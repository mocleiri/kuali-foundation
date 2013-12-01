package org.kuali.common.util.builder;

public abstract class AbstractBuilder<T> implements Builder<T> {

	@Override
	public T build() {
		T instance = getInstance();
		validate(instance);
		return instance;
	}

	protected abstract T getInstance();

	protected abstract void validate(T instance);

}
