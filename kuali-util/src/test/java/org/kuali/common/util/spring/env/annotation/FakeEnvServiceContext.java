package org.kuali.common.util.spring.env.annotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.Mode;
import org.kuali.common.util.builder.ValidatingBuilder;
import org.kuali.common.util.spring.env.EnvUtils;
import org.kuali.common.util.spring.env.PropertiesEnvironment;
import org.springframework.core.env.Environment;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class FakeEnvServiceContext {

	public Environment getEnv() {
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

	private final Environment env;
	private final boolean checkEnvironmentVariables;
	private final boolean resolveStrings;
	private final Mode missingPropertyMode;

	private FakeEnvServiceContext(Builder builder) {
		this.env = builder.env;
		this.checkEnvironmentVariables = builder.checkEnvironmentVariables;
		this.resolveStrings = builder.resolveStrings;
		this.missingPropertyMode = builder.missingPropertyMode;
	}

	@EnvOverridePrefix("env")
	public static class Builder extends ValidatingBuilder<FakeEnvServiceContext> {

		private Environment env = EnvUtils.getDefaultEnvironment();

		@EnvOverride
		private boolean checkEnvironmentVariables = true;
		private boolean resolveStrings = true;
		private Mode missingPropertyMode = Mode.ERROR;

		private static final String CHECK_ENVIRONMENT_VARIABLES_KEY = "env.checkEnvironmentVariables";
		private static final String RESOLVE_STRINGS_KEY = "env.resolveStrings";
		private static final String MISSING_PROPERTY_MODE_KEY = "env.missingPropertyMode";

		public Builder env(Properties properties) {
			return env(new PropertiesEnvironment(properties));
		}

		public Builder env(Environment env) {
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
			EnvOverridePrefix eop = this.getClass().getAnnotation(EnvOverridePrefix.class);
			if (eop == null || StringUtils.isBlank(eop.value())) {
				return Optional.<String> absent();
			} else {
				return Optional.of(eop.value());
			}
		}

		private void override() {
			Assert.noNulls(env);
			Optional<String> prefix = getPrefix();
			System.out.println(prefix);
			for (Field field : this.getClass().getDeclaredFields()) {
				EnvOverride override = field.getAnnotation(EnvOverride.class);
				if (override == null) {
					continue;
				}
				Class<?> type = field.getType();
				String name = field.getName();
				System.out.println(type + name);
			}
			checkEnvironmentVariables(env.getProperty(CHECK_ENVIRONMENT_VARIABLES_KEY, Boolean.class, checkEnvironmentVariables));
			resolveStrings(env.getProperty(RESOLVE_STRINGS_KEY, Boolean.class, resolveStrings));
			missingPropertyMode(env.getProperty(MISSING_PROPERTY_MODE_KEY, Mode.class, missingPropertyMode));
		}

		protected List<String> getKeys(Optional<String> prefix, Field field, EnvOverride override) {
			String[] values = override.value();

			List<String> keys = new ArrayList<String>();
			if (values != null && values.length == 0) {
				keys.addAll(ImmutableList.copyOf(values));
			} else {
				keys.add(field.getName());
			}
			Assert.noBlanks(keys);
			return null;
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
