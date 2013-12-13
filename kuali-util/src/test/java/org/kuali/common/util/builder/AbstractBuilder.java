package org.kuali.common.util.builder;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.kuali.common.util.env.DefaultOverrideService;
import org.kuali.common.util.env.OverrideService;
import org.kuali.common.util.env.annotation.EnvIgnore;
import org.kuali.common.util.validate.ValidatorUtils;

public abstract class AbstractBuilder<T> implements Builder<T> {

	public AbstractBuilder() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
		this.overrider = new DefaultOverrideService();
	}

	@EnvIgnore
	private final Validator validator;

	@EnvIgnore
	private final OverrideService overrider;

	@Override
	public final T build() {
		overrider.override(this);
		T instance = getInstance();
		Set<ConstraintViolation<T>> violations = validator.validate(instance);
		ValidatorUtils.validate(violations);
		return instance;
	}

	protected abstract T getInstance();

}
