package org.springframework.jdbc.datasource;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

@IdiotProofImmutable
public final class Database {

	private final String url;
	private final String username;

	private Database(Builder builder) {
		this.url = builder.url;
		this.username = builder.username;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Database> {

		private String url;
		private String username;

		public Builder withUrl(String url) {
			this.url = url;
			return this;
		}

		public Builder withUsername(String username) {
			this.username = username;
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

}
