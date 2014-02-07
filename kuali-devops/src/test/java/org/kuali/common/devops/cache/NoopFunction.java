package org.kuali.common.devops.cache;

import com.google.common.base.Function;

public class NoopFunction<T> implements Function<T, T> {

	@Override
	public T apply(T reference) {
		return reference;
	}

}
