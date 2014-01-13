package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class NoBlankOptionalsValidator extends AbstractFieldsValidator<NoBlankOptionals, Object> {

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		// If this field isn't an Optional<String> we are good to go
		if (!ReflectionUtils.isOptionalString(field)) {
			return Optional.absent();
		}

		// Get the value of the field as an optional
		Optional<?> fieldValue = ReflectionUtils.get(field, instance);

		// If the field was null we are good to go
		if (!fieldValue.isPresent()) {
			return Optional.absent();
		}

		// Extract the object inside the field (we know it's an Optional<String> at this point)
		@SuppressWarnings("unchecked")
		Optional<String> optional = (Optional<String>) fieldValue.get();

		// If there is no value inside the optional we are good to go
		if (!optional.isPresent()) {
			return Optional.absent();
		}

		// Extract the string from the optional
		String string = optional.get();

		// If it's not blank, we are good to go
		if (!StringUtils.isBlank(string)) {
			return Optional.absent();
		}

		// Otherwise, return an error message
		return Validation.errorMessage(field, "optional string value cannot be blank");
	}

}
