package org.kuali.common.util.spring.binder;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class GlobalValidator implements Validator {

	private static final Class<?>[] EMPTY = {};
	private final javax.validation.Validator validator;

	public GlobalValidator(javax.validation.Validator validator) {
		Assert.notNull(validator);
		this.validator = validator;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Set<ConstraintViolation<Object>> violations = validator.validate(target, EMPTY);
		for (ConstraintViolation<Object> violation : violations) {
			errors.reject("validation.violation", violation.getMessage());
		}
	}

}
