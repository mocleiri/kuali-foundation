package org.kuali.common.devops.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;

import com.google.common.base.Optional;

public final class TableCellDescriptor {

	private final Field field;
	private final Optional<?> fieldValue;

	private TableCellDescriptor(Builder builder) {
		this.field = builder.field;
		this.fieldValue = builder.fieldValue;
	}

	public static TableCellDescriptor create(Field field, Optional<?> fieldValue) {
		return builder().field(field).fieldValue(fieldValue).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder implements org.kuali.common.util.build.Builder<TableCellDescriptor> {

		private Field field;
		private Optional<?> fieldValue;

		public Builder field(Field field) {
			this.field = field;
			return this;
		}

		public Builder fieldValue(Optional<?> fieldValue) {
			this.fieldValue = fieldValue;
			return this;
		}

		public Builder fieldValue(Object object) {
			return fieldValue(Optional.of(object));
		}

		@Override
		public TableCellDescriptor build() {
			TableCellDescriptor instance = new TableCellDescriptor(this);
			validate(instance);
			return instance;
		}

		private static void validate(TableCellDescriptor instance) {
			checkNotNull(instance.field, "'field' cannot be null");
			checkNotNull(instance.fieldValue, "'value' cannot be null");
		}
	}

	public Field getField() {
		return field;
	}

	public Optional<?> getFieldValue() {
		return fieldValue;
	}

}
