package org.kuali.common.core.validate;

import javax.validation.ConstraintValidatorContext;

import org.kuali.common.util.LocationUtils;

public class LocationExistsValidator extends AbstractExistsValidator<String> {

	@Override
	public boolean isValid(String resource, ConstraintValidatorContext context) {
		if (resource == null) {
			return true;
		} else {
			return validate(LocationUtils.exists(resource), resource, context);
		}
	}

}
