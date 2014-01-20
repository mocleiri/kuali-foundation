package org.kuali.common.util.bind.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.env.Environment;

import com.google.common.collect.ImmutableMap;

public final class EnvironmentPropertyValues<T> extends MutablePropertyValues {

	private static final long serialVersionUID = 6864548343005636397L;

	private final Class<T> type;
	private final Environment env;
	private final ImmutableMap<String, String> values;

	private EnvironmentPropertyValues(Builder<T> builder) {
		super(builder.values);
		this.type = builder.type;
		this.env = builder.env;
		this.values = ImmutableMap.copyOf(builder.values);
	}

	public static <T> EnvironmentPropertyValues<T> create(Class<T> target, Environment env) {
		return builder(target, env).build();
	}

	public static <T> Builder<T> builder(Class<T> type, Environment env) {
		return new Builder<T>(type, env);
	}

	public static class Builder<T> implements org.kuali.common.util.build.Builder<EnvironmentPropertyValues<T>> {

		// Required
		private final Class<T> type;
		private final Environment env;

		// Filled in by the build() method
		private Map<String, String> values;

		public Builder(Class<T> target, Environment env) {
			this.type = target;
			this.env = env;
		}

		@Override
		public EnvironmentPropertyValues<T> build() {
			this.values = BindUtils.getMap(type, env);
			EnvironmentPropertyValues<T> instance = new EnvironmentPropertyValues<T>(this);
			validate(instance);
			return instance;
		}

		private static <T> void validate(EnvironmentPropertyValues<T> instance) {
			checkNotNull(instance.type, "'type' cannot be null");
			checkNotNull(instance.env, "'env' cannot be null");
			checkNotNull(instance.values, "'values' cannot be null");
		}
	}

	public Class<T> getType() {
		return type;
	}

	public Environment getEnv() {
		return env;
	}

	public ImmutableMap<String, String> getValues() {
		return values;
	}

}
