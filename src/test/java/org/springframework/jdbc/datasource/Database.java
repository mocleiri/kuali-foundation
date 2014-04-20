package org.springframework.jdbc.datasource;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

@IdiotProofImmutable
public final class Database {

	private final String url;
	private final String username;
	private final Versioned product;
	private final Versioned driver;

	private Database(Builder builder) {
		this.url = builder.url;
		this.username = builder.username;
		this.product = builder.product;
		this.driver = builder.driver;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Database> {

		private String url;
		private String username;
		private Versioned product;
		private Versioned driver;

		public Builder withUrl(String url) {
			this.url = url;
			return this;
		}

		public Builder withUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder withProduct(Versioned product) {
			this.product = product;
			return this;
		}

		public Builder withDriver(Versioned driver) {
			this.driver = driver;
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

	public Versioned getProduct() {
		return product;
	}

	public Versioned getDriver() {
		return driver;
	}

}
