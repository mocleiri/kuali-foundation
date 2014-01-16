package org.kuali.common.util.reflection;

import static com.google.common.base.Preconditions.checkArgument;

import org.apache.commons.lang3.StringUtils;

public class Foo {

	private final String bar;

	private Foo(Builder builder) {
		this.bar = builder.bar;
	}

	public static class Builder implements org.kuali.common.util.builder.Builder<Foo> {

		private String bar;

		public Builder bar(String bar) {
			this.bar = bar;
			return this;
		}

		@Override
		public Foo build() {
			Foo instance = new Foo(this);
			validate(instance);
			return instance;
		}

		private static void validate(Foo instance) {
			checkArgument(!StringUtils.isBlank(instance.bar), "'bar' cannot be blank");
		}
	}

}
