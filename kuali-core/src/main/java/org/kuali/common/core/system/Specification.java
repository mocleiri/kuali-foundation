package org.kuali.common.core.system;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@IdiotProofImmutable
@JsonDeserialize(builder = Specification.Builder.class)
public final class Specification {

	private final String vendor;
	private final String version;
	private final String name;

	private Specification(Builder builder) {
		this.vendor = builder.vendor;
		this.version = builder.version;
		this.name = builder.name;
	}

	public static class Builder extends ValidatingBuilder<Specification> {

		private String vendor;
		private String version;
		private String name;

		public Builder withVendor(String vendor) {
			this.vendor = vendor;
			return this;
		}

		public Builder withVersion(String version) {
			this.version = version;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		@Override
		public Specification build() {
			return validate(new Specification(this));
		}
	}

	public String getVendor() {
		return vendor;
	}

	public String getVersion() {
		return version;
	}

	public String getName() {
		return name;
	}

}
