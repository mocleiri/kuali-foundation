package org.kuali.common.util.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.kuali.common.util.LocationUtils;

public class ExistsValidator implements ConstraintValidator<Exists, String> {

	@Override
	public void initialize(Exists constraintAnnotation) {
	}

	@Override
	public boolean isValid(String location, ConstraintValidatorContext context) {
		if (location == null) {
			return true;
		} else {
			return LocationUtils.exists(location);
		}
	}

}
