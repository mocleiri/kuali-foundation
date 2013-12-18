package org.kuali.common.util.validate;

import java.lang.reflect.Field;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.kuali.common.util.ReflectionUtils;

public class MatchDeclaringClassFieldsValidator implements ConstraintValidator<MatchingFields, Object> {

	boolean skip = false;
	boolean includeInheritedFields = true;

	@Override
	public void initialize(MatchingFields constraintAnnotation) {
		this.skip = constraintAnnotation.skip();
		this.includeInheritedFields = constraintAnnotation.includeInheritedFields();
	}

	@Override
	public boolean isValid(Object instance, ConstraintValidatorContext constraintContext) {

		// Explicit skip was requested
		if (skip) {
			return true;
		}

		// There might not be a declaring class
		Class<?> declaringClass = instance.getClass().getDeclaringClass();
		if (declaringClass == null) {
			return true;
		}

		// There might not be a declaring class
		Set<Field> fields = ReflectionUtils.getFields(instance.getClass(), includeInheritedFields);
		if (!verifyFields(fields, instance.getClass(), constraintContext)) {
			return false;
		}
		Set<Field> declaringClassFields = ReflectionUtils.getFields(declaringClass, includeInheritedFields);
		if (!verifyFields(declaringClassFields, declaringClass, constraintContext)) {
			return false;
		}
		return true;
	}

	protected boolean verifyFields(Set<Field> fields, Class<?> type, ConstraintValidatorContext constraintContext) {
		if (ReflectionUtils.hasUniqueFieldNames(fields)) {
			return true;
		} else {
			String error = "[" + ReflectionUtils.getDeclarationPath(type) + "] field names are not unique";
			constraintContext.buildConstraintViolationWithTemplate(error).addConstraintViolation();
			return false;
		}
	}

}
