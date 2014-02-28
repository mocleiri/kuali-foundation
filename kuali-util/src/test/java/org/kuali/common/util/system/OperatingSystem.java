package org.kuali.common.util.system;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@IdiotProofImmutable
@JsonDeserialize(builder = OperatingSystem.Builder.class)
@JsonPropertyOrder(alphabetic = true)
public final class OperatingSystem {

	private final String name;
	private final String arch;
	private final String version;

	private OperatingSystem(Builder builder) {
		this.name = builder.name;
		this.arch = builder.arch;
		this.version = builder.version;
	}

	public static class Builder extends ValidatingBuilder<OperatingSystem> {

		private String name;
		private String arch;
		private String version;

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withArch(String arch) {
			this.arch = arch;
			return this;
		}

		public Builder withVersion(String version) {
			this.version = version;
			return this;
		}

		@Override
		public OperatingSystem build() {
			return validate(new OperatingSystem(this));
		}
	}

	public String getName() {
		return name;
	}

	public String getArch() {
		return arch;
	}

	public String getVersion() {
		return version;
	}
}
