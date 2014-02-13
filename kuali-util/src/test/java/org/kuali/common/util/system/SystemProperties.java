package org.kuali.common.util.system;

import static org.kuali.common.util.validate.Validation.checkValidation;

import org.kuali.common.util.bind.api.Alias;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
@Bind(Bind.ABSENT)
public final class SystemProperties {

	@Bind
	private final User user;

	@Bind("os")
	private final OperatingSystem operatingSystem;

	@Bind
	private final Java java;

	@Alias("line.separator")
	private final String lineSeparator;

	@Alias("path.separator")
	private final String pathSeparator;

	@Alias("file.separator")
	private final String fileSeparator;

	private SystemProperties(Builder builder) {
		this.user = builder.user;
		this.operatingSystem = builder.operatingSystem;
		this.java = builder.java;
		this.lineSeparator = builder.lineSeparator;
		this.pathSeparator = builder.pathSeparator;
		this.fileSeparator = builder.fileSeparator;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<SystemProperties> {

		private User user;
		private OperatingSystem operatingSystem;
		private Java java;
		private String pathSeparator;
		private String lineSeparator;
		private String fileSeparator;

		@Override
		public SystemProperties build() {
			return checkValidation(new SystemProperties(this));
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

}
