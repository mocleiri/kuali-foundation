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

		// Get field details
		FieldDetail instanceDetail = getFieldInfo(instance.getClass(), includeInheritedFields);
		FieldDetail declaringClassDetail = getFieldInfo(declaringClass, includeInheritedFields);

		// Make sure the field names are unique for both
		if (!checkUniqueness(constraintContext, instanceDetail, declaringClassDetail)) {
			return false;
		}

		return true;
	}

	protected FieldDetail getFieldInfo(Class<?> type, boolean includeInheritedFields) {
		Set<Field> set = ReflectionUtils.getFields(type, includeInheritedFields);
		Map<String, Field> map = ReflectionUtils.getNameMap(set);
		return FieldDetail.builder(type).withSet(set).withMap(map).build();
	}

	protected boolean checkUniqueness(ConstraintValidatorContext constraintContext, FieldDetail... details) {
		boolean unique = true;
		for (FieldDetail detail : details) {
			Set<Field> set = detail.getSet();
			Map<String, Field> map = detail.getMap();
			if (set.size() != map.size()) {
				String error = "[" + ReflectionUtils.getDeclarationPath(detail.getType()) + "] field names are not unique";
				constraintContext.buildConstraintViolationWithTemplate(error).addConstraintViolation();
				unique = false;
			}
		}
		return unique;
	}

}
