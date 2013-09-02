package org.kuali.common.deploy;

import java.util.List;

import org.kuali.common.deploy.env.model.DeployEnvironment;
import org.kuali.common.util.Assert;
import org.kuali.common.util.maven.model.Artifact;

import com.google.common.collect.ImmutableList;

public final class DeployContext {

	public DeployContext(String username, DeployEnvironment environment, Artifact application, Artifact jdbcDriver, List<Deployable> configFiles) {
		Assert.noBlanks(username);
		Assert.noNulls(environment, application, jdbcDriver, configFiles);
		this.environment = environment;
		this.username = username;
		this.application = application;
		this.jdbcDriver = jdbcDriver;
		this.configFiles = ImmutableList.copyOf(configFiles);
	}

	private final DeployEnvironment environment;
	private final String username;
	private final Artifact application;
	private final Artifact jdbcDriver;
	private final List<Deployable> configFiles;

	public DeployEnvironment getEnvironment() {
		return environment;
	}

	public String getUsername() {
		return username;
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
