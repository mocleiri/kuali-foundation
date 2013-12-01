package org.kuali.common.util.builder;

/**
 * {@code Builder} API visible to callers.
 */
public interface ExampleBuilder<T> {

	/**
	 * @return Inspect the current builder properties and to create a validation result object
	 */
	ValidationResult validate();

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