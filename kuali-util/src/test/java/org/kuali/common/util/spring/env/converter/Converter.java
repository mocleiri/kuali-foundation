package org.kuali.common.util.spring.env.converter;

public interface Converter<T, S> {

	T convert(S source);
}
