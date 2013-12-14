package org.kuali.common.util.validate;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import javax.validation.ConstraintValidator;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.collect.CollectionUtils;
import org.kuali.common.util.collect.MapUtils;

import com.google.common.base.Optional;

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

		if (!isValidatableType(field)) {
			return Optional.absent();
		}

		Optional<?> fieldValue = ReflectionUtils.get(field, instance);

		if (isCharSequence(field)) {
			CharSequence charSequence = (CharSequence) fieldValue.orNull();
			return getOptionalErrorMessage(field, charSequence);
		}

		if (!fieldValue.isPresent()) {
			return Optional.absent();
		}

		if (checkOptionals && isOptional(field)) {
			Optional<?> optional = (Optional<?>) fieldValue.get();
			return validateOptional(field, optional);
		} else if (checkCollections && isCollection(field)) {
			Collection<?> collection = (Collection<?>) fieldValue.get();
			return validateCollection(field, collection);
		} else if (checkMaps && isMap(field)) {
			Map<?, ?> map = (Map<?, ?>) fieldValue.get();
			return validateMap(field, map);
		} else {
			return Optional.absent();
		}
	}

	protected boolean isValidatableType(Field field) {
		if (isCharSequence(field)) {
			return true;
		} else if (isOptional(field)) {
			return true;
		} else if (CollectionUtils.isCollection(field.getClass())) {
			return true;
		} else {
			return MapUtils.isMap(field.getClass());
		}

	}

	protected Optional<String> validateMap(Field field, Map<?, ?> map) {
		Map<?, ?> blanks = MapUtils.getBlankEntries(map);
		if (blanks.size() > 0) {
			return Optional.of(getErrorMessage(field, "contains " + blanks.size() + " entries with a blank key or value"));
		} else {
			return Optional.absent();
		}
	}

	protected Optional<String> validateCollection(Field field, Collection<?> collection) {
		// Examine the collection for blanks
		Collection<String> blanks = CollectionUtils.getBlanks(collection);
		if (blanks.size() > 0) {
			return Optional.of(getErrorMessage(field, "contains " + blanks.size() + " blank elements"));
		} else {
			return Optional.absent();
		}
	}

	protected Optional<String> validateOptional(Field field, Optional<?> optional) {

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
			return getOptionalErrorMessage(field, charSequence);
		} else {
			// If it's not a char sequence, we don't care what it is, always return the absence of an error message
			return Optional.absent();
		}
	}

	/**
	 * If the charSequence is blank, return an error message, otherwise return Optional.absent()
	 */
	protected Optional<String> getOptionalErrorMessage(Field field, CharSequence charSequence) {
		if (StringUtils.isBlank(charSequence)) {
			return Optional.of(getErrorMessage(field, "cannot be blank"));
		} else {
			return Optional.absent();
		}
	}

	/**
	 * Return true if this field is a Collection
	 */
	protected boolean isCollection(Field field) {
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

}
