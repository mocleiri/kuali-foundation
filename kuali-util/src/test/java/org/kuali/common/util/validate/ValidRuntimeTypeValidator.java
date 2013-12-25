package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class ValidRuntimeTypeValidator extends AbstractFieldsValidator<ValidRuntimeType, Object> {

	private Class<?> base;
	private Class<?> required;

	@Override
	public void initialize(ValidRuntimeType constraintAnnotation) {
		this.base = constraintAnnotation.base();
		this.required = constraintAnnotation.required();
		// Make sure required is the same as or descends from base
		ReflectionUtils.validateEqualsOrDescendsFrom(required, base);
	}

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		// If this field does not descend from base, we can skip checking it's runtime type
		boolean skip = !ReflectionUtils.equalsOrDescendsFrom(field.getType(), base);

		// If not, there is nothing more to do
		if (!skip) {
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

		// Make sure it descends from the correct type
		if (ReflectionUtils.equalsOrDescendsFrom(runtimeType, required)) {
			// If it does, we are good to go
			return Optional.absent();
		} else {
			// If not, return an error message
			String runtime = runtimeType.getCanonicalName();
			String suffix = "Invalid runtime type: [" + runtime + "] must descend from (or be) [" + required.getCanonicalName() + "]";
			return ValidationUtils.errorMessage(field, suffix);
		}
	}

}
