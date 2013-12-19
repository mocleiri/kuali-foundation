package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;

import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class ValidRuntimeTypeValidator extends AbstractFieldsValidator implements ConstraintValidator<ValidRuntimeType, Object> {

	private Class<?> baseType;
	private Class<?> requiredRuntimeBaseType;

	@Override
	public void initialize(ValidRuntimeType constraintAnnotation) {
		this.baseType = constraintAnnotation.baseType();
		this.requiredRuntimeBaseType = constraintAnnotation.requiredRuntimeBaseType();
	}

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		// Check to see if this field's type is derived from the declared type we are checking
		if (!baseType.isAssignableFrom(field.getType())) {
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

		// Extract the runtime type of this instance
		Class<?> runtimeType = value.getClass();

		// Make sure it's assignable from the type we want
		if (requiredRuntimeBaseType.isAssignableFrom(runtimeType)) {
			// If it's assignable, we are good to go
			return Optional.absent();
		} else {
			// If not, return an error message
			String required = requiredRuntimeBaseType.getCanonicalName();
			String runtime = runtimeType.getCanonicalName();
			return ValidationUtils.errorMessage(field, "Invalid runtime type: [" + required + "] is not a super class of [" + runtime + "]");
		}
	}

}
