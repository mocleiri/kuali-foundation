package org.kuali.common.util.build;

import static org.kuali.common.util.validate.Validation.checkConstraints;

import java.util.List;

import javax.validation.Validator;

import org.apache.commons.lang3.builder.Builder;
import org.kuali.common.util.validate.Validation;

import com.google.common.collect.ImmutableList;

public abstract class ValidatingBuilder<T> implements Builder<T> {

	protected Validator validator = Validation.getDefaultValidator();
	protected List<Class<?>> validationGroups = ImmutableList.of();

	public boolean isValid(T instance) {
		return Validation.isValid(instance, validator, validationGroups);
	}

	public T validate(T instance) {
		return checkConstraints(instance, validator, validationGroups);
	}

	public ValidatingBuilder<T> validator(Validator validator) {
		this.validator = validator;
		return this;
	}

	public ValidatingBuilder<T> validationGroups(List<Class<?>> validationGroups) {
		this.validationGroups = validationGroups;
		return this;
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public List<Class<?>> getValidationGroups() {
		return validationGroups;
	}

	public void setValidationGroups(List<Class<?>> validationGroups) {
		this.validationGroups = validationGroups;
	}

}
