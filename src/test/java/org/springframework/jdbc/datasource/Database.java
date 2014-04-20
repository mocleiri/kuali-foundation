package org.springframework.jdbc.datasource;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

@IdiotProofImmutable
public final class Database {

	private final String url;
	private final String username;
	private final String productName;
	private final String productVersion;
	private final String driverName;
	private final String driverVersion;

	private Database(Builder builder) {
		this.url = builder.url;
		this.username = builder.username;
		this.productName = builder.productName;
		this.productVersion = builder.productVersion;
		this.driverName = builder.driverName;
		this.driverVersion = builder.driverVersion;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Database> {

		private String url;
		private String username;
		private String productName;
		private String productVersion;
		private String driverName;
		private String driverVersion;

		public Builder withUrl(String url) {
			this.url = url;
			return this;
		}

		public Builder withUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder withProductName(String productName) {
			this.productName = productName;
			return this;
		}

		public Builder withProductVersion(String productVersion) {
			this.productVersion = productVersion;
			return this;
		}

		public Builder withDriverName(String driverName) {
			this.driverName = driverName;
			return this;
		}

		public Builder withDriverVersion(String driverVersion) {
			this.driverVersion = driverVersion;
			return this;
		}

		@Override
		public Database build() {
			return validate(new Database(this));
		}
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductVersion() {
		return productVersion;
	}

	public String getDriverName() {
		return driverName;
	}

	public String getDriverVersion() {
		return driverVersion;
	}

}
