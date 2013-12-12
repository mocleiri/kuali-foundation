package org.kuali.common.util.validate;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

public final class DefaultValidationService implements ValidationService {

	private final List<Validator> validators;

	@Override
	public Optional<Errors> validate(Object instance) {
		List<String> messages = new ArrayList<String>();
		for (Validator validator : validators) {
			Optional<Errors> errors = validator.validate(instance);
			if (errors.isPresent()) {
				messages.addAll(errors.get().getMessages());
			}
		}
		if (messages.size() > 0) {
			return Optional.of(new Errors(messages));
		} else {
			return Optional.absent();
		}
	}

	private DefaultValidationService(Builder builder) {
		this.validators = builder.validators;
	}

	public static class Builder {

		private List<Validator> validators = ImmutableList.of();

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
			Preconditions.checkNotNull(instance.validators, "validators may not be null");
		}
	}

}
