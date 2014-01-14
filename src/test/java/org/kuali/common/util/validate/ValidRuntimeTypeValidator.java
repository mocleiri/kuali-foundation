package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class ValidRuntimeTypeValidator extends AbstractFieldsValidator<ValidRuntimeType, Object> {

	private Class<?> superType; // eg java.util.Map
	private Class<?> type; // eg com.google.common.collect.ImmutableMap

	@Override
	public void initialize(ValidRuntimeType constraintAnnotation) {
		this.superType = constraintAnnotation.superType();
		this.type = constraintAnnotation.type();
		// Make sure type descends from superType
		ReflectionUtils.validateIsSuperType(superType, type);
	}

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		// If this field does not descend from superType, we can skip checking it's runtime type
		boolean skip = !ReflectionUtils.isSuperType(superType, field.getType());

		if (skip) {
			// Nothing more to do
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

		// Extract the runtime type
		Class<?> runtimeType = value.getClass();

		// Make sure it descends from the correct type
		if (ReflectionUtils.isSuperType(type, runtimeType)) {
			// If it does, we are good to go
			return Optional.absent();
		} else {
			// If not, return an error message
			String runtime = runtimeType.getCanonicalName();
			String suffix = "Invalid runtime type: [" + runtime + "] must descend from (or be) [" + type.getCanonicalName() + "]";
			return Validation.errorMessage(field, suffix);
		}
	}

}
