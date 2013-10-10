package org.kuali.common.deploy.appserver.tomcat;

import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public class SetEnvContent {

	private final Optional<String> kuali;
	private final Optional<String> group;
	private final Optional<String> app;
	private final Optional<String> version;
	private final Optional<String> env;

	public static class Builder {

		private Optional<String> kuali = Optional.absent();
		private Optional<String> group = Optional.absent();
		private Optional<String> app = Optional.absent();
		private Optional<String> version = Optional.absent();
		private Optional<String> env = Optional.absent();

		public Builder() {
		}

		public Builder kuali(String kuali) {
			this.kuali = Optional.fromNullable(kuali);
			return this;
		}

		public Builder group(String group) {
			this.group = Optional.fromNullable(group);
			return this;
		}

		public Builder app(String app) {
			this.app = Optional.fromNullable(app);
			return this;
		}

		public Builder version(String version) {
			this.version = Optional.fromNullable(version);
			return this;
		}

		public Builder env(String env) {
			this.env = Optional.fromNullable(env);
			return this;
		}

		public SetEnvContent build() {
			Assert.noNulls(kuali, group, app, version, env);
			return new SetEnvContent(this);
		}

	}

	private SetEnvContent(Builder builder) {
		this.kuali = builder.kuali;
		this.group = builder.group;
		this.app = builder.app;
		this.version = builder.version;
		this.env = builder.env;
	}

	public Optional<String> getKuali() {
		return kuali;
	}

	public Optional<String> getGroup() {
		return group;
	}

	public Optional<String> getApp() {
		return app;
	}

	public Optional<String> getVersion() {
		return version;
	}

	public Optional<String> getEnv() {
		return env;
	}
}
