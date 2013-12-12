package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import org.kuali.common.util.validate.annotation.NoNulls;

import com.google.common.base.Optional;

public final class DefaultValidationService implements ValidationService {

	@Override
	public Optional<Errors> validate(Object instance) {
		Optional<NoNulls> annotation = Optional.fromNullable(instance.getClass().getAnnotation(NoNulls.class));
		Field[] fields = instance.getClass().getDeclaredFields();
		for (Field field : fields) {
			validate(instance, field);
		}

		return null;
	}

	protected Optional<Errors> validate(Object instance, Field field) {
		Optional<NoNulls> annotation = Optional.fromNullable(field.getAnnotation(NoNulls.class));
		if (annotation.isPresent()) {

		}
		return null;
	}

}
