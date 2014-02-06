package org.kuali.common.util.validate;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.kuali.common.util.ReflectionUtils;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class UniqueFieldNamesValidator implements ConstraintValidator<UniqueFieldNames, Object> {

	@Override
	public void initialize(UniqueFieldNames constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object instance, ConstraintValidatorContext constraintContext) {

		// Extract every field in the type hierarchy
		Set<Field> fields = ReflectionUtils.getAllFields(instance.getClass());

		// These field names are duplicated
		Set<String> duplicates = getDuplicateFieldNames(fields);

		if (duplicates.size() == 0) {
			return true;
		} else {
			constraintContext.disableDefaultConstraintViolation();
			String path = ReflectionUtils.getDeclarationPath(instance.getClass());
			for (String duplicate : duplicates) {
				String error = "Duplicate field declaration: In type [" + path + "] the field [" + duplicate + "] appears more than once in the type hierarchy";
				constraintContext.buildConstraintViolationWithTemplate(error).addConstraintViolation();
			}
			return false;
		}
	}

	protected Set<String> getDuplicateFieldNames(Set<Field> fields) {
		// Keep track of field name counts
		Multiset<String> multiset = HashMultiset.create();
		for (Field field : fields) {
			multiset.add(field.getName());
		}

		Set<String> duplicates = new HashSet<String>();
		for (String fieldName : multiset) {
			int count = multiset.count(fieldName);
			if (count > 1) {
				duplicates.add(fieldName);
			}
		}
		return duplicates;
	}
}
