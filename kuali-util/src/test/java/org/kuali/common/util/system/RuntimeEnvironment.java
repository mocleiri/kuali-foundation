package org.kuali.common.util.system;

import org.kuali.common.util.build.SimpleValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@IdiotProofImmutable
@JsonDeserialize(builder = RuntimeEnvironment.Builder.class)
@JsonPropertyOrder(alphabetic = true)
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

	public static class Builder extends SimpleValidatingBuilder<RuntimeEnvironment> {

		private String vendor;
		private String version;
		private String url;
		private Specification specification;

		public Builder withVendor(String vendor) {
			this.vendor = vendor;
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

	public String getUrl() {
		return url;
	}

	public Specification getSpecification() {
		return specification;
	}

}
