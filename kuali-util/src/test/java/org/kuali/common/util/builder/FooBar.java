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

	public static EmptyBarBuilder nullBarBuilder() {
		return new EmptyBarBuilder();
	}

	public static abstract class Builder<B extends Builder<B>> extends AbstractBuilder<B, FooBar> {

		private int foo;
		private List<String> bar;

		private Builder() {
		}

		public final B foo(int foo) {
			this.foo = foo;
			return getThis();
		}

		public final B bar(List<String> bar) {
			this.bar = bar;
			return getThis();
		}

		@Override
		public final boolean isValid() {
			FooBar instance = construct();
			Optional<String> invalidMessage = invalidMessage(instance);
			return !invalidMessage.isPresent();
		}

		protected void and(StringBuilder sb, String message) {
			if (sb.length() != 0) {
				sb.append(" AND ");
			}
			sb.append(message);
		}

		@Override
		protected Optional<String> invalidMessage(FooBar instance) {
			StringBuilder sb = new StringBuilder();
			if (bar == null) {
				and(sb, "bar is null");
			}
			int fooMin = bar.size() != 0 ? 0 : 30;
			int fooMax = bar.size() != 0 ? 45 : 60;
			if (foo < fooMin) {
				and(sb, "foo is too small");
			}
			if (foo > fooMax) {
				and(sb, "foo is too large");
			}
			if (sb.length() == 0) {
				return Optional.<String> absent();
			} else {
				return Optional.of(sb.toString());
			}
		}

	}

	public static class DefaultBuilder extends Builder<DefaultBuilder> {

		public static final int FOO_DEFAULT = 0;
		public static final List<String> BAR_DEFAULT = ImmutableList.of("Hello World");

		private DefaultBuilder() {
		}

		@Override
		protected void defaults() {
			foo(FOO_DEFAULT).bar(BAR_DEFAULT);
		}

		@Override
		protected FooBar construct() {
			return new FooBar(getThis(), Optional.<Boolean> absent());
		}
	}

	public static class EmptyBarBuilder extends Builder<EmptyBarBuilder> {

		public static final int FOO_DEFAULT = 30;
		public static final List<String> BAR_DEFAULT = ImmutableList.of();
		public static final boolean BAZ_DEFAULT = true;

		private Optional<Boolean> baz = Optional.of(BAZ_DEFAULT);

		private EmptyBarBuilder() {
		}

		public EmptyBarBuilder baz(boolean baz) {
			this.baz = Optional.of(baz);
			return getThis();
		}

		@Override
		protected void defaults() {
			foo(FOO_DEFAULT).bar(BAR_DEFAULT).baz(BAZ_DEFAULT);
		}

		@Override
		protected FooBar construct() {
			return new FooBar(getThis(), baz);
		}
	}
}