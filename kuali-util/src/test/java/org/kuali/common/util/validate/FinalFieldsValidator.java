package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class FinalFieldsValidator extends AbstractFieldsValidator<NoNullFields, Object> {

	@Override
	protected Optional<String> validate(Field field, Object instance) {
		if (ReflectionUtils.isFinal(field)) {
			return Optional.absent();
		} else {
			return ValidationUtils.errorMessage(field, "is not final");
		}
	}
}
