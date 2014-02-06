package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class NoBlankStringsValidator extends AbstractFieldsValidator<NoBlankStrings, Object> {

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		// This field may not be a String
		if (!ReflectionUtils.isString(field)) {
			return Optional.absent();
		}

		// Extract the value of the field into an optional
		Optional<?> fieldValue = ReflectionUtils.get(field, instance);

		// We know the field contains a string at this point
		String string = (String) fieldValue.orNull();

		// Null is ok
		if (string == null) {
			return Optional.absent();
		}

		// Non-null value cannot be blank
		if (StringUtils.isBlank(string)) {
			return Validation.errorMessage(field, "blank strings not allowed");
		} else {
			return Optional.absent();
		}
	}

}
