package org.kuali.common.util.validate;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.validation.ConstraintValidator;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.collect.MapUtils;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;

public class NoBlanksValidator extends AbstractFieldsValidator implements ConstraintValidator<NoBlanks, Object> {

	private boolean checkOptionals;
	private boolean checkCollections;
	private boolean checkMaps;

	@Override
	public void initialize(NoBlanks constraintAnnotation) {
		this.skip = constraintAnnotation.skip();
		this.includeInheritedFields = constraintAnnotation.includeInheritedFields();
		this.checkOptionals = constraintAnnotation.checkOptionals();
		this.checkCollections = constraintAnnotation.checkCollections();
		this.checkMaps = constraintAnnotation.checkMaps();
	}

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		if (!isValidatableField(field)) {
			return Optional.absent();
		}

		Optional<?> fieldValue = ReflectionUtils.get(field, instance);

		if (isCharSequence(field)) {
			return validateCharSequence(field, fieldValue);
		}

		if (!fieldValue.isPresent()) {
			return Optional.absent();
		}

		if (checkOptionals && isOptional(field)) {
			return validateOptional(field, fieldValue);
		} else if (checkCollections && isCollection(field)) {
			return validateCollection(field, instance);
		} else if (checkMaps && isMap(field)) {
			return validateMap(field, (Map<?, ?>) fieldValue.get());
		} else {
			return Optional.absent();
		}
	}

	protected boolean isValidatableField(Field field) {
		if (isCharSequence(field)) {
			return true;
		} else if (isCollection(field)) {
			return true;
		} else if (isOptional(field)) {
			return true;
		} else {
			return MapUtils.isMap(field.getClass());
		}

	}

	/**
	 * Return true if this field is a Collection
	 */
	protected boolean isCollection(Field field) {
		Maps.asMap(null, null);
		return Collection.class.isAssignableFrom(field.getClass());
	}

	/**
	 * Return true if this field is a Map
	 */
	protected boolean isMap(Field field) {
		return Map.class.isAssignableFrom(field.getClass());
	}

	/**
	 * Return true if this field is a CharSequence
	 */
	protected boolean isCharSequence(Field field) {
		return CharSequence.class.isAssignableFrom(field.getClass());
	}

	/**
	 * Return true if this field is an Optional
	 */
	protected boolean isOptional(Field field) {
		return Optional.class.isAssignableFrom(field.getClass());
	}

	protected Optional<String> validateCharSequence(Field field, Optional<?> fieldValue) {
		// It must be a CharSequence at this point
		CharSequence charSequence = (CharSequence) fieldValue.orNull();

		// If it's blank return an error message
		return getBlankCharSequenceErrorMessage(field, charSequence);
	}

	protected Optional<String> validateOptional(Field field, Object instance) {

		// Determine if there is a value for this field on this object
		Optional<?> fieldValue = ReflectionUtils.get(field, instance);
		if (!fieldValue.isPresent()) {
			// If not, return the absence of an error message
			return Optional.absent();
		} else {
			// The field contains a non-null optional that we need to examine
			Optional<?> optional = (Optional<?>) fieldValue.get();

			// Examine the optional to see if it contains a blank string
			return handleOptionalField(field, optional);
		}

	}

	protected Optional<String> validateMap(Field field, Map<?, ?> map) {
		Map<?, ?> blanks = MapUtils.getBlankOrNullEntries(map);
		if (blanks.size() > 0) {
			return Optional.of(getErrorMessage(field, "contains " + blanks.size() + " entries with a blank key or value"));
		} else {
			return Optional.absent();
		}
	}

	protected Optional<String> validateCollection(Field field, Object instance) {

		// Determine if there is a value for this field on this object
		Optional<?> fieldValue = ReflectionUtils.get(field, instance);
		if (!fieldValue.isPresent()) {
			// If not, return the absence of an error message
			return Optional.absent();
		} else {
			// The field contains a non-null optional that we need to examine
			Collection<?> collection = (Collection<?>) fieldValue.get();

			Iterator<?> iterator = collection.iterator();
			int count = 0;
			while (iterator.hasNext()) {
				Object value = iterator.next();
				if (value instanceof CharSequence) {
					count = StringUtils.isBlank((CharSequence) value) ? count++ : count;
				} else {
					return Optional.absent();
				}
			}

			if (count > 0) {
				return Optional.of(getErrorMessage(field, "contains " + count + " blank values"));
			} else {
				return Optional.absent();
			}
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
