package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class NoBlankStringsValidator extends AbstractFieldsValidator<NoBlankElements, Object> {

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		// This field may not be a char sequence
		if (!ReflectionUtils.isCharSequence(field)) {
			return Optional.absent();
		}

		// Extract the value of the field into an optional
		Optional<?> fieldValue = ReflectionUtils.get(field, instance);

		// We know the field contains an CharSequence at this point
		CharSequence cs = (CharSequence) fieldValue.orNull();

		// Null is ok
		if (cs == null) {
			return Optional.absent();
		}

		// Non-null value cannot be blank
		if (StringUtils.isBlank(cs)) {
			return ValidationUtils.errorMessage(field, "blank strings not allowed");
		} else {
			return Optional.absent();
		}
	}

}
