package org.kuali.common.util.builder;

import java.util.Set;

import javax.validation.ConstraintViolation;

public interface ValidatingBuilder<T> extends Builder<T> {

	Set<ConstraintViolation<T>> validate(T instance);

}
