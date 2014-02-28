package org.kuali.common.util.system;

import static org.kuali.common.util.validate.Validation.checkConstraints;

import org.kuali.common.util.validate.IdiotProofImmutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@IdiotProofImmutable
@JsonDeserialize(builder = RuntimeEnvironment.Builder.class)
public final class RuntimeEnvironment {

	private final String vendor;
	private final String version;
	private final String url;
	private final Specification specification;

	private RuntimeEnvironment(Builder builder) {
		this.specification = builder.specification;
		this.vendor = builder.vendor;
		this.version = builder.version;
		this.url = builder.url;
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<RuntimeEnvironment> {

		private String vendor;
		private String version;
		private String url;
		private Specification specification;

		public Builder specification(Specification specification) {
			this.specification = specification;
			return this;
		}

		public Builder vendor(String vendor) {
			this.vendor = vendor;
			return this;
		}

		public Builder version(String version) {
			this.version = version;
			return this;
		}

		public Builder url(String url) {
			this.url = url;
			return this;
		}

		@Override
		public RuntimeEnvironment build() {
			return checkConstraints(new RuntimeEnvironment(this));
		}

		public Specification getSpecification() {
			return specification;
		}

		public void setSpecification(Specification specification) {
			this.specification = specification;
		}

		public String getVendor() {
			return vendor;
		}

		public void setVendor(String vendor) {
			this.vendor = vendor;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

	}

	public Specification getSpecification() {
		return specification;
	}

	public String getVendor() {
		return vendor;
	}

	public String getVersion() {
		return version;
	}

	public String getUrl() {
		return url;
	}

}
