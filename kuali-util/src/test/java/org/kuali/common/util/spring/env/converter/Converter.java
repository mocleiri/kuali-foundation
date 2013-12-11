package org.kuali.common.util.spring.env.converter;

public interface Converter<S, T> {

	T convert(S source);
}
