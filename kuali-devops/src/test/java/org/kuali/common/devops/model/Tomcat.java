package org.kuali.common.devops.model;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isBlank;

public final class Tomcat {

	private final String version;
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

	public static class Builder implements org.kuali.common.util.build.Builder<Tomcat> {

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
		public Tomcat build() {
			Tomcat instance = new Tomcat(this);
			validate(instance);
			return instance;
		}

		private static void validate(Tomcat instance) {
			checkArgument(!isBlank(instance.version), "'version' cannot be blank");
			checkArgument(instance.startupTime >= -1, "'startup' must be greater than or equal to -1");
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
