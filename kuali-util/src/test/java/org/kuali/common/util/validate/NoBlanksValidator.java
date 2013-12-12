package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Optional;

public class NoBlanksValidator extends AbstractFieldsValidator implements ConstraintValidator<NoBlanks, Object> {

	@Override
	public void initialize(NoBlanks constraintAnnotation) {
		this.skip = constraintAnnotation.skip();
	}

	@Override
	protected Optional<String> validate(Field field, Object instance) {
		if (field.getType() != String.class) {
			return Optional.absent();
		}
		Optional<?> value = get(field, instance);
		String string = (String) value.orNull();
		if (StringUtils.isBlank(string)) {
			return Optional.of(getErrorMessage(field, "cannot be blank"));
		} else {
			return Optional.absent();
		}
	}

}
