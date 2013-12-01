package org.kuali.common.util.builder;

public abstract class AbstractBuilder<B extends AbstractBuilder<B, T>, T> implements Builder<T> {

	protected AbstractBuilder() {
		reset();
	}

	@Override
	public final T build() {
		validate();
		T newbie = construct();
		reset();
		return newbie;
	}

	@Override
	public final void reset() {
		defaults();
		validate();
	}

	private final void validate() {
		if (!isValid()) {
			throw new IllegalArgumentException();
		}
	}

	protected abstract void defaults();

	protected abstract T construct();

	/** @return {@code this} cast to a specific subtype */
	@SuppressWarnings("unchecked")
	protected B getThis() {
		return (B) this;
	}
}