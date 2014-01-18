package org.kuali.common.util.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.kuali.common.util.LocationUtils;

public class LocationExistsValidator implements ConstraintValidator<Exists, String> {

	@Override
	public void initialize(Exists constraintAnnotation) {
	}

	@Override
	public boolean isValid(String resource, ConstraintValidatorContext context) {
		if (resource == null) {
			return true;
		} else {
			boolean valid = LocationUtils.exists(resource);
			if (!valid) {
				String error = String.format("[%s] does not exist", resource);
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(error).addConstraintViolation();
			}
			return valid;
		}
	}

}
