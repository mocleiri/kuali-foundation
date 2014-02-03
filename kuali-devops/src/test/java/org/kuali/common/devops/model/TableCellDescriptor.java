package org.kuali.common.devops.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;

import com.google.common.base.Optional;

public final class TableCellDescriptor {

	private final Field field;
	private final Optional<?> value;

	private TableCellDescriptor(Builder builder) {
		this.field = builder.field;
		this.value = builder.value;
	}

	public static TableCellDescriptor create(Field field, Optional<?> value) {
		return builder().field(field).value(value).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder implements org.kuali.common.util.build.Builder<TableCellDescriptor> {

		private Field field;
		private Optional<?> value;

		public Builder field(Field field) {
			this.field = field;
			return this;
		}

		public Builder value(Optional<?> value) {
			this.value = value;
			return this;
		}

		public Builder value(Object object) {
			return value(Optional.of(object));
		}

		@Override
		public TableCellDescriptor build() {
			TableCellDescriptor instance = new TableCellDescriptor(this);
			validate(instance);
			return instance;
		}

		private static void validate(TableCellDescriptor instance) {
			checkNotNull(instance.field, "'field' cannot be null");
			checkNotNull(instance.value, "'value' cannot be null");
		}
	}

	public Field getField() {
		return field;
	}

	public Optional<?> getValue() {
		return value;
	}

}
