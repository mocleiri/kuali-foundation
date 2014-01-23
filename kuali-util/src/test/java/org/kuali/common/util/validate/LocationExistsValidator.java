package org.kuali.common.util.validate;

import javax.validation.ConstraintValidatorContext;

import org.kuali.common.util.LocationUtils;

public class LocationExistsValidator extends AbstractExistsValidator<String> {

	@Override
	public boolean isValid(String resource, ConstraintValidatorContext context) {
		if (resource == null) {
			return true;
		} else {
			boolean valid = LocationUtils.exists(resource);
			doValidCheck(valid, resource, context);
			return valid;
		}
	}

}
