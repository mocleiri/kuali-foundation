package org.kuali.common.devops.json.fruit;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.lang.reflect.Field;

import org.springframework.core.convert.TypeDescriptor;

public class FieldDescriptor extends TypeDescriptor {

	private static final long serialVersionUID = 1L;

	public FieldDescriptor(Field field) {
		super(field);
		this.field = checkNotNull(field, "field");
	}

	private final Field field;

	public Field getField() {
		return field;
	}

}
