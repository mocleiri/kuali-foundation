package org.kuali.common.core.validate;

import static com.google.common.base.Optional.absent;
import static org.kuali.common.util.ReflectionUtils.extractFieldValue;

import java.lang.reflect.Field;

import org.kuali.common.core.validate.annotation.NoNullFields;

import com.google.common.base.Optional;

public class NoNullFieldsValidator extends AbstractFieldsValidator<NoNullFields, Object> {

	@Override
	protected Optional<String> validate(Field field, Object instance) {
		Optional<?> value = extractFieldValue(field, instance);
		if (value.isPresent()) {
			return absent();
		} else {
			return Validation.errorMessage(field, "cannot be null");
		}
	}
}
