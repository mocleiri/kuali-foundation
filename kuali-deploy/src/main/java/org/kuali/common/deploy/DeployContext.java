package org.kuali.common.deploy;

import org.kuali.common.util.Artifact;

public class DeployContext {

	String username;
	String hostname;
	Artifact war;
	Artifact jdbcDriver;

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

	public Artifact getWar() {
		return war;
	}

	public void setWar(Artifact war) {
		this.war = war;
	}

	public Artifact getJdbcDriver() {
		return jdbcDriver;
	}

	public void setJdbcDriver(Artifact jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

}
