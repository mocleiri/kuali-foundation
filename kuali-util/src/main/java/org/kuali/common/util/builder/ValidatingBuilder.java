package org.kuali.common.util.builder;

public abstract class ValidatingBuilder<T> implements Builder<T> {

	@Override
	public final T build() {
		T instance = getInstance();
		validate(instance);
		return instance;
	}

	protected abstract T getInstance();

	protected abstract void validate(T instance);

}
