package org.kuali.common.util.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.kuali.common.util.LocationUtils;

public class ExistsValidator implements ConstraintValidator<Exists, String> {

	@Override
	public void initialize(Exists constraintAnnotation) {
	}

	@Override
	public boolean isValid(String resource, ConstraintValidatorContext context) {
		if (resource == null) {
			return true;
		} else {
			return LocationUtils.exists(resource);
		}
	}

}
