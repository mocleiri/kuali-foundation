package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.validate.annotation.ValidType;

import com.google.common.base.Optional;

public class ValidTypeValidator extends AbstractFieldsValidator<ValidType, Object> {

	private Class<?> superType; // eg java.util.Map
	private Class<?> type; // eg com.google.common.collect.ImmutableMap
	private Class<?>[] excludes;

	@Override
	public void initialize(ValidType constraintAnnotation) {
		this.superType = constraintAnnotation.superType();
		this.type = constraintAnnotation.type();
		this.excludes = constraintAnnotation.exclude();
		// Make sure type descends from superType
		ReflectionUtils.validateIsSuperType(superType, type);
	}

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		// Figure out if we even need to validate this field
		if (isSkip(field)) {
			// If not, there is nothing more to do
			return Optional.absent();
		}

		// Otherwise, make sure it descends from the correct type
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

	/**
	 * Return true if this field needs validation, false otherwise
	 */
	protected boolean isSkip(Field field) {

		// If this field does not descend from superType, we can skip checking it's type
		if (!ReflectionUtils.isSuperType(superType, field.getType())) {
			return true;
		}

		// If it is an explicitly configured exclusion, we can skip checking its type
		for (Class<?> exclusion : excludes) {
			if (field.getType() == exclusion) {
				return true;
			}
		}

		// If we get here we need to validate this field
		return false;

	}

}
