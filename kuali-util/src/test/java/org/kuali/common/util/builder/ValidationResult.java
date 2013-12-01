package org.kuali.common.util.builder;

import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public final class ValidationResult {

	public boolean isValid() {
		return valid;
	}

	public Optional<String> getMessage() {
		return message;
	}

	private final Optional<String> message;
	private final boolean valid;

	private ValidationResult(Builder builder) {
		this.message = builder.message;
		this.valid = message.isPresent();
	}

	public static class Builder {

		private final Optional<String> message;

		public Builder(Optional<String> message) {
			this.message = message;
		}

		private void validate(ValidationResult instance) {
			Assert.noNulls(instance);
		}

		public ValidationResult build() {
			ValidationResult instance = new ValidationResult(this);
			validate(instance);
			return instance;
		}
	}
}
