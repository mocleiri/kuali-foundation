package org.kuali.common.util.env;

import java.lang.reflect.Field;

public interface Overridable {

	void override(Field field, Object value);

}
