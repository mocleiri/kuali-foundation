package org.kuali.common.util.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.kuali.common.util.ReflectionUtils;

public class FinalClassValidator implements ConstraintValidator<FinalClass, Object> {

	@Override
	public void initialize(FinalClass constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {
		return ReflectionUtils.isFinal(object.getClass());
	}
}
