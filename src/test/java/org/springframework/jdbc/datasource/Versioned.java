package org.springframework.jdbc.datasource;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

@IdiotProofImmutable
public final class Versioned {

	private final String name;
	private final String version;

	private Versioned(Builder builder) {
		this.name = builder.name;
		this.version = builder.version;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Versioned> {

		private String name;
		private String version;

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withVersion(String version) {
			this.version = version;
			return this;
		}

		@Override
		public Versioned build() {
			return validate(new Versioned(this));
		}
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

}
