package org.kuali.common.util.validate;

import static org.kuali.common.util.ReflectionUtils.isFinal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.kuali.common.util.validate.annotation.FinalClass;

public class FinalClassValidator implements ConstraintValidator<FinalClass, Object> {

	@Override
	public void initialize(FinalClass constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {
		return isFinal(object.getClass());
	}
}
