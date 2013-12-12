package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import com.google.common.base.Optional;

public class NoNullsValidator extends ClassValidator {

	@Override
	protected Optional<String> validate(Field field, Object instance) {
		Optional<?> value = get(field, instance);
		if (value.isPresent()) {
			return Optional.absent();
		} else {
			return Optional.of("'" + field.getName() + "' cannot be null");
		}
	}

}
