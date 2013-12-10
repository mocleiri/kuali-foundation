package org.kuali.common.util.spring.env.annotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.ListUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.builder.AbstractBuilder;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.BasicEnvironmentService;
import org.kuali.common.util.spring.env.EnvironmentService;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class FakeEnvServiceContext {

	public EnvironmentService getEnv() {
		return env;
	}

	public boolean isCheckEnvironmentVariables() {
		return checkEnvironmentVariables;
	}

	public boolean isResolveStrings() {
		return resolveStrings;
	}

	public Mode getMissingPropertyMode() {
		return missingPropertyMode;
	}

	private final EnvironmentService env;
	private final boolean checkEnvironmentVariables;
	private final boolean resolveStrings;
	private final Mode missingPropertyMode;

	private FakeEnvServiceContext(Builder builder) {
		this.env = builder.env;
		this.checkEnvironmentVariables = builder.checkEnvironmentVariables;
		this.resolveStrings = builder.resolveStrings;
		this.missingPropertyMode = builder.missingPropertyMode;
	}

	@EnvPrefix("env")
	public static class Builder extends AbstractBuilder<FakeEnvServiceContext> {

		private EnvironmentService env = new BasicEnvironmentService();

		@EnvOverride
		private boolean checkEnvironmentVariables = true;

		@EnvOverride
		private boolean resolveStrings = true;

		@EnvOverride
		private Mode missingPropertyMode = Mode.ERROR;

		public Builder env(Properties properties) {
			return env(new BasicEnvironmentService(properties));
		}

		public Builder env(EnvironmentService env) {
			this.env = env;
			return this;
		}

		public Builder checkEnvironmentVariables(boolean checkEnvironmentVariables) {
			this.checkEnvironmentVariables = checkEnvironmentVariables;
			return this;
		}

		public Builder resolveStrings(boolean resolveStrings) {
			this.resolveStrings = resolveStrings;
			return this;
		}

		public Builder missingPropertyMode(Mode missingPropertyMode) {
			this.missingPropertyMode = missingPropertyMode;
			return this;
		}

		private Optional<String> getPrefix() {
			EnvPrefix prefix = this.getClass().getAnnotation(EnvPrefix.class);
			if (prefix == null || StringUtils.isBlank(prefix.value())) {
				return Optional.<String> absent();
			} else {
				return Optional.of(prefix.value());
			}
		}

		private void override() {
			Optional<String> prefix = getPrefix();
			for (Field field : this.getClass().getDeclaredFields()) {
				EnvOverride annotation = field.getAnnotation(EnvOverride.class);
				if (annotation != null) {
					List<String> keys = getKeys(prefix, field, annotation);
					override(field, keys);
				}
			}
		}

		protected void set(Object instance, Field field, Object value) {
			try {
				field.set(instance, value);
			} catch (IllegalAccessException e) {
				throw new IllegalStateException(e);
			}
		}

		protected void override(Field field, List<String> keys) {
			Class<?> type = field.getType();
			for (String key : keys) {
				Optional<?> optional = SpringUtils.getOptionalProperty(env, key, type);
				if (optional.isPresent()) {
					set(this, field, optional.get());
					return;
				}
			}
		}

		protected List<String> getKeys(Optional<String> prefix, Field field, EnvOverride override) {
			List<String> keys = new ArrayList<String>();
			if (override.keys().length > 0) {
				keys.addAll(ImmutableList.copyOf(override.keys()));
			} else {
				keys.add(field.getName());
			}
			if (prefix.isPresent()) {
				return ListUtils.prefix(prefix.get(), ".", keys);
			} else {
				return keys;
			}
		}

		@Override
		protected void validate(FakeEnvServiceContext ctx) {
			Assert.noNulls(ctx.getEnv(), ctx.getMissingPropertyMode());
		}

		@Override
		protected FakeEnvServiceContext getInstance() {
			override();
			return new FakeEnvServiceContext(this);
		}
	}

}
