package org.kuali.common.devops.model;

import static com.google.common.base.Preconditions.checkArgument;

import org.apache.commons.lang3.StringUtils;

public final class Tomcat {

	private final String version;
	private final long startup;

	private Tomcat(Builder builder) {
		this.version = builder.version;
		this.startup = builder.startup;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder implements org.kuali.common.util.build.Builder<Tomcat> {

		private String version;
		private long startup;

		public Builder version(String version) {
			this.version = version;
			return this;
		}

		public Builder startup(long startup) {
			this.startup = startup;
			return this;
		}

		@Override
		public Tomcat build() {
			Tomcat instance = new Tomcat(this);
			validate(instance);
			return instance;
		}

		private static void validate(Tomcat instance) {
			checkArgument(!StringUtils.isBlank(instance.version), "'version' cannot be blank");
			checkArgument(instance.startup > 0, "'startup' must be greater than zero");
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public long getStartup() {
			return startup;
		}

		public void setStartup(long startup) {
			this.startup = startup;
		}
	}

	public String getVersion() {
		return version;
	}

	public long getStartup() {
		return startup;
	}

}
