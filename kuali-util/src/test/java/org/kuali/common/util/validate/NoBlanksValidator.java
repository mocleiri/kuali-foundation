package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class NoBlanksValidator extends AbstractFieldsValidator implements ConstraintValidator<NoBlanks, Object> {

	@Override
	public void initialize(NoBlanks constraintAnnotation) {
		this.skip = constraintAnnotation.skip();
		this.includeInheritedFields = constraintAnnotation.includeInheritedFields();
	}

	@Override
	protected Optional<String> validate(Field field, Object instance) {
		if (field.getType() == String.class) {
			return handleString(field, instance);
		} else if (field.getType() == Optional.class) {
			return handleOptional(field, instance);
		} else {
			return Optional.absent();
		}
	}

	protected Optional<String> handleString(Field field, Object instance) {
		Optional<?> optional = ReflectionUtils.get(field, instance);
		String string = (String) optional.orNull();
		return getBlankStringErrorMessage(field, string);
	}

	protected Optional<String> handleOptional(Field field, Object instance) {
		Optional<?> optional = ReflectionUtils.get(field, instance);
		if (!optional.isPresent()) {
			return Optional.absent();
		}
		Object value = optional.get();
		if (value instanceof String) {
			String string = (String) value;
			return getBlankStringErrorMessage(field, string);
		} else {
			return Optional.absent();
		}
	}

	protected Optional<String> getBlankStringErrorMessage(Field field, String string) {
		if (StringUtils.isBlank(string)) {
			return Optional.of(getErrorMessage(field, "cannot be blank"));
		} else {
			return Optional.absent();
		}
	}

}
