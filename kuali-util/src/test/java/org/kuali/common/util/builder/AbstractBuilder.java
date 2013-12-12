package org.kuali.common.util.builder;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public abstract class AbstractBuilder<T> implements Builder<T> {

	public AbstractBuilder() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
	}

	public Validator getValidator() {
		return validator;
	}

	private final Validator validator;

	@Override
	public final T build() {
		T instance = getInstance();
		Set<ConstraintViolation<T>> violations = validator.validate(instance);
		validate(violations);
		return instance;
	}

	protected void validate(Set<ConstraintViolation<T>> violations) {
		if (violations.size() > 0) {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (ConstraintViolation<T> violation : violations) {
				if (i++ != 0) {
					sb.append(", ");
				}
				sb.append("[" + violation.getMessage() + "]");
			}
			throw new IllegalArgumentException(sb.toString());
		}
	}

	protected abstract T getInstance();

}
