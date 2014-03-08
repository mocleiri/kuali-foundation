package org.kuali.common.core.build;

import static org.kuali.common.core.validate.Validation.checkConstraints;

import java.util.List;

import javax.validation.Validator;

import org.apache.commons.lang3.builder.Builder;
import org.kuali.common.core.validate.Validation;

import com.google.common.collect.ImmutableList;

/**
 * Concrete builder that hooks into the {@code javax.validation.Validator} validation framework to enable validating objects before they are built
 */
public abstract class ValidatingBuilder<T> implements Builder<T> {

	protected Validator validator = Validation.getDefaultValidator();
	protected List<Class<?>> validationGroups = ImmutableList.of();

	protected T validate(T instance) {
		return checkConstraints(instance, validator, validationGroups);
	}

	public ValidatingBuilder<T> withValidator(Validator validator) {
		this.validator = validator;
		return this;
	}

	public ValidatingBuilder<T> withValidationGroups(List<Class<?>> validationGroups) {
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
