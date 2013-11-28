package org.kuali.common.util.builder;

/**
 * {@code Builder} API visible to callers.
 */
public interface Builder<T> {

	/**
	 * @return {@code true} iff current properties specify a valid {@code T} instance
	 */
	boolean isValid();

	/**
	 * @return a {@code T} instance based on current properties
	 * @throws IllegalArgumentException
	 *             if a valid {@code T} instance cannot be built
	 */
	T build();

	/**
	 * Reset all current properties to valid defaults.
	 */
	void reset();
}