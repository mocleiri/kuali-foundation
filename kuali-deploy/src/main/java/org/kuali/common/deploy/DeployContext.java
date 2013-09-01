package org.kuali.common.deploy;

import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.maven.model.Artifact;

import com.google.common.collect.ImmutableList;

public final class DeployContext {

	public DeployContext(String environment, String username, String hostname, Artifact application, Artifact jdbcDriver, List<Deployable> configFiles) {
		Assert.noBlanks(environment, username, hostname);
		Assert.noNulls(application, jdbcDriver, configFiles);
		this.environment = environment;
		this.username = username;
		this.hostname = hostname;
		this.application = application;
		this.jdbcDriver = jdbcDriver;
		this.configFiles = ImmutableList.copyOf(configFiles);
	}

	private final String environment;
	private final String username;
	private final String hostname;
	private final Artifact application;
	private final Artifact jdbcDriver;
	private final List<Deployable> configFiles;

	public String getEnvironment() {
		return environment;
	}

	public String getUsername() {
		return username;
	}

	public String getHostname() {
		return hostname;
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

}
