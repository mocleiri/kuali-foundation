package org.kuali.common.deploy;

import java.util.List;

import org.kuali.common.deploy.env.model.DeployEnvironment;
import org.kuali.common.util.Assert;
import org.kuali.common.util.maven.model.Artifact;
import org.kuali.common.util.secure.channel.SecureChannel;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class DeployContext {

	private final SecureChannel channel;
	private final DeployEnvironment environment;
	private final Artifact application;
	private final Optional<Artifact> jdbcDriver;
	private final List<Deployable> configFiles;

	public DeployEnvironment getEnvironment() {
		return environment;
	}

	public Artifact getApplication() {
		return application;
	}

	public Optional<Artifact> getJdbcDriver() {
		return jdbcDriver;
	}

	public List<Deployable> getConfigFiles() {
		return configFiles;
	}

	public SecureChannel getChannel() {
		return channel;
	}

	public static class Builder {

		// Required
		private final SecureChannel channel;
		private final DeployEnvironment environment;
		private final Artifact application;

		// Optional
		private Optional<Artifact> jdbcDriver = Optional.absent();
		private List<Deployable> configFiles = ImmutableList.of();

		public Builder(DeployEnvironment environment, SecureChannel channel, Artifact application) {
			this.channel = channel;
			this.environment = environment;
			this.application = application;
		}

		public Builder jdbcDriver(Optional<Artifact> jdbcDriver) {
			this.jdbcDriver = jdbcDriver;
			return this;
		}

		public Builder jdbcDriver(Artifact jdbcDriver) {
			return jdbcDriver(Optional.fromNullable(jdbcDriver));
		}

		public Builder configFiles(List<Deployable> configFiles) {
			this.configFiles = configFiles;
			return this;
		}

		public DeployContext build() {
			Assert.noNulls(environment, channel, application, jdbcDriver, configFiles);
			this.configFiles = ImmutableList.copyOf(configFiles);
			return new DeployContext(this);
		}

	}

	private DeployContext(Builder builder) {
		this.environment = builder.environment;
		this.channel = builder.channel;
		this.application = builder.application;
		this.jdbcDriver = builder.jdbcDriver;
		this.configFiles = builder.configFiles;
	}

}
