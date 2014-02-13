package org.kuali.common.devops.model;

import static org.kuali.common.util.validate.Validation.checkConstraints;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class Database {

	private final String vendor;
	private final String url;
	private final String username;

	private Database(Builder builder) {
		this.vendor = builder.vendor;
		this.url = builder.url;
		this.username = builder.username;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Database> {

		private String vendor;
		private String url;
		private String username;

		public Builder vendor(String vendor) {
			this.vendor = vendor;
			return this;
		}

		public Builder url(String url) {
			this.url = url;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		@Override
		public Database build() {
			return checkConstraints(validator, new Database(this));
		}

		public String getVendor() {
			return vendor;
		}

		public void setVendor(String vendor) {
			this.vendor = vendor;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

	}

	public String getVendor() {
		return vendor;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}
}
