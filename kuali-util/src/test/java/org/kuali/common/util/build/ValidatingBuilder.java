package org.kuali.common.util.build;

import static org.kuali.common.util.base.Precondition.checkNotNull;
import static org.kuali.common.util.validate.Validation.checkViolations;

import javax.validation.Validator;

import org.kuali.common.util.validate.Validation;

public abstract class ValidatingBuilder<T> implements InstanceBuilder<T> {

	private Validator validator = Validation.getDefaultValidator();

	@Override
	public final T build() {
		checkNotNull(validator, "validator");
		T instance = getInstance();
		checkViolations(validator.validate(instance));
		return instance;
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

}
