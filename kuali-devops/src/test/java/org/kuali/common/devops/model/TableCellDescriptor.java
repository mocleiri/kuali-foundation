package org.kuali.common.devops.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;

import com.google.common.base.Optional;

public final class TableCellDescriptor<T> {

	private final Field field;
	private final Optional<T> fieldValue;

	private TableCellDescriptor(Builder<T> builder) {
		this.field = builder.field;
		this.fieldValue = builder.fieldValue;
	}

	public static <T> TableCellDescriptor<T> create(Field field, Optional<T> fieldValue) {
		Builder<T> builder = builder();
		builder.setField(field);
		builder.setFieldValue(fieldValue);
		return builder.build();
	}

	public static <T> Builder<T> builder() {
		return new Builder<T>();
	}

	public static class Builder<T> implements org.kuali.common.util.build.Builder<TableCellDescriptor<T>> {

		private Field field;
		private Optional<T> fieldValue;

		public Builder<T> field(Field field) {
			this.field = field;
			return this;
		}

		public Builder<T> fieldValue(Optional<T> fieldValue) {
			this.fieldValue = fieldValue;
			return this;
		}

		public Builder<T> fieldValue(Object object) {
			return fieldValue(Optional.of(object));
		}

		@Override
		public TableCellDescriptor<T> build() {
			TableCellDescriptor<T> instance = new TableCellDescriptor<T>(this);
			validate(instance);
			return instance;
		}

		private static <T> void validate(TableCellDescriptor<T> instance) {
			checkNotNull(instance.field, "'field' cannot be null");
			checkNotNull(instance.fieldValue, "'fieldValue' cannot be null");
		}

		public Field getField() {
			return field;
		}

		public void setField(Field field) {
			this.field = field;
		}

		public Optional<T> getFieldValue() {
			return fieldValue;
		}

		public void setFieldValue(Optional<T> fieldValue) {
			this.fieldValue = fieldValue;
		}
	}

	public Field getField() {
		return field;
	}

	public Optional<T> getFieldValue() {
		return fieldValue;
	}

}
