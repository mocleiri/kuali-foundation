package org.kuali.common.devops.aws.sysadmin.model;

public enum Users {

	EC2USER("ec2-user", "/home/ec2-user"), //
	ROOT("root", "/root"); //

	private final User user;

	private Users(String login, String home) {
		this.user = new User(login, home);
	}

	public User getUser() {
		return user;
	}

}
