package org.kuali.common.util.env.annotation;

import java.util.List;

import org.kuali.common.util.Mode;
import org.kuali.common.util.builder.AbstractBuilder;
import org.kuali.common.util.env.DefaultEnvOverrideService;
import org.kuali.common.util.env.EnvOverrideService;
import org.kuali.common.util.env.adapter.CSVToListAdapter;
import org.kuali.common.util.env.adapter.OptionalStringAdapter;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

public final class FakeEnvServiceContext {

	public List<String> getBar() {
		return bar;
	}

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
	private final List<String> bar;

	private FakeEnvServiceContext(Builder builder) {
		this.checkEnvironmentVariables = builder.checkEnvironmentVariables;
		this.resolveStrings = builder.resolveStrings;
		this.missingPropertyMode = builder.missingPropertyMode;
		this.foo = builder.foo;
		this.bar = builder.bar;
	}

	@EnvOverride(prefix = "env")
	public static class Builder extends AbstractBuilder<FakeEnvServiceContext> {

		@EnvOverride(skip = true)
		private EnvOverrideService service = new DefaultEnvOverrideService.Builder().build();

		private boolean checkEnvironmentVariables = true;
		private boolean resolveStrings = true;
		private Mode missingPropertyMode = Mode.ERROR;

		@EnvAdapter(OptionalStringAdapter.class)
		private Optional<String> foo = Optional.absent();

		@EnvAdapter(CSVToListAdapter.class)
		private List<String> bar = ImmutableList.of();

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

	}

}
