package org.kuali.common.util.validate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.SetUtils;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;

public class MatchDeclaringClassFieldsValidator implements ConstraintValidator<MatchDeclaringClassFields, Object> {

	boolean skip = false;
	boolean includeInheritedFields = true;

	@Override
	public void initialize(MatchDeclaringClassFields constraintAnnotation) {
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

		// Get field details for the declaring class
		FieldDetail declaringClassDetail = getFieldDetail(declaringClass, includeInheritedFields);

		// Get field details for the instance class
		FieldDetail instanceDetail = getFieldDetail(instance.getClass(), includeInheritedFields);

		// Make sure the field names are unique for both
		List<String> duplicateErrors = checkForDuplicatedFieldNames(instanceDetail, declaringClassDetail);
		if (duplicateErrors.size() > 0) {
			handleErrors(constraintContext, duplicateErrors);
			return false;
		}

		// Make sure every field from the declaring class exists in the instance class
		// The instance class can have additional fields, but must always contain at least the field from the declaring class
		List<String> missingErrors = checkForMissingFields(declaringClassDetail, instanceDetail);
		if (missingErrors.size() > 0) {
			handleErrors(constraintContext, missingErrors);
			return false;
		}

		// Make sure all of the common fields use the exact same runtime type
		List<String> typeErrors = checkForMatchingTypes(declaringClassDetail, instanceDetail);
		if (typeErrors.size() > 0) {
			handleErrors(constraintContext, typeErrors);
			return false;
		}
		return true;
	}

	protected List<String> checkForMatchingTypes(FieldDetail main, FieldDetail other) {
		Map<String, Field> mainFields = main.getMap();
		Map<String, Field> otherFields = other.getMap();

		List<String> errors = Lists.newArrayList();
		for (String fieldName : mainFields.keySet()) {
			Field mainField = mainFields.get(fieldName);
			Field otherField = otherFields.get(fieldName);
			Class<?> mainType = mainField.getType();
			Class<?> otherType = otherField.getType();
			if (mainType != otherType) {
				String mainPath = ReflectionUtils.getDeclarationPath(main.getType()) + "." + fieldName;
				String otherPath = ReflectionUtils.getDeclarationPath(other.getType()) + "." + fieldName;
				String mainTypeName = mainType.getSimpleName();
				String otherTypeName = otherType.getSimpleName();
				String error = "Type mismatch [" + mainPath + "] is type [" + mainTypeName + "] but [" + otherPath + "] is type [" + otherTypeName + "]";
				errors.add(error);
			}
		}
		return ImmutableList.copyOf(errors);
	}

	protected List<String> checkForMissingFields(FieldDetail declaringClass, FieldDetail instance) {
		Set<String> declaringClassFieldNames = declaringClass.getMap().keySet();
		Set<String> instanceFieldNames = instance.getMap().keySet();
		Set<String> difference = SetUtils.difference(declaringClassFieldNames, instanceFieldNames);
		if (difference.size() == 0) {
			return ImmutableList.of();
		}
		List<String> errors = Lists.newArrayList();
		for (String missingField : difference) {
			String declaringClassPath = ReflectionUtils.getDeclarationPath(declaringClass.getType());
			String instancePath = ReflectionUtils.getDeclarationPath(instance.getType());
			String error = "[" + declaringClassPath + "] contains field [" + missingField + "], but [" + instancePath + "] does not";
			errors.add(error);
		}
		return ImmutableList.copyOf(errors);
	}

	protected FieldDetail getFieldDetail(Class<?> type, boolean includeInheritedFields) {
		Set<Field> set = ReflectionUtils.getFields(type, includeInheritedFields);
		Map<String, Field> map = ReflectionUtils.getNameMap(set);
		return FieldDetail.builder(type).withSet(set).withMap(map).build();
	}

	protected List<String> checkForDuplicatedFieldNames(FieldDetail... details) {
		List<String> errors = new ArrayList<String>();
		for (FieldDetail detail : details) {
			Set<String> duplicates = getDuplicatedFieldNames(detail.getSet());
			errors.addAll(getDuplicatedFieldNameErrors(detail.getType(), duplicates));
		}
		return errors;
	}

	protected List<String> getDuplicatedFieldNameErrors(Class<?> type, Set<String> duplicates) {
		List<String> errors = new ArrayList<String>();
		for (String duplicate : duplicates) {
			String path = ReflectionUtils.getDeclarationPath(type);
			String error = "In [" + path + "] the field [" + duplicate + "]  appears more than once in the type hierarchy";
			errors.add(error);
		}
		return errors;
	}

	protected Set<String> getDuplicatedFieldNames(Set<Field> fields) {
		Multiset<String> fieldNames = HashMultiset.create();
		for (Field field : fields) {
			fieldNames.add(field.getName());
		}
		Set<String> duplicates = new HashSet<String>();
		for (String fieldName : fieldNames) {
			int count = fieldNames.count(fieldName);
			if (count > 1) {
				duplicates.add(fieldName);
			}
		}
		return ImmutableSet.copyOf(duplicates);
	}

	protected void handleErrors(ConstraintValidatorContext constraintContext, List<String> errors) {
		constraintContext.disableDefaultConstraintViolation();
		for (String error : errors) {
			constraintContext.buildConstraintViolationWithTemplate(error).addConstraintViolation();
		}
	}

}
