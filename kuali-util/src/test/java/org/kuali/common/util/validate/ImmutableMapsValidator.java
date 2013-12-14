package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;

import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.collect.MapUtils;

import com.google.common.base.Optional;

public class ImmutableMapsValidator extends AbstractFieldsValidator implements ConstraintValidator<NoNulls, Object> {

	@Override
	public void initialize(NoNulls constraintAnnotation) {
		this.skip = constraintAnnotation.skip();
		this.includeInheritedFields = constraintAnnotation.includeInheritedFields();
	}

	@Override
	protected Optional<String> validate(Field field, Object instance) {
		if (!MapUtils.isMap(field.getClass())) {
			return Optional.absent();
		}
		Optional<?> fieldValue = ReflectionUtils.get(field, instance);
		if (!fieldValue.isPresent()) {
			return Optional.absent();
		}
		Object value = fieldValue.get();
		if (MapUtils.isImmutable(value.getClass())) {
			return Optional.absent();
		}
		return Optional.of(getErrorMessage(field, "is not immutable"));
	}
}
