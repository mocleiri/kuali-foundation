package org.kuali.common.util.env.adapter;

public interface Adapter<S, T> {

	Class<S> getSourceType();

	T convert(S source);
}
