package org.kuali.common.devops.util;

import java.util.Comparator;

import org.kuali.common.util.project.model.Project;

import com.google.common.base.Optional;

public class Environment implements Comparable<Environment> {

	private static final Comparator<String> COMPARATOR = new FqdnComparator();

	String project;
	String id;
	String fqdn;
	String type;
	Tomcat tomcat;
	String java;
	Database database;
	Optional<Project> application;

	@Override
	public int compareTo(Environment other) {
		return COMPARATOR.compare(this.getFqdn(), other.getFqdn());
	}

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

	public String getFqdn() {
		return fqdn;
	}

	public void setFqdn(String dns) {
		this.fqdn = dns;
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

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

}
