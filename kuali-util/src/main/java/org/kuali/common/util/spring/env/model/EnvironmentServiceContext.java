package org.kuali.common.util.spring.env.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.Mode;
import org.kuali.common.util.spring.env.EnvUtils;
import org.springframework.core.env.Environment;

public final class EnvironmentServiceContext {

	private final Environment env;
	private final boolean checkEnvironmentVariables;
	private final boolean resolveStrings;
	private final Mode missingPropertyMode;

	public static class Builder {

		private Environment env = EnvUtils.getDefaultEnvironment();
		private boolean checkEnvironmentVariables = true;
		private boolean resolveStrings = true;
		private Mode missingPropertyMode = Mode.ERROR;

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

		private void validate(EnvironmentServiceContext ctx) {
			Assert.noNulls(ctx.getEnv(), ctx.getMissingPropertyMode());
		}

		private void override() {

		}

		public EnvironmentServiceContext build() {
			override();
			EnvironmentServiceContext ctx = new EnvironmentServiceContext(this);
			validate(ctx);
			return ctx;
		}

	}

	private EnvironmentServiceContext(Builder builder) {
		this.env = builder.env;
		this.checkEnvironmentVariables = builder.checkEnvironmentVariables;
		this.resolveStrings = builder.resolveStrings;
		this.missingPropertyMode = builder.missingPropertyMode;
	}

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

}
