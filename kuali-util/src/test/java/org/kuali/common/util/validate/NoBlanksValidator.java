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

		// Extract the value of this field on this object
		Optional<?> optional = ReflectionUtils.get(field, instance);

		// It can only be a string at this point
		String string = (String) optional.orNull();

		// If it's blank return an error message
		return getBlankStringErrorMessage(field, string);
	}

	protected Optional<String> handleOptional(Field field, Object instance) {

		// Extract the value of this field on this object
		Optional<?> optional = ReflectionUtils.get(field, instance);

		// If this field on this object is null, we are done
		if (!optional.isPresent()) {
			return Optional.absent();
		}

		// The value of this field can only be an Optional at this point
		Optional<?> value = (Optional<?>) optional.get();

		// If the optional does not contain a value, we are done
		if (!value.isPresent()) {
			return Optional.absent();
		}

		// We've located an optional that contains a non-null string value
		String string = (String) value.get();

		// If that string is blank, return an error message
		return getBlankStringErrorMessage(field, string);
	}

	/**
	 * If the string is blank, return an error message, otherwise return Optional.absent()
	 */
	protected Optional<String> getBlankStringErrorMessage(Field field, String string) {
		if (StringUtils.isBlank(string)) {
			return Optional.of(getErrorMessage(field, "cannot be blank"));
		} else {
			return Optional.absent();
		}
	}

}
