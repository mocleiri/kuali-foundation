package org.kuali.common.util.builder;

/** An abstract {@link Builder} implementation for easy subclassing. */
public abstract class AbstractBuilder<T> implements Builder<T> {
	/** Construct a new builder with valid defaults. */
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

	/**
	 * @throws IllegalArgumentException
	 *             if <code>{@link #isValid()}==false</code>
	 */
	private final void validate() {
		if (!isValid())
			throw new IllegalArgumentException();
	}

	/** Set properties to default values. */
	protected abstract void defaults();

	/** Create a {@code T} instance based on current properties. */
	protected abstract T construct();
}