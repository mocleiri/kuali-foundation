package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class NoBlankOptionalsValidator extends AbstractFieldsValidator<NoBlankOptionals, Object> {

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		// This field may not be a type we can validate
		if (!ReflectionUtils.isOptional(field)) {
			return Optional.absent();
		}

		// Extract the value of the field into an optional
		Optional<?> fieldValue = ReflectionUtils.get(field, instance);

		// If there is no value for this field, we are done
		if (!fieldValue.isPresent()) {
			return Optional.absent();
		}

		// Extract the object inside the field (we know it's an optional at this point)
		Optional<?> optional = (Optional<?>) fieldValue.get();

		// The optional does not contain a value
		if (!optional.isPresent()) {
			return Optional.absent();
		}

		// Extract whatever value the optional contains
		Object value = optional.get();

		// If it's a CharSequence we need to examine it further
		if (!ReflectionUtils.isCharSequence(value.getClass())) {
			return Optional.absent();
		}

		// Cast to a CharSequence
		CharSequence cs = (CharSequence) value;

		// If the char sequence is not blank, we are good to go
		if (!StringUtils.isBlank(cs)) {
			return Optional.absent();
		}

		// If the CharSequence is blank, return an error message
		return ValidationUtils.errorMessage(field, "optional value cannot be blank");
	}

}
