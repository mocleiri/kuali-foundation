package org.kuali.common.devops.model;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isBlank;

public final class AwsRecord {

	private final String project;
	private final String env;
	private final String dns;
	private final String type;
	private final long launchTime;

	private AwsRecord(Builder builder) {
		this.project = builder.project;
		this.env = builder.env;
		this.dns = builder.dns;
		this.type = builder.type;
		this.launchTime = builder.launchTime;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder implements org.kuali.common.util.build.Builder<AwsRecord> {

		private String project;
		private String env;
		private String dns;
		private String type;
		private long launchTime;

		public Builder launchTime(long launchTime) {
			this.launchTime = launchTime;
			return this;
		}

		public Builder project(String project) {
			this.project = project;
			return this;
		}

		public Builder env(String env) {
			this.env = env;
			return this;
		}

		public Builder dns(String dns) {
			this.dns = dns;
			return this;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		@Override
		public AwsRecord build() {
			AwsRecord instance = new AwsRecord(this);
			validate(instance);
			return instance;
		}

		private static void validate(AwsRecord instance) {
			checkArgument(!isBlank(instance.project), "'project' cannot be blank");
			checkArgument(!isBlank(instance.env), "'env' cannot be blank");
			checkArgument(!isBlank(instance.dns), "'dns' cannot be blank");
			checkArgument(!isBlank(instance.type), "'type' cannot be blank");
			checkArgument(instance.launchTime > 0, "'launchTimeMillis' must be greater than zero");
		}

		public String getProject() {
			return project;
		}

		public void setProject(String project) {
			this.project = project;
		}

		public String getEnv() {
			return env;
		}

		public void setEnv(String env) {
			this.env = env;
		}

		public String getDns() {
			return dns;
		}

		public void setDns(String dns) {
			this.dns = dns;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public long getLaunchTime() {
			return launchTime;
		}

		public void setLaunchTime(long launchTime) {
			this.launchTime = launchTime;
		}
	}

	public String getProject() {
		return project;
	}

	public String getEnv() {
		return env;
	}

	public String getDns() {
		return dns;
	}

	public String getType() {
		return type;
	}

	public long getLaunchTime() {
		return launchTime;
	}

}
