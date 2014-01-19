package org.kuali.common.util.env.annotation;

import java.util.List;

import org.kuali.common.util.Mode;
import org.kuali.common.util.build.test.AbstractBuilder;
import org.kuali.common.util.env.adapter.CSVToListAdapter;
import org.kuali.common.util.env.adapter.OptionalStringAdapter;
import org.kuali.common.util.validate.NoBlanks;
import org.kuali.common.util.validate.NoNullFields;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

@NoNullFields
@NoBlanks
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

	@Env(prefix = "env")
	public static class Builder extends AbstractBuilder<FakeEnvServiceContext> {

		private boolean checkEnvironmentVariables = true;
		private boolean resolveStrings = true;
		private Mode missingPropertyMode = Mode.ERROR;

		@EnvAdapterClass(OptionalStringAdapter.class)
		private Optional<String> foo = Optional.absent();

		@EnvAdapterClass(CSVToListAdapter.class)
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
			return new FakeEnvServiceContext(this);
		}

	}

}
