package org.kuali.common.util.spring.env.converter;

public interface Converter<S, T> {

	Class<S> getSourceType();

	T convert(S source);
}
