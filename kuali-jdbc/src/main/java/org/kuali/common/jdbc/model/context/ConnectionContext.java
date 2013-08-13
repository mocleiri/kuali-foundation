package org.kuali.common.jdbc.model.context;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

public final class ConnectionContext {

	public ConnectionContext(String url) {
		this(url, NullUtils.NONE, NullUtils.NONE);
	}

	public ConnectionContext(String url, String username) {
		this(url, username, NullUtils.NONE);
	}

	public ConnectionContext(String url, String username, String password) {
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
