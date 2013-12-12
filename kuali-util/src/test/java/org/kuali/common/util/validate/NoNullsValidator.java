package org.kuali.common.util.validate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.validate.annotation.NoNulls;

import com.google.common.base.Optional;

public class NoNullsValidator implements ConstraintValidator<NoNulls, Object> {

	private boolean skip;

	@Override
	public void initialize(NoNulls constraintAnnotation) {
		this.skip = constraintAnnotation.skip();
	}

	@Override
	public boolean isValid(Object instance, ConstraintValidatorContext constraintContext) {
		if (skip) {
			return true;
		}
		if (instance == null) {
			throw new IllegalStateException("instance cannot be null");
		}
		Field[] fields = instance.getClass().getDeclaredFields();
		List<String> errors = new ArrayList<String>();
		for (Field field : fields) {
			Optional<String> error = validate(field, instance);
			if (error.isPresent()) {
				errors.add(error.get());
			}
		}

		if (errors.size() == 0) {
			return true;
		} else {
			constraintContext.disableDefaultConstraintViolation();
			constraintContext.buildConstraintViolationWithTemplate(CollectionUtils.asCSV(errors)).addConstraintViolation();
			return false;
		}
	}

	protected Optional<?> get(Field field, Object instance) {
		try {
			synchronized (field) {
				boolean accessible = field.isAccessible();
				if (!accessible) {
					field.setAccessible(true);
				}
				Object value = field.get(instance);
				if (!accessible) {
					field.setAccessible(false);
				}
				return Optional.fromNullable(value);
			}
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

	protected Optional<String> validate(Field field, Object instance) {
		Optional<?> value = get(field, instance);
		if (value.isPresent()) {
			return Optional.absent();
		} else {
			String className = instance.getClass().getSimpleName();
			return Optional.of("[" + className + "." + field.getName() + "] cannot be null");
		}
	}

}
