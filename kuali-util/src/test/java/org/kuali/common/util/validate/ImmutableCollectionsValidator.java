package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;

import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.collect.CollectionUtils;

import com.google.common.base.Optional;

public class ImmutableCollectionsValidator extends AbstractFieldsValidator implements ConstraintValidator<NoNulls, Object> {

	@Override
	public void initialize(NoNulls constraintAnnotation) {
		this.skip = constraintAnnotation.skip();
		this.includeInheritedFields = constraintAnnotation.includeInheritedFields();
	}

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		// This field doesn't implement the Collection interface, nothing to do
		if (!CollectionUtils.isCollection(field.getClass())) {
			return Optional.absent();
		}

		// Extract the value of the field into an Optional
		Optional<?> fieldValue = ReflectionUtils.get(field, instance);

		// There is no value for this field (ie it was null)
		if (!fieldValue.isPresent()) {
			return Optional.absent();
		}

		// Get the actual object reference
		Object value = fieldValue.get();

		if (CollectionUtils.isImmutable(value.getClass())) {
			// If it's immutable, we are good to go
			return Optional.absent();
		} else {
			// If not, return an error message
			return Optional.of(getErrorMessage(field, "is not an immutable map"));
		}
	}
}
