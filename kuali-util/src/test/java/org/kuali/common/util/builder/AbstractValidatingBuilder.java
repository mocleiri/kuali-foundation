package org.kuali.common.util.builder;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.kuali.common.util.validate.ValidationUtils;

public abstract class AbstractValidatingBuilder<T> implements ValidatingBuilder<T> {

	protected abstract T getInstance();

	@Override
	public final T build() {
		T instance = getInstance();
		Validator validator = ValidationUtils.getDefaultValidator();
		Set<ConstraintViolation<T>> violations = validator.validate(instance);
		ValidationUtils.check(violations);
		return instance;
	}

}
