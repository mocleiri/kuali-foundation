package org.kuali.common.devops.aws.sysadmin.model;

public enum Users {

	EC2USER("ec2-user"), // Default ssh enabled user Amazon creates on Amazon Linux based images
	TOMCAT("tomcat"), // The user tomcat runs as
	ROOT("root", "/root"); // The root user

	private final User user;

	private Users(String login) {
		this.user = new User.Builder(login).build();
	}

	private Users(String login, String home) {
		this.user = new User.Builder(login).home(home).build();
	}

	public User getUser() {
		return user;
	}

}
