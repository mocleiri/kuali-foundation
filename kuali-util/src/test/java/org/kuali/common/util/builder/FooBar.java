package org.kuali.common.util.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** Conditionally settable {@code baz}, with one small problem. */
public class FooBar {
	private final int foo;
	private final List<String> bar;
	private final Boolean baz;

	private FooBar(Builder builder, Boolean baz) {
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

		public final boolean isValid() {
			int fooMin = bar != null ? 0 : 30;
			int fooMax = bar != null ? 45 : 60;
			return fooMin <= foo && foo <= fooMax;
		}
	}

	public static class DefaultBuilder extends Builder {
		public static final int FOO_DEFAULT = 0;
		public static final List<String> BAR_DEFAULT = Collections.unmodifiableList(Arrays.asList(new String[] { "Hello, world!" }));

		private DefaultBuilder() {
		}

		protected void defaults() {
			withFoo(FOO_DEFAULT).withBar(BAR_DEFAULT);
		}

		protected FooBar construct() {
			return new FooBar(this, null);
		}
	}

	public static class NullBarBuilder extends Builder {
		public static final int FOO_DEFAULT = 30;
		public static final List<String> BAR_DEFAULT = null;
		public static final Boolean BAZ_DEFAULT = Boolean.TRUE;

		private Boolean baz;

		private NullBarBuilder() {
		}

		public NullBarBuilder withBaz(Boolean baz) {
			this.baz = baz;
			return this;
		}

		protected void defaults() {
			withFoo(FOO_DEFAULT).withBar(BAR_DEFAULT).withBaz(BAZ_DEFAULT); // OOPS!
		}

		protected FooBar construct() {
			return new FooBar(this, baz);
		}
	}
}