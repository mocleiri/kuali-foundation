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
		if (CharSequence.class.isAssignableFrom(field.getClass())) {
			return handleCharSequence(field, instance);
		} else if (Optional.class.isAssignableFrom(field.getClass())) {
			return handleOptional(field, instance);
		} else {
			return Optional.absent();
		}
	}

	protected Optional<String> handleCharSequence(Field field, Object instance) {

		// Extract the value of this field on this object
		Optional<?> optional = ReflectionUtils.get(field, instance);

		// It can only be a CharSequence at this point
		CharSequence charSequence = (CharSequence) optional.orNull();

		// If it's blank return an error message
		return getBlankCharSequenceErrorMessage(field, charSequence);
	}

	protected Optional<String> handleOptional(Field field, Object instance) {

		// Determine if there is a value for this field on this object
		Optional<?> fieldValue = ReflectionUtils.get(field, instance);

		if (!fieldValue.isPresent()) {
			
			// There is no value for this field on this object (ie the field is null)
			return Optional.absent();
		} else {
			
			// The field contains a non-null optional that we need to examine
			Optional<?> optional = (Optional<?>) fieldValue.get();

			// Examine the optional to see if it contains a blank string
			return handleOptionalField(field, optional);
		}

	}

	protected Optional<String> handleOptionalField(Field field, Optional<?> optional) {
		
		// The optional does not contain a value
		if (!optional.isPresent()) {
			return Optional.absent();
		}

		// Extract whatever value the optional contains
		Object value = optional.get();

		// If it's a CharSequence we need to examine it further
		if (value instanceof CharSequence) {
			// Cast to a CharSequence
			CharSequence charSequence = (CharSequence) value;
			// If the CharSequence is blank, return an error message
			return getBlankCharSequenceErrorMessage(field, charSequence);
		} else {
			// If it's not a char sequence, we don't care what it is, always return the absence of an error message
			return Optional.absent();
		}
	}

	/**
	 * If the charSequence is blank, return an error message, otherwise return Optional.absent()
	 */
	protected Optional<String> getBlankCharSequenceErrorMessage(Field field, CharSequence charSequence) {
		if (StringUtils.isBlank(charSequence)) {
			return Optional.of(getErrorMessage(field, "cannot be blank"));
		} else {
			return Optional.absent();
		}
	}

}
