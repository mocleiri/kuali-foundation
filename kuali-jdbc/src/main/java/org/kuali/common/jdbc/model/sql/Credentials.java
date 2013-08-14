package org.kuali.common.jdbc.model.sql;

import org.kuali.common.util.Assert;

public final class Credentials {

	public Credentials(String username, String password) {
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
