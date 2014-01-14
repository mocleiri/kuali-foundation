package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class ValidTypeValidator extends AbstractFieldsValidator<ValidType, Object> {

	private Class<?> superType; // eg java.util.Map
	private Class<?> type; // eg com.google.common.collect.ImmutableMap

	@Override
	public void initialize(ValidType constraintAnnotation) {
		this.superType = constraintAnnotation.superType();
		this.type = constraintAnnotation.type();
		// Make sure type descends from superType
		ReflectionUtils.validateIsSuperType(superType, type);
	}

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		// If this field does not descend from superType, we can skip checking it's type
		boolean skip = !ReflectionUtils.isSuperType(superType, field.getType());

		if (skip) {
			// Nothing more to do
			return Optional.absent();
		}

		// Make sure it descends from the correct type
		if (ReflectionUtils.isSuperType(type, field.getType())) {
			// If it does, we are good to go
			return Optional.absent();
		} else {
			// If not, return an error message
			String fieldType = field.getType().getCanonicalName();
			String suffix = "Invalid type: [" + fieldType + "] must descend from (or be) [" + type.getCanonicalName() + "]";
			return Validation.errorMessage(field, suffix);
		}

	}

}
