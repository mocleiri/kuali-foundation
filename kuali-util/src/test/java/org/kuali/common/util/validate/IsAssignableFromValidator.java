package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;

import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class IsAssignableFromValidator extends AbstractFieldsValidator implements ConstraintValidator<IsAssignableFrom, Object> {

	private Class<?> type;
	private Class<?> superType;

	@Override
	public void initialize(IsAssignableFrom constraintAnnotation) {
		this.type = constraintAnnotation.type();
		this.superType = constraintAnnotation.superType();
	}

	@Override
	protected Optional<String> validate(Field field, Object instance) {
		if (!type.isAssignableFrom(field.getType())) {
			return Optional.absent();
		}

		// Extract the value of the field into an Optional
		Optional<?> fieldValue = ReflectionUtils.get(field, instance);

		// There is no value for this field (ie it was null)
		if (!fieldValue.isPresent()) {
			return Optional.absent();
		}

		// Get the actual object reference
		Object value = fieldValue.get();

		Class<?> runtimeType = value.getClass();

		if (superType.isAssignableFrom(runtimeType)) {
			// If it's assignable, we are good to go
			return Optional.absent();
		} else {
			// If not, return an error message
			return ValidationUtils.errorMessage(field, "is not assignable from [" + superType.getCanonicalName() + "]");
		}
	}

}
