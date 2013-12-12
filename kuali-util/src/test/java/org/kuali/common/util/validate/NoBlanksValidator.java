package org.kuali.common.util.validate;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.validate.annotation.NoBlanks;

import com.google.common.base.Optional;

public class NoBlanksValidator extends AbstractInstanceValidator implements ConstraintValidator<NoBlanks, Object> {

	@Override
	public void initialize(NoBlanks constraintAnnotation) {
		this.skip = constraintAnnotation.skip();
	}

	@Override
	protected Optional<String> validate(Field field, Object instance) {
		if (field.getType() != String.class && field.getType() != CharSequence.class) {
			return Optional.absent();
		}
		Optional<?> value = get(field, instance);
		CharSequence cs = (CharSequence) value.orNull();
		if (StringUtils.isBlank(cs)) {
			return Optional.of(getErrorMessage(field, instance, "cannot be blank"));
		} else {
			return Optional.absent();
		}
	}

}
