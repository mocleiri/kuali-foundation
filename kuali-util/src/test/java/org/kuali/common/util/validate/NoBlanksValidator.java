package org.kuali.common.util.validate;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.validation.ConstraintValidator;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.ReflectionUtils;

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
	}

	@Override
	protected Optional<String> validate(Field field, Object instance) {
		if (isCharSequence(field)) {
			return validateCharSequence(field, instance);
		} else if (checkOptionals && isOptional(field)) {
			return validateOptional(field, instance);
		} else if (checkCollections && isCollection(field)) {
			return validateCollection(field, instance);
		} else if (checkMaps && isMap(field)) {
			return validateMap(field, instance);
		} else {
			return Optional.absent();
		}
	}

	/**
	 * Return true if this field is a CharSequence
	 */
	protected boolean isCollection(Field field) {
		return Collection.class.isAssignableFrom(field.getClass());
	}

	/**
	 * Return true if this field is a CharSequence
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

	protected Optional<String> validateCharSequence(Field field, Object instance) {

		// Extract the value of this field on this object
		Optional<?> optional = ReflectionUtils.get(field, instance);

		// It must be a CharSequence at this point
		CharSequence charSequence = (CharSequence) optional.orNull();

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

	protected Optional<String> validateMap(Field field, Object instance) {

		// Determine if there is a value for this field on this object
		Optional<?> fieldValue = ReflectionUtils.get(field, instance);
		if (!fieldValue.isPresent()) {
			// If not, return the absence of an error message
			return Optional.absent();
		} else {
			// The field contains a non-null map that we need to examine
			Map<?, ?> map = (Map<?, ?>) fieldValue.get();
			int blankKeys = 0;
			int blankVals = 0;
			for (Map.Entry<?, ?> entry : map.entrySet()) {
				Object key = entry.getKey();
				Object val = entry.getValue();
				if (key instanceof CharSequence) {
					blankKeys = StringUtils.isBlank((CharSequence) key) ? blankKeys++ : blankKeys;
				}
				if (val instanceof CharSequence) {
					blankVals = StringUtils.isBlank((CharSequence) val) ? blankVals++ : blankVals;
				}
			}
			if (blankKeys > 0 || blankVals > 0) {
				String errorMessage = getMapBlanksErrorMessage(blankKeys, blankVals);
				return Optional.of(getErrorMessage(field, errorMessage));
			} else {
				return Optional.absent();
			}
		}
	}

	protected String getMapBlanksErrorMessage(int keyCount, int valCount) {
		StringBuilder sb = new StringBuilder();
		sb.append("contains");
		if (keyCount > 0) {
			sb.append(keyCount + " blank keys");
		}
		if (valCount > 0) {
			if (keyCount > 0) {
				sb.append(" and ");
			}
			sb.append(valCount + " blank values");
		}
		return sb.toString();
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
