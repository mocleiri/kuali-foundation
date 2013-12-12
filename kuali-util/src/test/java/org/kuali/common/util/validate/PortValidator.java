package org.kuali.common.util.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.kuali.common.util.validate.annotation.Port;

public class PortValidator implements ConstraintValidator<Port, Integer> {

	private static final int MIN = 0;
	private static final int MAX = 65535;

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
