package org.kuali.common.util.bind.function;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;

import com.google.common.base.Function;

public class FieldNameFunction implements Function<Field, String> {

	public static FieldNameFunction of() {
		return new FieldNameFunction();
	}

	@Override
	public String apply(Field field) {
		checkNotNull(field, "'field' cannot be null'");
		return field.getName();
	}

}
