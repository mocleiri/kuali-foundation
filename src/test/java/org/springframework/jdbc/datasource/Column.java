package org.springframework.jdbc.datasource;

import javax.validation.constraints.Min;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

@IdiotProofImmutable
public final class Column {

	@Min(0)
	private final int index;
	private final String name;
	private final Class<?> type;

	private Column(Builder builder) {
		this.name = builder.name;
		this.type = builder.type;
		this.index = builder.index;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Column> {

		private int index = -1;
		private String name;
		private Class<?> type;

		public Builder withIndex(int index) {
			this.index = index;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withType(Class<?> type) {
			this.type = type;
			return this;
		}

		@Override
		public Column build() {
			return validate(new Column(this));
		}
	}

	public String getName() {
		return name;
	}

	public Class<?> getType() {
		return type;
	}

	public int getIndex() {
		return index;
	}

}
