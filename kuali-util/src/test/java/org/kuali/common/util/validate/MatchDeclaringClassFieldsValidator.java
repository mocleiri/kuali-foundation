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
		Class<?> declaringClass = instance.getClass().getDeclaringClass();
		if (declaringClass == null) {
			return true;
		}
		Set<Field> fields = ReflectionUtils.getAllFields(instance.getClass());
		Set<Field> declaringClassFields = ReflectionUtils.getAllFields(declaringClass);
		return false;
	}

}
