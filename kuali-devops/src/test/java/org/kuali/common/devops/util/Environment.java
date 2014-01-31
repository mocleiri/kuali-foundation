package org.kuali.common.devops.util;

import java.util.Comparator;

import com.google.common.base.Optional;

public class Environment implements Comparable<Environment> {

	private static final Comparator<String> COMPARATOR = new EnvStringComparator();

	String project;
	String id;
	String fqdn;
	String type;
	Tomcat tomcat;
	String java;
	Optional<Application> application;

	@Override
	public int compareTo(Environment other) {
		int compare = project.compareTo(other.getProject());
		if (compare != 0) {
			return compare;
		} else {
			return COMPARATOR.compare(id, other.getId());
		}
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

	public Tomcat getTomcat() {
		return tomcat;
	}

	public void setTomcat(Tomcat tomcat) {
		this.tomcat = tomcat;
	}

	public Optional<Application> getApplication() {
		return application;
	}

	public void setApplication(Optional<Application> application) {
		this.application = application;
	}

}
