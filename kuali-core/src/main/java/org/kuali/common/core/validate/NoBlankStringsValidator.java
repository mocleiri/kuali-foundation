package org.kuali.common.core.validate;

import static com.google.common.base.Optional.absent;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.kuali.common.util.ReflectionUtils.extractFieldValue;

import java.lang.reflect.Field;

import org.kuali.common.core.validate.annotation.IgnoreBlanks;
import org.kuali.common.core.validate.annotation.NoBlankStrings;
import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class NoBlankStringsValidator extends AbstractFieldsValidator<NoBlankStrings, Object> {

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		// Might be ignoring blanks on this particular field
		if (field.getAnnotation(IgnoreBlanks.class) != null) {
			return absent();
		}

		// This field may not be a String
		if (!ReflectionUtils.isString(field)) {
			return absent();
		}

		// Extract the value of the field into an optional
		Optional<?> fieldValue = extractFieldValue(field, instance);

		// We know the field contains a string at this point
		String string = (String) fieldValue.orNull();

		// Null is ok
		if (string == null) {
			return absent();
		}

		// Non-null value cannot be blank
		if (isBlank(string)) {
			return Validation.errorMessage(field, "blank strings not allowed");
		} else {
			return absent();
		}
	}

}
