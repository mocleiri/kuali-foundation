package org.kuali.common.util.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

/**
 * <p>
 * This class contains boilerplate code for examining every declared field for a given object's class hierarchy.
 * </p>
 * 
 * <p>
 * It recursively traverses the class hierarchy to acquire an exhaustive list of every declared field in the hierarchy and provides a hook for validating each field in turn.
 * </p>
 */
public abstract class AbstractFieldsValidator<A extends Annotation, T> implements ConstraintValidator<A, T> {

	@Override
	public void initialize(A constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {
		Preconditions.checkNotNull(object, "'object' cannot be null");
		Preconditions.checkNotNull(constraintContext, "'constraintContext' cannot be null");
		Set<Field> fields = ReflectionUtils.getAllFields(object.getClass());
		List<String> errors = new ArrayList<String>();
		for (Field field : fields) {
			Optional<String> error = validate(field, object);
			if (error.isPresent()) {
				errors.add(error.get());
			}
		}
		if (errors.size() == 0) {
			return true;
		} else {
			constraintContext.disableDefaultConstraintViolation();
			for (String error : errors) {
				constraintContext.buildConstraintViolationWithTemplate(error).addConstraintViolation();
			}
			return false;
		}
	}

	protected abstract Optional<String> validate(Field field, Object instance);

}
