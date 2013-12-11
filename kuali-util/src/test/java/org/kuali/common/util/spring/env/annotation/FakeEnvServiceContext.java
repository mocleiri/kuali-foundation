package org.kuali.common.util.spring.env.annotation;

import java.lang.reflect.Field;

import org.kuali.common.util.Mode;
import org.kuali.common.util.builder.AbstractBuilder;
import org.kuali.common.util.env.DefaultEnvironmentOverrideService;
import org.kuali.common.util.env.EnvironmentOverrideService;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

public final class FakeEnvServiceContext {

	public Optional<String> getFoo() {
		return foo;
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

	private final boolean checkEnvironmentVariables;
	private final boolean resolveStrings;
	private final Mode missingPropertyMode;
	private final Optional<String> foo;

	private FakeEnvServiceContext(Builder builder) {
		this.checkEnvironmentVariables = builder.checkEnvironmentVariables;
		this.resolveStrings = builder.resolveStrings;
		this.missingPropertyMode = builder.missingPropertyMode;
		this.foo = builder.foo;
	}

	@EnvPrefix("env")
	public static class Builder extends AbstractBuilder<FakeEnvServiceContext> {

		private EnvironmentOverrideService service = new DefaultEnvironmentOverrideService.Builder().build();

		@EnvOverride
		private boolean checkEnvironmentVariables = true;

		@EnvOverride
		private boolean resolveStrings = true;

		@EnvOverride
		private Mode missingPropertyMode = Mode.ERROR;

		@EnvOverride
		private Optional<String> foo = Optional.absent();

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

		@Override
		protected FakeEnvServiceContext getInstance() {
			service.override(this);
			return new FakeEnvServiceContext(this);
		}

		@Override
		protected void validate(FakeEnvServiceContext ctx) {
			Preconditions.checkNotNull(ctx.getMissingPropertyMode(), "'mode' cannot be null");
		}

		public void set(Field field, Object value) {
			try {
				field.set(this, value);
			} catch (IllegalAccessException e) {
				throw new IllegalStateException(e);
			}
		}

	}

}
