package org.kuali.common.util.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.kuali.common.util.Ports;

public class PortValidator implements ConstraintValidator<Port, Integer> {

	@Override
	public void initialize(Port constraintAnnotation) {
	}

	@Override
	public boolean isValid(Integer number, ConstraintValidatorContext context) {
		if (number == null) {
			return false;
		} else {
			return number >= Ports.MIN && number <= Ports.MAX;
		}
	}

}
