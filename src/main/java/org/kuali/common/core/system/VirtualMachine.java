package org.kuali.common.core.system;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@IdiotProofImmutable
@JsonDeserialize(builder = VirtualMachine.Builder.class)
public final class VirtualMachine {

	private final String name;
	private final String vendor;
	private final String version;
	private final Specification specification;

	private VirtualMachine(Builder builder) {
		this.name = builder.name;
		this.vendor = builder.vendor;
		this.version = builder.version;
		this.specification = builder.specification;
	}

	public static class Builder extends ValidatingBuilder<VirtualMachine> {

		private String name;
		private String vendor;
		private String version;
		private Specification specification;

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withVendor(String vendor) {
			this.vendor = vendor;
			return this;
		}

		public Builder withVersion(String version) {
			this.version = version;
			return this;
		}

		public Builder withSpecification(Specification specification) {
			this.specification = specification;
			return this;
		}

		@Override
		public VirtualMachine build() {
			return validate(new VirtualMachine(this));
		}
	}

	public String getName() {
		return name;
	}

	public String getVendor() {
		return vendor;
	}

	public String getVersion() {
		return version;
	}

	public Specification getSpecification() {
		return specification;
	}

}
