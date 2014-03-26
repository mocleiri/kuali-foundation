package org.kuali.common.core.validate;

import static com.google.common.base.Optional.absent;

import java.lang.reflect.Field;

import org.kuali.common.core.validate.annotation.NoNullFields;
import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class FinalFieldsValidator extends AbstractFieldsValidator<NoNullFields, Object> {

	@Override
	protected Optional<String> validate(Field field, Object instance) {
		if (ReflectionUtils.isFinal(field)) {
			return absent();
		} else {
			return Validation.errorMessage(field, "is not final");
		}
	}
}
