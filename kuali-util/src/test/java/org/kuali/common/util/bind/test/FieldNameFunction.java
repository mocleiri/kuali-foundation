package org.kuali.common.util.bind.test;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;

import com.google.common.base.Function;

public class FieldNameFunction implements Function<Field, String> {

	@Override
	public String apply(Field field) {
		checkNotNull(field, "'field' cannot be null'");
		return field.getName();
	}

}
