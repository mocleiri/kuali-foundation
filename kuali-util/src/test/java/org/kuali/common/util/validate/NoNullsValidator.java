package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;


import com.google.common.base.Optional;

public class NoNullsValidator extends AbstractFieldsValidator implements ConstraintValidator<NoNulls, Object> {

	@Override
	public void initialize(NoNulls constraintAnnotation) {
		this.skip = constraintAnnotation.skip();
	}

	@Override
	protected Optional<String> validate(Field field, Object instance) {
		Optional<?> value = get(field, instance);
		if (value.isPresent()) {
			return Optional.absent();
		} else {
			return Optional.of(getErrorMessage(field, instance, "cannot be null"));
		}
	}
}
