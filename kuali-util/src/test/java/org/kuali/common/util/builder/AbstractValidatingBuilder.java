package org.kuali.common.util.builder;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.validate.Validation;

public abstract class AbstractValidatingBuilder<T> implements ValidatingBuilder<T> {

	protected abstract T getInstance();

	@Override
	public final T build() {
		T instance = getInstance();
		Set<ConstraintViolation<T>> violations = Validation.getDefaultValidator().validate(instance);
		Validation.check(violations);
		return instance;
	}

}
