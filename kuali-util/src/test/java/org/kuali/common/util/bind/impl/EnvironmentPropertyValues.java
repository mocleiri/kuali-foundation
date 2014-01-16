package org.kuali.common.util.bind.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.env.Environment;

import com.google.common.collect.ImmutableMap;

public final class EnvironmentPropertyValues<T> extends MutablePropertyValues {

	private static final long serialVersionUID = 6864548343005636397L;

	private final Class<T> target;
	private final Environment env;
	private final ImmutableMap<String, String> values;

	private EnvironmentPropertyValues(Builder<T> builder) {
		super(builder.values);
		this.target = builder.target;
		this.env = builder.env;
		this.values = builder.values;
	}

	public static <T> EnvironmentPropertyValues<T> create(Class<T> target, Environment env) {
		return builder(target, env).build();
	}

	public static <T> Builder<T> builder(Class<T> target, Environment env) {
		return new Builder<T>(target, env);
	}

	public static class Builder<T> implements org.kuali.common.util.builder.Builder<EnvironmentPropertyValues<T>> {

		// Required
		private final Class<T> target;
		private final Environment env;

		// Filled in by the build() method
		private ImmutableMap<String, String> values;

		public Builder(Class<T> target, Environment env) {
			this.target = target;
			this.env = env;
		}

		@Override
		public EnvironmentPropertyValues<T> build() {
			this.values = BindUtils.getMap(target, env);
			EnvironmentPropertyValues<T> instance = new EnvironmentPropertyValues<T>(this);
			validate(instance);
			return instance;
		}

		private static <T> void validate(EnvironmentPropertyValues<T> instance) {
			checkNotNull(instance.target, "'target' cannot be null");
			checkNotNull(instance.env, "'env' cannot be null");
			checkNotNull(instance.values, "'values' cannot be null");
		}
	}

}
