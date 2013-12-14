package org.kuali.common.util.validate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidatorContext;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

/**
 * <p>
 * This class contains boilerplate code for examining every declared field for a given object's class hierarchy.
 * </p>
 * 
 * <p>
 * It recursively traverses the class hierarchy to acquire an exhaustive list of every declared field in the hierarchy and provides a hook for validating each field in turn.
 * </p>
 */
public abstract class AbstractFieldsValidator {

	boolean skip = false;
	boolean includeInheritedFields = true;

	public boolean isValid(Object instance, ConstraintValidatorContext constraintContext) {
		if (skip) {
			return true;
		}
		Assert.notNull(instance, "'instance' cannot be null");
		List<Field> fields = ReflectionUtils.getDeclaredFields(instance.getClass(), includeInheritedFields);
		List<String> errors = new ArrayList<String>();
		for (Field field : fields) {
			Optional<SkipFieldValidation> annotation = ReflectionUtils.getAnnotation(instance.getClass(), SkipFieldValidation.class);
			if (annotation.isPresent()) {
				continue;
			}
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

}
