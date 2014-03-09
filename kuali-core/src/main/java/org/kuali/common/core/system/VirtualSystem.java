package org.kuali.common.core.system;

import java.util.Properties;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.core.validate.annotation.IgnoreBlanks;
import org.kuali.common.util.property.ImmutableProperties;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Strongly typed and immutable object containing the same information available via {@code java.lang.System}
 */
@IdiotProofImmutable
@JsonDeserialize(builder = VirtualSystem.Builder.class)
public final class VirtualSystem {

	private final User user;
	private final OperatingSystem os;
	private final Java java;
	@IgnoreBlanks
	private final String lineSeparator;
	private final String pathSeparator;
	private final String fileSeparator;
	private final ImmutableProperties properties;
	private final ImmutableProperties environment;

	/**
	 * Create a new immutable VirtualSystem instance representing the current state of the system we are running on.
	 */
	public static VirtualSystem create() {
		return VirtualSystemHelper.createVirtualSystem();
	}

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

	public static class Builder extends ValidatingBuilder<VirtualSystem> {

		private User user;
		private OperatingSystem os;
		private Java java;
		private String lineSeparator;
		private String pathSeparator;
		private String fileSeparator;
		private Properties properties = new Properties();
		private Properties environment = new Properties();

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
