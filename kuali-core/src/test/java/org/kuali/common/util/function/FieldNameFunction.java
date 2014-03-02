package org.kuali.common.util.function;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.lang.reflect.Field;

import com.google.common.base.Function;

public class FieldNameFunction implements Function<Field, String> {

	public static FieldNameFunction make() {
		return new FieldNameFunction();
	}

	@Override
	public String apply(Field field) {
		checkNotNull(field, "field");
		return field.getName();
	}

}
