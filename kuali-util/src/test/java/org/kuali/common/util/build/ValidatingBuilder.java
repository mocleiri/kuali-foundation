package org.kuali.common.util.build;

import static org.kuali.common.util.base.Precondition.checkNotNull;
import static org.kuali.common.util.validate.Validation.checkValidation;

import javax.validation.Validator;

import org.kuali.common.util.validate.Validation;

public abstract class ValidatingBuilder<T> implements InstanceBuilder<T> {

	private Validator validator = Validation.getDefaultValidator();

	@Override
	public final T build() {
		checkNotNull(validator, "validator");
		return checkValidation(getInstance());
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

}
