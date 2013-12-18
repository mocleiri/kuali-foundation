package org.kuali.common.util.validate;

import java.lang.reflect.Field;
import java.util.Map;
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

		// Examine field info
		FieldInfo result1 = getFieldInfo(instance.getClass(), includeInheritedFields);
		FieldInfo result2 = getFieldInfo(declaringClass, includeInheritedFields);
		if (!checkUniqueness(constraintContext, result1, result2)) {
			return false;
		}

		return true;
	}

	protected FieldInfo getFieldInfo(Class<?> type, boolean includeInheritedFields) {
		Set<Field> set = ReflectionUtils.getFields(type, includeInheritedFields);
		Map<String, Field> map = ReflectionUtils.getNameMap(set);
		return FieldInfo.builder(type).withSet(set).withMap(map).build();
	}

	protected boolean checkUniqueness(ConstraintValidatorContext constraintContext, FieldInfo... results) {
		boolean ok = true;
		for (FieldInfo result : results) {
			Set<Field> set = result.getSet();
			Map<String, Field> map = result.getMap();
			if (set.size() != map.size()) {
				String error = "[" + ReflectionUtils.getDeclarationPath(result.getType()) + "] field names are not unique";
				constraintContext.buildConstraintViolationWithTemplate(error).addConstraintViolation();
				ok = false;
			}
		}
		return ok;
	}

}
