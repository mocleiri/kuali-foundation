package org.kuali.common.util.spring.env.model;

import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.Mode;
import org.kuali.common.util.builder.AbstractBuilder;
import org.kuali.common.util.spring.env.EnvUtils;
import org.kuali.common.util.spring.env.PropertiesEnvironment;
import org.springframework.core.env.Environment;

public final class EnvironmentServiceContext {

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

	private EnvironmentServiceContext(Builder builder) {
		this.env = builder.env;
		this.checkEnvironmentVariables = builder.checkEnvironmentVariables;
		this.resolveStrings = builder.resolveStrings;
		this.missingPropertyMode = builder.missingPropertyMode;
	}

	public static class Builder extends AbstractBuilder<EnvironmentServiceContext> {

		private Environment env = EnvUtils.getDefaultEnvironment();
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
			Assert.noNulls(env);
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

		private void override() {
			checkEnvironmentVariables(env.getProperty(CHECK_ENVIRONMENT_VARIABLES_KEY, Boolean.class, checkEnvironmentVariables));
			resolveStrings(env.getProperty(RESOLVE_STRINGS_KEY, Boolean.class, resolveStrings));
			missingPropertyMode(env.getProperty(MISSING_PROPERTY_MODE_KEY, Mode.class, missingPropertyMode));
		}

		@Override
		protected void validate(EnvironmentServiceContext ctx) {
			Assert.noNulls(ctx.getEnv(), ctx.getMissingPropertyMode());
		}

		@Override
		protected EnvironmentServiceContext getInstance() {
			override();
			return new EnvironmentServiceContext(this);
		}

	}

}
