package org.kuali.common.util.system;

import static org.kuali.common.util.validate.Validation.checkConstraints;

import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class OperatingSystem {

	private final String name;
	private final String architecture;
	private final String version;

	private OperatingSystem(Builder builder) {
		this.name = builder.name;
		this.architecture = builder.architecture;
		this.version = builder.version;
	}

	public static OperatingSystem create() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<OperatingSystem> {

		private String name;
		private String architecture;
		private String version;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder architecture(String architecture) {
			this.architecture = architecture;
			return this;
		}

		public Builder version(String version) {
			this.version = version;
			return this;
		}

		@Override
		public OperatingSystem build() {
			return checkConstraints(new OperatingSystem(this));
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getArchitecture() {
			return architecture;
		}

		public void setArchitecture(String architecture) {
			this.architecture = architecture;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

	}

	public String getName() {
		return name;
	}

	public String getArchitecture() {
		return architecture;
	}

	public String getVersion() {
		return version;
	}

}
