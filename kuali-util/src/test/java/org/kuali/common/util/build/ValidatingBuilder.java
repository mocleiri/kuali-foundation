package org.kuali.common.util.build;

import static org.kuali.common.util.base.Precondition.checkNotNull;
import static org.kuali.common.util.validate.Validation.checkValidation;
import static org.kuali.common.util.validate.Validation.getDefaultValidator;

import javax.validation.Validator;

public abstract class ValidatingBuilder<T> implements InstanceBuilder<T> {

	private Validator validator = getDefaultValidator();

	@Override
	public final T build() {
		checkNotNull(validator, "validator");
		return checkValidation(validator, getInstance());
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

}
