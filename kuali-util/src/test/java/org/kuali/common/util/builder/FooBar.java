package org.kuali.common.util.builder;

import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

/** Conditionally settable {@code baz}, corrected! */
public class FooBar {

	private final int foo;
	private final List<String> bar;
	private final Optional<Boolean> baz;

	private FooBar(Builder<?> builder, Optional<Boolean> baz) {
		this.foo = builder.foo;
		this.bar = builder.bar;
		this.baz = baz;
	}

	public int getFoo() {
		return foo;
	}

	public List<String> getBar() {
		return bar;
	}

	public Optional<Boolean> getBaz() {
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
			if (bar == null) {
				return false;
			}
			int fooMin = bar.size() != 0 ? 0 : 30;
			int fooMax = bar.size() != 0 ? 45 : 60;
			return foo >= fooMin && foo <= fooMax;
		}
	}

	public static class DefaultBuilder extends Builder<DefaultBuilder> {

		public static final int FOO_DEFAULT = 0;
		public static final List<String> BAR_DEFAULT = ImmutableList.of("Hello World");

		private DefaultBuilder() {
		}

		@Override
		protected void defaults() {
			withFoo(FOO_DEFAULT).withBar(BAR_DEFAULT);
		}

		@Override
		protected FooBar construct() {
			return new FooBar(getThis(), Optional.<Boolean> absent());
		}
	}

	public static class NullBarBuilder extends Builder<NullBarBuilder> {

		public static final int FOO_DEFAULT = 30;
		public static final List<String> BAR_DEFAULT = null;
		public static final boolean BAZ_DEFAULT = true;

		private Optional<Boolean> baz = Optional.of(BAZ_DEFAULT);

		private NullBarBuilder() {
		}

		public NullBarBuilder withBaz(boolean baz) {
			this.baz = Optional.of(baz);
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