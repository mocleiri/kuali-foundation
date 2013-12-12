package org.kuali.common.util.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PortValidator implements ConstraintValidator<Port, Integer> {

	public static final int MIN = 0;
	public static final int MAX = 65535;

	@Override
	public void initialize(Port constraintAnnotation) {
	}

	@Override
	public boolean isValid(Integer number, ConstraintValidatorContext context) {
		if (number == null) {
			return false;
		} else {
			return number >= MIN && number <= MAX;
		}
	}

}
