package org.kuali.common.deploy.aws.model;

import org.kuali.common.util.Assert;

public final class SesCredentials {

	public SesCredentials(String username, String password) {
		Assert.noBlanks(username, password);
		this.username = username;
		this.password = password;
	}

	private final String username;
	private final String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
