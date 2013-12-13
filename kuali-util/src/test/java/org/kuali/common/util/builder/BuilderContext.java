package org.kuali.common.util.builder;

import javax.validation.Validator;

import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.env.OverrideService;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

public final class BuilderContext {

	public Optional<EncryptionService> getEnc() {
		return enc;
	}

	public Validator getValidator() {
		return validator;
	}

	public OverrideService getOverrider() {
		return overrider;
	}

	private final Validator validator;
	private final OverrideService overrider;
	private final Optional<EncryptionService> enc;

	private BuilderContext(Builder builder) {
		this.validator = builder.validator;
		this.overrider = builder.overrider;
		this.enc = builder.enc;
	}

	public static class Builder {

		// Required
		private final Validator validator;
		private final OverrideService overrider;

		// Optional
		private Optional<EncryptionService> enc;

		public Builder(Validator validator, OverrideService overrider) {
			this.validator = validator;
			this.overrider = overrider;
		}

		public Builder enc(EncryptionService enc) {
			this.enc = Optional.of(enc);
			return this;
		}

		public BuilderContext build() {
			BuilderContext instance = new BuilderContext(this);
			validate(instance);
			return instance;
		}

		private void validate(BuilderContext instance) {
			Preconditions.checkNotNull(instance.validator, "'validator' cannot be null");
			Preconditions.checkNotNull(instance.overrider, "'overrider' cannot be null");
			Preconditions.checkNotNull(instance.enc, "'enc' cannot be null");
		}
	}

}
