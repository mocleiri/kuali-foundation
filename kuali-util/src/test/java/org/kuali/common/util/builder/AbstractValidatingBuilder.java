package org.kuali.common.util.builder;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.kuali.common.util.validate.Validation;

public abstract class AbstractValidatingBuilder<T> implements ValidatingBuilder<T> {

	protected abstract T getInstance();

	private final Validator validator;

	@Override
	public final T build() {
		T instance = getInstance();
		Set<ConstraintViolation<T>> violations = validate(instance);
		Validation.check(violations);
		return instance;
	}

	@Override
	public Set<ConstraintViolation<T>> validate(T instance) {
		return validator.validate(instance);
	}

	private AbstractValidatingBuilder(Builder builder) {
		this.validator = builder.validator;
	}

	public static class Builder {

		private Validator validator = Validation.getDefaultValidator();

		public Builder validator(Validator validator) {
			this.validator = validator;
			return this;
		}

		protected static <T> void validate(AbstractValidatingBuilder<T> instance) {
			checkNotNull(instance.validator, "'validator' cannot be null");
		}
	}

}
