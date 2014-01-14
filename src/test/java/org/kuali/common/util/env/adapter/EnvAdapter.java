package org.kuali.common.util.env.adapter;

public interface EnvAdapter<S, T> {

	Class<S> getSourceType();

	T convert(S source);
}
