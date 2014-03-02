package org.kuali.common.core.build;

import java.util.Set;

import javax.validation.ConstraintViolation;

public abstract class ViolationsBuilder<T> extends ValidatingBuilder<T> {

	private static final Class<?>[] EMPTY_CLASS_ARRAY = {};

	public abstract Set<ConstraintViolation<T>> violations();

	protected Set<ConstraintViolation<T>> violations(T instance) {
		return validator.validate(instance, validationGroups.toArray(EMPTY_CLASS_ARRAY));
	}

}
