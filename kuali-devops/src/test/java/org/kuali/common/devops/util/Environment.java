package org.kuali.common.devops.util;

import org.kuali.common.util.project.model.Project;

public class Environment {

	String project;
	String id;
	String dns;
	String type;
	String tomcat;
	String java;
	String startup;
	String uptime;
	Project application;

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTomcat() {
		return tomcat;
	}

	public void setTomcat(String tomcat) {
		this.tomcat = tomcat;
	}

	public String getJava() {
		return java;
	}

	public void setJava(String java) {
		this.java = java;
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

	public Project getApplication() {
		return application;
	}

	public void setApplication(Project application) {
		this.application = application;
	}

}
