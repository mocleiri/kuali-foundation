package org.kuali.common.deploy;

import org.kuali.common.util.Artifact;

public class DeployContext {

	String environment;
	String username;
	String hostname;
	Artifact application;
	Artifact jdbcDriver;
	Deployable config;

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Artifact getApplication() {
		return application;
	}

	public void setApplication(Artifact application) {
		this.application = application;
	}

	public Artifact getJdbcDriver() {
		return jdbcDriver;
	}

	public void setJdbcDriver(Artifact jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	public Deployable getConfig() {
		return config;
	}

	public void setConfig(Deployable config) {
		this.config = config;
	}

}
