package org.kuali.common.util.build;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.kuali.common.util.validate.Validation;

public abstract class ValidatingInstanceBuilder<T> implements InstanceBuilder<T> {

	protected Validator validator = Validation.getDefaultValidator();

	@Override
	public final T build() {
		checkNotNull(validator, "'validator' cannot be null'");
		T instance = getInstance();
		Set<ConstraintViolation<T>> violations = validator.validate(instance);
		Validation.check(violations);
		return instance;
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

}
