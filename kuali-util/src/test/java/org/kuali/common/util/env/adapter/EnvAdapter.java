package org.kuali.common.util.env.adapter;

public interface EnvAdapter<E, T> {

	Class<E> getEnvType();

	T convert(E value);
}
