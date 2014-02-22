package org.kuali.common.util.build;

import static org.kuali.common.util.validate.Validation.checkConstraints;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang3.builder.Builder;
import org.kuali.common.util.validate.Validation;

import com.google.common.collect.ImmutableList;

public abstract class ValidatingBuilder<T> implements Builder<T> {

	private static final Class<?>[] EMPTY_CLASS_ARRAY = {};

	protected Validator validator = Validation.getDefaultValidator();
	protected List<Class<?>> validationGroups = ImmutableList.of();

	public abstract Set<ConstraintViolation<T>> violations();

	protected Set<ConstraintViolation<T>> getViolations(T instance) {
		return validator.validate(instance, validationGroups.toArray(EMPTY_CLASS_ARRAY));
	}

	protected T validate(T instance) {
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
