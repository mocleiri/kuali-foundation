package org.kuali.common.deploy;

import java.util.List;

import org.kuali.common.deploy.env.model.DeployEnvironment;
import org.kuali.common.util.Assert;
import org.kuali.common.util.maven.model.Artifact;
import org.kuali.common.util.secure.channel.SecureChannel;

import com.google.common.collect.ImmutableList;

public final class DeployContext {

	public DeployContext(SecureChannel channel, DeployEnvironment environment, Artifact application, Artifact jdbcDriver, List<Deployable> configFiles) {
		Assert.noNulls(channel, environment, application, jdbcDriver, configFiles);
		this.environment = environment;
		this.channel = channel;
		this.application = application;
		this.jdbcDriver = jdbcDriver;
		this.configFiles = ImmutableList.copyOf(configFiles);
	}

	private final SecureChannel channel;
	private final DeployEnvironment environment;
	private final Artifact application;
	private final Artifact jdbcDriver;
	private final List<Deployable> configFiles;

	public DeployEnvironment getEnvironment() {
		return environment;
	}

	public Artifact getApplication() {
		return application;
	}

	public Artifact getJdbcDriver() {
		return jdbcDriver;
	}

	public List<Deployable> getConfigFiles() {
		return configFiles;
	}

	public SecureChannel getChannel() {
		return channel;
	}

}
