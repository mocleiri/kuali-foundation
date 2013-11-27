package org.kuali.common.devops.aws.sysadmin.model;

public enum Services {

	SSHD("sshd"), //
	MYSQLD("mysqld"), //
	TOMCAT6("tomcat6"), //
	TOMCAT7("tomcat7"); //

	private final Service service;

	private Services(String name) {
		this.service = new Service.Builder(name).build();
	}

	public Service getService() {
		return service;
	}

}
