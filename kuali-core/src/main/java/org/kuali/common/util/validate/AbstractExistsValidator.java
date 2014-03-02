package org.kuali.common.util.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public abstract class AbstractExistsValidator<T> implements ConstraintValidator<Exists, T> {

	@Override
	public void initialize(Exists constraintAnnotation) {
	}

	protected boolean validate(boolean valid, String path, ConstraintValidatorContext context) {
		if (!valid) {
			String error = String.format("[%s] does not exist", path);
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(error).addConstraintViolation();
		}
		return valid;
	}

}
