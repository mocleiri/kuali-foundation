package org.kuali.common.util.spring.env.adapter;

public class NoOpAdapter<T> implements EnvAdapter<T, T> {

	@Override
	public T convert(T element) {
		return element;
	}

}
