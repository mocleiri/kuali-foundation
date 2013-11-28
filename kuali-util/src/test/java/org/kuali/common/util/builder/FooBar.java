package org.kuali.common.util.builder;

import java.util.Arrays;

public class FooBar {
	private final int foo;
	private final String[] bar;

	/**
	 * Constructs a valid {@code FooBar} instance.
	 * 
	 * ...doc specifying preconditions here...
	 * 
	 * @throw IllegalArgumentException if a valid instance cannot be created based on provided values
	 */
	public FooBar(int foo, String[] bar) {
		int fooMin = bar != null ? 0 : 30;
		int fooMax = bar != null ? 45 : 60;
		if (foo < fooMin || foo > fooMax) {
			throw new IllegalArgumentException();
		}
		this.foo = foo;
		this.bar = copy(bar);
	}

	public int getFoo() {
		return foo;
	}

	public String[] getBar() {
		return copy(bar);
	}

	/** Convenience method that returns a copy of {@code array}. */
	private static String[] copy(String[] array) {
		return Arrays.copyOf(array, array.length);
	}
}