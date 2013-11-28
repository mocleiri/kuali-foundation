package org.kuali.common.util.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** Now with more builders! */
public class FooBar {
	private final int foo;
	private final List<String> bar;

	private FooBar(Builder builder) {
		this.foo = builder.foo;
		this.bar = Collections.unmodifiableList(new ArrayList<String>(builder.bar));
	}

	public int getFoo() {
		return foo;
	}

	/** @return {@code bar} as an unmodifiable list */
	public List<String> getBar() {
		return bar;
	}

	public static DefaultBuilder builder() {
		return new DefaultBuilder();
	}

	public static NullBarBuilder nullBarBuilder() {
		return new NullBarBuilder();
	}

	public static abstract class Builder extends AbstractBuilder<FooBar> {
		private int foo;
		private List<String> bar;

		private Builder() {
		}

		public final Builder withFoo(int foo) {
			this.foo = foo;
			return this;
		}

		public final Builder withBar(List<String> bar) {
			this.bar = bar;
			return this;
		}

		protected abstract int defaultFoo();

		protected abstract List<String> defaultBar();

		@Override
		public final boolean isValid() {
			int fooMin = bar != null ? 0 : 30;
			int fooMax = bar != null ? 45 : 60;
			return fooMin <= foo && foo <= fooMax;
		}

		@Override
		protected final void defaults() {
			withFoo(defaultFoo()).withBar(defaultBar());
		}

		@Override
		protected final FooBar construct() {
			return new FooBar(this);
		}
	}

	public static class DefaultBuilder extends Builder {
		public static final int FOO_DEFAULT = 0;
		public static final List<String> BAR_DEFAULT = Collections.unmodifiableList(Arrays.asList(new String[] { "Hello, world!" }));

		private DefaultBuilder() {
		}

		@Override
		protected int defaultFoo() {
			return FOO_DEFAULT;
		}

		@Override
		protected List<String> defaultBar() {
			return BAR_DEFAULT;
		}
	}

	public static class NullBarBuilder extends Builder {
		public static final int FOO_DEFAULT = 30;
		public static final List<String> BAR_DEFAULT = null;

		private NullBarBuilder() {
		}

		@Override
		protected int defaultFoo() {
			return FOO_DEFAULT;
		}

		@Override
		protected List<String> defaultBar() {
			return BAR_DEFAULT;
		}
	}
}