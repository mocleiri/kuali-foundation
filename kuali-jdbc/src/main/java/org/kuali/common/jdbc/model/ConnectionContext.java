package org.kuali.common.jdbc.model;

import org.kuali.common.util.Assert;

public final class ConnectionContext {

	private ConnectionContext(String url, String username, String password) {
		Assert.noBlanks(url, username, password);
		this.url = url;
		this.username = username;
		this.password = password;
	}

	private final String url;
	private final String username;
	private final String password;

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
