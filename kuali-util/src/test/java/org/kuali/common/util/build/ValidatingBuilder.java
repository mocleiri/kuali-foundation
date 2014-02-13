package org.kuali.common.util.build;

import static org.kuali.common.util.base.Precondition.checkNotNull;
import static org.kuali.common.util.validate.Validation.checkValidation;

import javax.validation.Validator;

import org.apache.commons.lang3.builder.Builder;
import org.kuali.common.util.validate.Validation;

public abstract class ValidatingBuilder<T> implements Builder<T> {

	protected Validator validator = Validation.getDefaultValidator();

	protected abstract T newInstance();

	@Override
	public final T build() {
		return checkValidation(validator, newInstance());
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		checkNotNull(validator, "validator");
		this.validator = validator;
	}

}
