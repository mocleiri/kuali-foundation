package org.kuali.common.core.validate;

import static com.google.common.base.Optional.absent;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.kuali.common.util.ReflectionUtils.extractFieldValue;
import static org.kuali.common.util.ReflectionUtils.isOptionalString;

import java.lang.reflect.Field;

import org.kuali.common.core.validate.annotation.IgnoreBlanks;
import org.kuali.common.core.validate.annotation.NoBlankOptionals;

import com.google.common.base.Optional;

public class NoBlankOptionalsValidator extends AbstractFieldsValidator<NoBlankOptionals, Object> {

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		// Might be ignoring blanks on this particular field
		if (field.getAnnotation(IgnoreBlanks.class) != null) {
			return absent();
		}

		// If this field isn't an Optional<String> we are good to go
		if (!isOptionalString(field)) {
			return absent();
		}

		// Get the value of the field as an optional
		Optional<?> fieldValue = extractFieldValue(field, instance);

		// If the field was null we are good to go
		if (!fieldValue.isPresent()) {
			return absent();
		}

		// Extract the object inside the field (we know it's an Optional<String> at this point)
		@SuppressWarnings("unchecked")
		Optional<String> optional = (Optional<String>) fieldValue.get();

		// If there is no value inside the optional we are good to go
		if (!optional.isPresent()) {
			return absent();
		}

		// Extract the string from the optional
		String string = optional.get();

		// If it's not blank, we are good to go
		if (!isBlank(string)) {
			return absent();
		}

		// Otherwise, return an error message
		return Validation.errorMessage(field, "optional string value cannot be blank");
	}

}
