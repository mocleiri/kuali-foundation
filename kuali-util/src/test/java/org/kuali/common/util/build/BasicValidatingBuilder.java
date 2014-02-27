package org.kuali.common.util.build;

import static org.kuali.common.util.validate.Validation.checkConstraints;

import java.util.List;

import javax.validation.Validator;

import org.apache.commons.lang3.builder.Builder;
import org.kuali.common.util.validate.Validation;

import com.google.common.collect.ImmutableList;

public abstract class BasicValidatingBuilder<T> implements Builder<T> {

	protected Validator validator = Validation.getDefaultValidator();
	protected List<Class<?>> validationGroups = ImmutableList.of();

	protected T validate(T instance) {
		return checkConstraints(instance, validator, validationGroups);
	}

	public BasicValidatingBuilder<T> validator(Validator validator) {
		this.validator = validator;
		return this;
	}

	public BasicValidatingBuilder<T> validationGroups(List<Class<?>> validationGroups) {
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
