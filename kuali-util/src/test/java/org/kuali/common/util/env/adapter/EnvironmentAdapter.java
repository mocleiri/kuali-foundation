package org.kuali.common.util.env.adapter;

public interface EnvironmentAdapter<S, T> {

	Class<S> getSourceType();

	T convert(S source);
}
