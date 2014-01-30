package org.kuali.common.devops.util;

import java.io.Serializable;

public class Tomcat implements Serializable {

	private static final long serialVersionUID = 883317421109808807L;

	String version;
	String startup;
	String uptime;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getStartup() {
		return startup;
	}

	public void setStartup(String startup) {
		this.startup = startup;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

}
