package org.kuali.common.util.validate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidatorContext;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

/**
 * This class contains boilerplate code for examining every declared field given an object instance. It recursively acquires every declared field for instance's class hierarchy and
 * provides a hook for validating each field in turn
 */
public abstract class AbstractFieldsValidator {

	boolean skip;

	public boolean isValid(Object instance, ConstraintValidatorContext constraintContext) {
		if (skip) {
			return true;
		}
		if (instance == null) {
			throw new IllegalStateException("instance cannot be null");
		}
		List<Field> fields = ReflectionUtils.getAllFields(instance.getClass());
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

	protected String getErrorMessage(Field field, String suffix) {
		String className = field.getDeclaringClass().getSimpleName();
		return "[" + className + "." + field.getName() + "] " + suffix;
	}

	protected abstract Optional<String> validate(Field field, Object instance);

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

}
