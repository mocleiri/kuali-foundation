package org.kuali.common.util.env;

import java.lang.reflect.Field;

public interface Setter<T> {

	void set(T instance, Field field, Object value);

}
