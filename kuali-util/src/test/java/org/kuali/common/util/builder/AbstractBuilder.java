package org.kuali.common.util.builder;

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
		validator.validate(instance);
		return instance;
	}

	protected abstract T getInstance();

}
