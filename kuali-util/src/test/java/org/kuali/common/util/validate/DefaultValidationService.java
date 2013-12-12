package org.kuali.common.util.validate;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

public final class DefaultValidationService implements ValidationService {

	private final List<Validator> validators;

	@Override
	public ValidationResult validate(Object instance) {
		List<String> messages = new ArrayList<String>();
		for (Validator validator : validators) {
			ValidationResult result = validator.validate(instance);
			messages.addAll(result.getErrorMessages());
		}
		return new ValidationResult(messages);
	}

	private DefaultValidationService(Builder builder) {
		this.validators = builder.validators;
	}

	public static class Builder {

		private List<Validator> validators;

		public Builder() {
			this.validators = new ArrayList<Validator>();
			validators.add(new NoNullsValidator());
			validators.add(new NoBlanksValidator());
		}

		public Builder withValidators(List<Validator> validators) {
			this.validators = validators;
			return this;
		}

		public DefaultValidationService build() {
			this.validators = ImmutableList.copyOf(validators);
			DefaultValidationService instance = new DefaultValidationService(this);
			validate(instance);
			return new DefaultValidationService(this);
		}

		private void validate(DefaultValidationService instance) {
			Preconditions.checkNotNull(instance.validators, "'validators' cannot be null");
		}
	}

}
