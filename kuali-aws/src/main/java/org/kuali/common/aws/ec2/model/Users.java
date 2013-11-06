package org.kuali.common.aws.ec2.model;

public enum Users {

	EC2USER("ec2-user"), //
	ROOT("root"); //

	private final String login;

	private Users(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

}
