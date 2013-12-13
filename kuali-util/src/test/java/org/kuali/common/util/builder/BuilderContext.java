package org.kuali.common.util.builder;

import javax.validation.Validator;

import org.kuali.common.util.env.OverrideService;

import com.google.common.base.Preconditions;

public final class BuilderContext {

	public Validator getValidator() {
		return validator;
	}

	public OverrideService getOverrider() {
		return overrider;
	}

	private final Validator validator;
	private final OverrideService overrider;

	private BuilderContext(Builder builder) {
		this.validator = builder.validator;
		this.overrider = builder.overrider;
	}

	public static class Builder {

		private final Validator validator;
		private final OverrideService overrider;

		public Builder(Validator validator, OverrideService overrider) {
			this.validator = validator;
			this.overrider = overrider;
		}

		public BuilderContext build() {
			BuilderContext instance = new BuilderContext(this);
			validate(instance);
			return instance;
		}

		private void validate(BuilderContext instance) {
			Preconditions.checkNotNull(instance.validator, "'validator' cannot be null");
			Preconditions.checkNotNull(instance.overrider, "'overrider' cannot be null");
		}
	}

}
