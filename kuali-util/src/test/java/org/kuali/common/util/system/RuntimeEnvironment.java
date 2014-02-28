package org.kuali.common.util.system;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@IdiotProofImmutable
@JsonDeserialize(builder = RuntimeEnvironment.Builder.class)
@JsonPropertyOrder(alphabetic = true)
public final class RuntimeEnvironment {

	private final String vendor;
	private final String version;
	private final String vendorUrl;
	private final Specification specification;

	private RuntimeEnvironment(Builder builder) {
		this.specification = builder.specification;
		this.vendor = builder.vendorUrl;
		this.version = builder.version;
		this.vendorUrl = builder.url;
	}

	public static class Builder extends ValidatingBuilder<RuntimeEnvironment> {

		private String vendorUrl;
		private String version;
		private String url;
		private Specification specification;

		public Builder withVendorUrl(String vendorUrl) {
			this.vendorUrl = vendorUrl;
			return this;
		}

		public Builder withVersion(String version) {
			this.version = version;
			return this;
		}

		public Builder withUrl(String url) {
			this.url = url;
			return this;
		}

		public Builder withSpecification(Specification specification) {
			this.specification = specification;
			return this;
		}

		@Override
		public RuntimeEnvironment build() {
			return validate(new RuntimeEnvironment(this));
		}
	}

	public String getVendor() {
		return vendor;
	}

	public String getVersion() {
		return version;
	}

	public String getVendorUrl() {
		return vendorUrl;
	}

	public Specification getSpecification() {
		return specification;
	}

}
