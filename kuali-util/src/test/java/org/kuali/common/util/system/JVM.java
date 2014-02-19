package org.kuali.common.util.system;

import static org.kuali.common.util.bind.api.Bind.ABSENT;

import java.util.Properties;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.constraints.NotBlank;
import org.kuali.common.util.bind.api.Alias;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.validate.IdiotProofImmutableWithBlanks;

@IdiotProofImmutableWithBlanks
@Bind(ABSENT)
public final class JVM {

	@Bind
	private final User user;

	@Bind("os")
	private final OperatingSystem operatingSystem;

	@Bind
	private final Java java;

	@Alias("line.separator")
	private final String lineSeparator;

	@Alias("path.separator")
	@NotBlank
	private final String pathSeparator;

	@Alias("file.separator")
	@NotBlank
	private final String fileSeparator;

	@Alias("system.properties")
	private final ImmutableProperties system;

	@Alias("system.environment")
	private final ImmutableProperties environment;

	private JVM(Builder builder) {
		this.user = builder.user;
		this.operatingSystem = builder.operatingSystem;
		this.java = builder.java;
		this.lineSeparator = builder.lineSeparator;
		this.pathSeparator = builder.pathSeparator;
		this.fileSeparator = builder.fileSeparator;
		this.system = ImmutableProperties.copyOf(builder.system);
		this.environment = ImmutableProperties.copyOf(builder.environment);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<JVM> {

		private User user;
		private OperatingSystem operatingSystem;
		private Java java;
		private String pathSeparator;
		private String lineSeparator;
		private String fileSeparator;
		private Properties system;
		private Properties environment;

		@Override
		public Set<ConstraintViolation<JVM>> getViolations() {
			return getViolations(new JVM(this));
		}

		@Override
		public JVM build() {
			return validate(new JVM(this));
		}

		public Builder system(Properties system) {
			this.system = system;
			return this;
		}

		public Builder environment(Properties environment) {
			this.environment = environment;
			return this;
		}

		public Builder user(User user) {
			this.user = user;
			return this;
		}

		public Builder operatingSystem(OperatingSystem operatingSystem) {
			this.operatingSystem = operatingSystem;
			return this;
		}

		public Builder java(Java java) {
			this.java = java;
			return this;
		}

		public Builder lineSeparator(String lineSeparator) {
			this.lineSeparator = lineSeparator;
			return this;
		}

		public Builder pathSeparator(String pathSeparator) {
			this.pathSeparator = pathSeparator;
			return this;
		}

		public Builder fileSeparator(String fileSeparator) {
			this.fileSeparator = fileSeparator;
			return this;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public OperatingSystem getOperatingSystem() {
			return operatingSystem;
		}

		public void setOperatingSystem(OperatingSystem operatingSystem) {
			this.operatingSystem = operatingSystem;
		}

		public Java getJava() {
			return java;
		}

		public void setJava(Java java) {
			this.java = java;
		}

		public String getLineSeparator() {
			return lineSeparator;
		}

		public void setLineSeparator(String lineSeparator) {
			this.lineSeparator = lineSeparator;
		}

		public String getPathSeparator() {
			return pathSeparator;
		}

		public void setPathSeparator(String pathSeparator) {
			this.pathSeparator = pathSeparator;
		}

		public String getFileSeparator() {
			return fileSeparator;
		}

		public void setFileSeparator(String fileSeparator) {
			this.fileSeparator = fileSeparator;
		}

		public Properties getSystem() {
			return system;
		}

		public void setSystem(Properties system) {
			this.system = system;
		}

		public Properties getEnvironment() {
			return environment;
		}

		public void setEnvironment(Properties environment) {
			this.environment = environment;
		}

	}

	public User getUser() {
		return user;
	}

	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
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

	public Properties getSystem() {
		return system;
	}

	public Properties getEnvironment() {
		return environment;
	}

}
