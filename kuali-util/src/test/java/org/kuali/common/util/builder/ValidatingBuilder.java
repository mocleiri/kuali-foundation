package org.kuali.common.util.builder;

public interface ValidatingBuilder<T> extends Builder<T> {

	void validate(T instance);

}
