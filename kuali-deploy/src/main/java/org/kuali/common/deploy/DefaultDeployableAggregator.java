package org.kuali.common.deploy;

import java.util.ArrayList;
import java.util.List;

public class DefaultDeployableAggregator implements DeployableAggregator {

	List<Deployable> deployables;

	List<String> jsps;
	String jspDir;
	String setenv;
	String jdbcDriver;
	String jdbcDriverDir;
	String war;

	@Override
	public List<Deployable> aggregate() {
		List<Deployable> aggregate = new ArrayList<Deployable>();

		return aggregate;
	}

	public List<Deployable> getDeployables() {
		return deployables;
	}

	public void setDeployables(List<Deployable> deployables) {
		this.deployables = deployables;
	}

	public List<String> getJsps() {
		return jsps;
	}

	public void setJsps(List<String> jsps) {
		this.jsps = jsps;
	}

	public String getJspDir() {
		return jspDir;
	}

	public void setJspDir(String jspDir) {
		this.jspDir = jspDir;
	}

	public String getSetenv() {
		return setenv;
	}

	public void setSetenv(String setenv) {
		this.setenv = setenv;
	}

	public String getJdbcDriver() {
		return jdbcDriver;
	}

	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	public String getJdbcDriverDir() {
		return jdbcDriverDir;
	}

	public void setJdbcDriverDir(String jdbcDriverDir) {
		this.jdbcDriverDir = jdbcDriverDir;
	}

	public String getWar() {
		return war;
	}

	public void setWar(String war) {
		this.war = war;
	}

}
