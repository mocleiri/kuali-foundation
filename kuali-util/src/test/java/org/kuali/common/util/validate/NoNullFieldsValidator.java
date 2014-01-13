package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class NoNullFieldsValidator extends AbstractFieldsValidator<NoNullFields, Object> {

	@Override
	protected Optional<String> validate(Field field, Object instance) {
		Optional<?> value = ReflectionUtils.get(field, instance);
		if (value.isPresent()) {
			return Optional.absent();
		} else {
			return Validation.errorMessage(field, "cannot be null");
		}
	}
}
