package org.kuali.common.util.builder;

import java.util.Arrays;

/** {@code FooBar} that supports validation, complete with builder. */
public class FooBar {
	private final int foo;
	private final String[] bar;

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

	public static Builder builder() {
		return new Builder();
	}

	public static Builder nullBarBuilder() {
		return new Builder().withFoo(30).withBar(null);
	}

	public static class Builder extends AbstractBuilder<FooBar> {
		public static final int FOO_DEFAULT = 0;
		public static final String[] BAR_DEFAULT = new String[] { "Hello, world!" };

		private int foo;
		private String[] bar;

		private Builder() {
		}

		public Builder withFoo(int foo) {
			this.foo = foo;
			return this;
		}

		public Builder withBar(String[] bar) {
			this.bar = bar;
			return this;
		}

		@Override
		public boolean isValid() {
			int fooMin = bar != null ? 0 : 30;
			int fooMax = bar != null ? 45 : 60;
			return fooMin <= foo && foo <= fooMax;
		}

		@Override
		protected void defaults() {
			withFoo(FOO_DEFAULT).withBar(BAR_DEFAULT);
		}

		@Override
		protected FooBar construct() {
			return new FooBar(this);
		}
	}
}