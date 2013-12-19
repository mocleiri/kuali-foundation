package org.kuali.common.util.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Size;

import org.kuali.common.util.primitives.Numbers;

public class PortValidator implements ConstraintValidator<Port, Integer> {

	private int min;
	private int max;

	@Override
	public void initialize(Port constraintAnnotation) {
		Size size = constraintAnnotation.getClass().getAnnotation(Size.class);
		this.min = size.min();
		this.max = size.max();
	}

	@Override
	public boolean isValid(Integer number, ConstraintValidatorContext context) {
		return number != null && Numbers.between(number, min, max);
	}

}
