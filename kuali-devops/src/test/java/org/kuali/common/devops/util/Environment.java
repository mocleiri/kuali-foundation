package org.kuali.common.devops.util;

import java.io.Serializable;

import org.kuali.common.util.project.model.Project;

import com.google.common.base.Optional;

public class Environment implements Serializable {

	private static final long serialVersionUID = -8973641077371212241L;

	String project;
	String id;
	String dns;
	String type;
	Tomcat tomcat;
	String java;
	Optional<Project> application;

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

	public String getJava() {
		return java;
	}

	public void setJava(String java) {
		this.java = java;
	}

	public Optional<Project> getApplication() {
		return application;
	}

	public void setApplication(Optional<Project> application) {
		this.application = application;
	}

	public Tomcat getTomcat() {
		return tomcat;
	}

	public void setTomcat(Tomcat tomcat) {
		this.tomcat = tomcat;
	}

}
