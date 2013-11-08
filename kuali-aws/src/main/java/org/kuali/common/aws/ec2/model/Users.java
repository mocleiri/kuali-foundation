package org.kuali.common.aws.ec2.model;

public enum Users {

	EC2USER("ec2-user", "/home/ec2-user"), //
	ROOT("root", "/root"); //

	private final String login;
	private final String home;

	private Users(String login, String home) {
		this.login = login;
		this.home = home;
	}

	public String getLogin() {
		return login;
	}

	public String getHome() {
		return home;
	}

}
