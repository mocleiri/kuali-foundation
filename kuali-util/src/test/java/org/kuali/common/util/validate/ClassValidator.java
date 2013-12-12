package org.kuali.common.util.validate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.validate.annotation.NoNulls;
import org.springframework.util.Assert;

import com.google.common.base.Optional;

public abstract class ClassValidator implements Validator {

	@Override
	public ValidationResult validate(Object instance) {
		Assert.notNull(instance, "'instance' cannot be null");
		Optional<NoNulls> annotation = Optional.fromNullable(instance.getClass().getAnnotation(NoNulls.class));
		if (!annotation.isPresent()) {
			return new ValidationResult();
		}
		Field[] fields = instance.getClass().getDeclaredFields();
		List<String> errors = new ArrayList<String>();
		for (Field field : fields) {
			Optional<String> error = validate(field, instance);
			if (error.isPresent()) {
				errors.add(error.get());
			}
		}
		return new ValidationResult(errors);
	}

	protected abstract Optional<String> validate(Field field, Object instance);

	protected Optional<?> get(Field field, Object instance) {
		try {
			Object value = null;
			synchronized (field) {
				boolean accessible = field.isAccessible();
				if (!accessible) {
					field.setAccessible(true);
				}
				value = field.get(instance);
				if (!accessible) {
					field.setAccessible(false);
				}
				return Optional.of(value);
			}
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

}
