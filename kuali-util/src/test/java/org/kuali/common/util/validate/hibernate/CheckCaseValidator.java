package org.kuali.common.util.validate.hibernate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckCaseValidator implements ConstraintValidator<CheckCase, String> {

	private CaseMode caseMode;

	@Override
	public void initialize(CheckCase constraintAnnotation) {
		this.caseMode = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(String string, ConstraintValidatorContext constraintContext) {

		if (string == null) {
			return true;
		}

		boolean isValid = isValid(string);
		if (!isValid) {
			constraintContext.disableDefaultConstraintViolation();
			String messageTemplate = "{org.hibernate.validator.referenceguide.chapter06.CheckCase.message}";
			constraintContext.buildConstraintViolationWithTemplate(messageTemplate).addConstraintViolation();
		}

		return isValid;
	}

	protected boolean isValid(String string) {
		switch (caseMode) {
		case UPPER:
			return string.equals(string.toUpperCase());
		case LOWER:
			return string.equals(string.toLowerCase());
		default:
			throw new IllegalStateException("[" + caseMode + "] is unknown");
		}
	}
}