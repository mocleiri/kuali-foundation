package org.kuali.common.util.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** Conditionally settable {@code baz}, corrected! */
public class FooBar {
	private final int foo;
	private final List<String> bar;
	private final Boolean baz;

	private FooBar(Builder<?> builder, Boolean baz) {
		this.foo = builder.foo;
		this.bar = Collections.unmodifiableList(new ArrayList<String>(builder.bar));
		this.baz = baz;
	}

	public int getFoo() {
		return foo;
	}

	public List<String> getBar() {
		return bar;
	}

	public Boolean getBaz() {
		return baz;
	}

	public static DefaultBuilder builder() {
		return new DefaultBuilder();
	}

	public static NullBarBuilder nullBarBuilder() {
		return new NullBarBuilder();
	}

	public static abstract class Builder<B extends Builder<B>> extends AbstractBuilder<B, FooBar> {
		private int foo;
		private List<String> bar;

		private Builder() {
		}

		public final B withFoo(int foo) {
			this.foo = foo;
			return getThis();
		}

		public final B withBar(List<String> bar) {
			this.bar = bar;
			return getThis();
		}

		@Override
		public final boolean isValid() {
			int fooMin = bar != null ? 0 : 30;
			int fooMax = bar != null ? 45 : 60;
			return fooMin <= foo && foo <= fooMax;
		}
	}

	public static class DefaultBuilder extends Builder<DefaultBuilder> {
		public static final int FOO_DEFAULT = 0;
		public static final List<String> BAR_DEFAULT = Collections.unmodifiableList(Arrays.asList(new String[] { "Hello, world!" }));

		private DefaultBuilder() {
		}

		@Override
		protected void defaults() {
			withFoo(FOO_DEFAULT).withBar(BAR_DEFAULT);
		}

		@Override
		protected FooBar construct() {
			return new FooBar(getThis(), null);
		}
	}

	public static class NullBarBuilder extends Builder<NullBarBuilder> {
		public static final int FOO_DEFAULT = 30;
		public static final List<String> BAR_DEFAULT = null;
		public static final Boolean BAZ_DEFAULT = Boolean.TRUE;

		private Boolean baz;

		private NullBarBuilder() {
		}

		public NullBarBuilder withBaz(Boolean baz) {
			this.baz = baz;
			return getThis();
		}

		@Override
		protected void defaults() {
			withFoo(FOO_DEFAULT).withBar(BAR_DEFAULT).withBaz(BAZ_DEFAULT);
		}

		@Override
		protected FooBar construct() {
			return new FooBar(getThis(), baz);
		}
	}
}