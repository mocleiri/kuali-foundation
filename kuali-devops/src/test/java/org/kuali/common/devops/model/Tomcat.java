package org.kuali.common.devops.model;

import javax.validation.constraints.Min;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class Tomcat {

	private final String version;
	@Min(-1)
	private final long startupTime;

	private Tomcat(Builder builder) {
		this.version = builder.version;
		this.startupTime = builder.startupTime;
	}

	public static Tomcat create(String version, long startupTime) {
		return builder().version(version).startupTime(startupTime).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Tomcat> {

		private String version;
		private long startupTime;

		public Builder version(String version) {
			this.version = version;
			return this;
		}

		public Builder startupTime(long startupTime) {
			this.startupTime = startupTime;
			return this;
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

		public long getStartupTime() {
			return startupTime;
		}

		public void setStartupTime(long startup) {
			this.startupTime = startup;
		}
	}

	public String getVersion() {
		return version;
	}

	public long getStartupTime() {
		return startupTime;
	}

}
