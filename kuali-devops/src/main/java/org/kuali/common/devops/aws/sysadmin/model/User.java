package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.util.Assert;

public final class User {

	public User(String login) {
		this(login, "/home/" + login);
	}

	public User(String login, String home) {
		this(login, home, home + "/.ssh/authorized_keys", login);
	}

	public User(String login, String home, String authorizedKeys, String group) {
		Assert.noBlanks(login, home, authorizedKeys, group);
		this.login = login;
		this.home = home;
		this.authorizedKeys = authorizedKeys;
		this.group = group;
	}

	private final String login;
	private final String home;
	private final String group;
	private final String authorizedKeys;

	public String getLogin() {
		return login;
	}

	public String getHome() {
		return home;
	}

	public String getAuthorizedKeys() {
		return authorizedKeys;
	}

}
