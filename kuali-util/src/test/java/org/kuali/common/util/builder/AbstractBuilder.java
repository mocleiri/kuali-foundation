package org.kuali.common.util.builder;

import org.kuali.common.util.Assert;

public abstract class AbstractBuilder<B extends AbstractBuilder<B, T>, T> implements Builder<T> {

	protected AbstractBuilder() {
		reset();
	}

	@Override
	public final T build() {
		T instance = construct(); // Create a new instance
		validate(instance); // Ensure that the newly created instance is valid
		reset(); // Reset builder properties to their defaults
		return instance; // Return the newly created instance
	}

	@Override
	public final void reset() {
		defaults();
		Assert.isTrue(isValid(), "invalid defaults");
	}

	private final void validate(T instance) {
		if (!isValid(instance)) {
			throw new IllegalArgumentException();
		}
	}

	protected abstract boolean isValid(T instance);

	protected abstract void defaults();

	protected abstract T construct();

	/** @return {@code this} cast to a specific subtype */
	@SuppressWarnings("unchecked")
	protected B getThis() {
		return (B) this;
	}
}