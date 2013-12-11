package org.kuali.common.util.spring.env.annotation;

import java.lang.reflect.Field;

public interface Mutable<T> {

	void set(T instance, Field field, Object value);

}
