package org.kuali.common.devops.json.system;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@IdiotProofImmutable
@JsonDeserialize(builder = OperatingSystem.Builder.class)
@JsonPropertyOrder(alphabetic = true)
public final class OperatingSystem {

	private final String name;
	@JsonProperty("arch")
	private final String architecture;
	private final String version;

	private OperatingSystem(Builder builder) {
		this.name = builder.name;
		this.architecture = builder.architecture;
		this.version = builder.version;
	}

	public static Builder builder() {
		return new Builder();
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class Builder extends ValidatingBuilder<OperatingSystem> {

		private String name;
		private String architecture;
		private String version;

		@Override
		public OperatingSystem build() {
			return validate(new OperatingSystem(this));
		}

		@Override
		public Set<ConstraintViolation<OperatingSystem>> violations() {
			return violations(new OperatingSystem(this));
		}

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
