package org.kuali.common.devops.model;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class Tomcat {

	private final String version;
	private final Optional<Long> startupTime;

	private Tomcat(Builder builder) {
		this.version = builder.version;
		this.startupTime = builder.startupTime;
	}

	public static Tomcat create(String version, Optional<Long> startupTime) {
		return builder().version(version).startupTime(startupTime).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Tomcat> {

		private String version;
		private Optional<Long> startupTime = Optional.absent();

		public Builder version(String version) {
			this.version = version;
			return this;
		}

		public Builder startupTime(Optional<Long> startupTime) {
			this.startupTime = startupTime;
			return this;
		}

		public Builder startupTime(long startupTime) {
			return startupTime(Optional.of(startupTime));
		}

		@Override
		public Tomcat getInstance() {
			return new Tomcat(this);
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public Optional<Long> getStartupTime() {
			return startupTime;
		}

		public void setStartupTime(Optional<Long> startup) {
			this.startupTime = startup;
		}
	}

	public String getVersion() {
		return version;
	}

	public Optional<Long> getStartupTime() {
		return startupTime;
	}

}
