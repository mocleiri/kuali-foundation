package org.kuali.common.util.builder;

import java.util.Arrays;

public class FooBar {

	private final int foo;
	private final String[] bar;

	/** Prevent direct instantiation. */
	private FooBar(Builder builder) {
		this.foo = builder.foo;
		this.bar = copy(builder.bar);
	}

	public int getFoo() {
		return foo;
	}

	public String[] getBar() {
		return copy(bar);
	}

	private static String[] copy(String[] array) {
		return Arrays.copyOf(array, array.length);
	}

	public static class Builder {

		private int foo;
		private String[] bar;

		public Builder withFoo(int foo) {
			this.foo = foo;
			return this;
		}

		public Builder withBar(String[] bar) {
			this.bar = bar;
			return this;
		}

		/** @return {@code true} if a valid {@code FooBar} instance can be built */
		public boolean isValid() {
			int fooMin = bar != null ? 0 : 30;
			int fooMax = bar != null ? 45 : 60;
			return fooMin <= foo && foo <= fooMax;
		}

		public void validate() {
			if (!isValid())
				throw new IllegalArgumentException();
		}

		public FooBar build() {
			validate();
			return new FooBar(this);
		}
	}
}