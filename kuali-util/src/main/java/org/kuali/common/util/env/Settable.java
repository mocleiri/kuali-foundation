package org.kuali.common.util.env;

import java.lang.reflect.Field;

public interface Settable {

	void set(Field field, Object value);

}
