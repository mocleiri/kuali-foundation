package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;

import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class NoNullsValidator extends AbstractFieldsValidator implements ConstraintValidator<NoNulls, Object> {

	@Override
	public void initialize(NoNulls constraintAnnotation) {
		this.includeInheritedFields = constraintAnnotation.includeInheritedFields();
	}

	@Override
	protected Optional<String> validate(Field field, Object instance) {
		Optional<?> value = ReflectionUtils.get(field, instance);
		if (value.isPresent()) {
			return Optional.absent();
		} else {
			return ValidationUtils.errorMessage(field, "cannot be null");
		}
	}
}
