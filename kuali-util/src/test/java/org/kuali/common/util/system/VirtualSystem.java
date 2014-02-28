package org.kuali.common.util.system;

import java.util.Properties;

import org.hibernate.validator.constraints.NotBlank;
import org.kuali.common.util.build.SimpleValidatingBuilder;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.validate.IdiotProofImmutableWithBlanks;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@IdiotProofImmutableWithBlanks
@JsonDeserialize(builder = VirtualSystem.Builder.class)
public final class VirtualSystem {

	private final User user;
	private final OperatingSystem os;
	private final Java java;
	private final String lineSeparator;
	@NotBlank
	private final String pathSeparator;
	@NotBlank
	private final String fileSeparator;
	private final ImmutableProperties properties;
	private final ImmutableProperties environment;

	public static Builder builder() {
		return new Builder();
	}

	private VirtualSystem(Builder builder) {
		this.user = builder.user;
		this.os = builder.os;
		this.java = builder.java;
		this.lineSeparator = builder.lineSeparator;
		this.pathSeparator = builder.pathSeparator;
		this.fileSeparator = builder.fileSeparator;
		this.properties = ImmutableProperties.copyOf(builder.properties);
		this.environment = ImmutableProperties.copyOf(builder.environment);
	}

	public static class Builder extends SimpleValidatingBuilder<VirtualSystem> {

		private User user;
		private OperatingSystem os;
		private Java java;
		private String lineSeparator;
		private String pathSeparator;
		private String fileSeparator;
		private Properties properties;
		private Properties environment;

		public Builder withUser(User user) {
			this.user = user;
			return this;
		}

		public Builder withOs(OperatingSystem os) {
			this.os = os;
			return this;
		}

		public Builder withJava(Java java) {
			this.java = java;
			return this;
		}

		public Builder withLineSeparator(String lineSeparator) {
			this.lineSeparator = lineSeparator;
			return this;
		}

		public Builder withPathSeparator(String pathSeparator) {
			this.pathSeparator = pathSeparator;
			return this;
		}

		public Builder withFileSeparator(String fileSeparator) {
			this.fileSeparator = fileSeparator;
			return this;
		}

		public Builder withProperties(Properties properties) {
			this.properties = properties;
			return this;
		}

		public Builder withEnvironment(Properties environment) {
			this.environment = environment;
			return this;
		}

		@Override
		public VirtualSystem build() {
			return validate(new VirtualSystem(this));
		}
	}

	public User getUser() {
		return user;
	}

	public OperatingSystem getOs() {
		return os;
	}

	public Java getJava() {
		return java;
	}

	public String getLineSeparator() {
		return lineSeparator;
	}

	public String getPathSeparator() {
		return pathSeparator;
	}

	public String getFileSeparator() {
		return fileSeparator;
	}

	public Properties getProperties() {
		return properties;
	}

	public Properties getEnvironment() {
		return environment;
	}

}
