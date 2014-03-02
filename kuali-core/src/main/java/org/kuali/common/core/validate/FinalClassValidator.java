package org.kuali.common.core.validate;

import static org.kuali.common.util.ReflectionUtils.isFinal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.kuali.common.core.validate.annotation.FinalClass;

public class FinalClassValidator implements ConstraintValidator<FinalClass, Object> {

	@Override
	public void initialize(FinalClass constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {
		return isFinal(object.getClass());
	}
}
