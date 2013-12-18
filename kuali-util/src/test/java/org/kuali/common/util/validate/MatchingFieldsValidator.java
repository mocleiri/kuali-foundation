package org.kuali.common.util.validate;

import java.lang.reflect.Field;
import java.util.Map;

import javax.validation.ConstraintValidator;

import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class MatchingFieldsValidator extends AbstractFieldsValidator implements ConstraintValidator<MatchingFields, Object> {

	private Class<?> otherClass;
	private Map<String, Field> otherClassesFields;

	@Override
	public void initialize(MatchingFields constraintAnnotation) {
		this.skip = constraintAnnotation.skip();
		this.includeInheritedFields = constraintAnnotation.includeInheritedFields();
		this.otherClass = constraintAnnotation.value();
		this.otherClassesFields = ReflectionUtils.getDeclaredFieldsAsMap(otherClass, includeInheritedFields);
	}

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		Field matchingField = otherClassesFields.get(field.getName());
		if (matchingField == null) {
			String errorMessage = ValidationUtils.getErrorMessage(field, "has no matching field in [" + otherClass.getCanonicalName() + "]");
			return Optional.of(errorMessage);
		} else if (matchingField.getType() != field.getType()) {
			String other = otherClass.getCanonicalName();
			String me = field.getType().getCanonicalName();
			String it = matchingField.getType().getCanonicalName();
			String errorMessage = ValidationUtils.getErrorMessage(field, "has a matching field in [" + other + "] but it's a different type. [" + me + "] -> [" + it + "]");
			return Optional.of(errorMessage);
		} else {
			return Optional.absent();
		}
	}
}
