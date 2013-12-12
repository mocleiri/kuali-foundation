package org.kuali.common.util.validate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.validate.annotation.NoNulls;
import org.springframework.util.Assert;

import com.google.common.base.Optional;

public class NoNullsValidator implements Validator {

	@Override
	public Optional<Errors> validate(Object instance) {
		Assert.notNull(instance, "'instance' cannot be null");
		Optional<NoNulls> annotation = Optional.fromNullable(instance.getClass().getAnnotation(NoNulls.class));
		if (!annotation.isPresent()) {
			return Optional.absent();
		}
		Field[] fields = instance.getClass().getDeclaredFields();
		List<String> errors = new ArrayList<String>();
		for (Field field : fields) {
			Optional<String> error = validate(field, instance);
			if (error.isPresent()) {
				errors.add(error.get());
			}
		}
		if (errors.size() > 0) {
			return Optional.of(new Errors(errors));
		} else {
			return Optional.absent();
		}
	}

	protected Optional<String> validate(Field field, Object instance) {
		Optional<Object> value = getValue(field, instance);
		if (value.isPresent()) {
			return Optional.absent();
		} else {
			return Optional.of("'" + field.getName() + "' cannot be null");
		}
	}

	protected Optional<Object> getValue(Field field, Object instance) {
		synchronized (field) {
			boolean accessible = field.isAccessible();
			if (!accessible) {
				field.setAccessible(true);
			}
			Object value = get(field, instance);
			if (!accessible) {
				field.setAccessible(false);
			}
			return Optional.fromNullable(value);
		}
	}

	protected Object get(Field field, Object instance) {
		try {
			return field.get(instance);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

}
