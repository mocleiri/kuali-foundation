package org.kuali.common.util.env;

import java.lang.reflect.Field;

public interface Overridable<T> {

	void set(T instance, Field field, Object value);

}
