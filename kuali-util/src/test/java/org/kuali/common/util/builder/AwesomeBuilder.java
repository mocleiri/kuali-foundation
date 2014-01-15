package org.kuali.common.util.builder;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.kuali.common.util.validate.Validation;

public abstract class AwesomeBuilder<T> implements Builder<T> {

	protected abstract T getInstance();

	private final Validator validator = Validation.getDefaultValidator();

	@Override
	public final T build() {
		T instance = getInstance();
		Set<ConstraintViolation<T>> violations = validate(instance);
		Validation.check(violations);
		return instance;
	}

	public Set<ConstraintViolation<T>> validate(T instance) {
		return validator.validate(instance);
	}

}
