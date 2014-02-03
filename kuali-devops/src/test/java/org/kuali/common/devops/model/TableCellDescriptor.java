package org.kuali.common.devops.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;

public final class TableCellDescriptor {

	private final Field field;
	private final Object object;

	private TableCellDescriptor(Builder builder) {
		this.field = builder.field;
		this.object = builder.object;
	}

	public static class Builder {

		private Field field;
		private Object object;

		public Builder field(Field field) {
			this.field = field;
			return this;
		}

		public Builder object(Object object) {
			this.object = object;
			return this;
		}

		public TableCellDescriptor build() {
			TableCellDescriptor instance = new TableCellDescriptor(this);
			validate(instance);
			return instance;
		}

		private static void validate(TableCellDescriptor instance) {
			checkNotNull(instance.field, "'field' cannot be null");
			checkNotNull(instance.object, "'object' cannot be null");
		}
	}

	public Field getField() {
		return field;
	}

	public Object getObject() {
		return object;
	}

}
