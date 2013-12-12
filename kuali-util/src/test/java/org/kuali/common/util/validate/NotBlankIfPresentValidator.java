package org.kuali.common.util.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Optional;

public class NotBlankIfPresentValidator implements ConstraintValidator<NotBlankIfPresent, Optional<String>> {

	@Override
	public void initialize(NotBlankIfPresent constraintAnnotation) {
	}

	@Override
	public boolean isValid(Optional<String> value, ConstraintValidatorContext context) {
		if (value == null || !value.isPresent()) {
			return true;
		} else {
			return !StringUtils.isBlank(value.get());
		}
	}

}
