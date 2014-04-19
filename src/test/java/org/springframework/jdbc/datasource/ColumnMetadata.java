package org.springframework.jdbc.datasource;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

@IdiotProofImmutable
public final class ColumnMetadata {

	private final String name;
	private final Class<?> type;

	private ColumnMetadata(Builder builder) {
		this.name = builder.name;
		this.type = builder.type;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<ColumnMetadata> {

		private String name;
		private Class<?> type;

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withType(Class<?> type) {
			this.type = type;
			return this;
		}

		@Override
		public ColumnMetadata build() {
			return validate(new ColumnMetadata(this));
		}
	}

	public String getName() {
		return name;
	}

	public Class<?> getType() {
		return type;
	}

}
