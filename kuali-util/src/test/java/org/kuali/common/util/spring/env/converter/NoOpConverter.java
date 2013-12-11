package org.kuali.common.util.spring.env.converter;

public class NoOpConverter<T> implements Converter<T, T> {

	@Override
	public T convert(T element) {
		return element;
	}

}
