package org.kuali.common.jdbc.model.context;

import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.util.Assert;

public final class ConnectionContext {

	public ConnectionContext(String url) {
		this(url, Credentials.EMPTY);
	}

	public ConnectionContext(String url, String username) {
		this(url, new Credentials(username));
	}

	public ConnectionContext(String url, String username, String password) {
		this(url, new Credentials(username, password));
	}

	public ConnectionContext(String url, Credentials credentials) {
		Assert.noBlanks(url);
		this.url = url;
		this.credentials = credentials;
	}

	private final String url;
	private final Credentials credentials;

	public String getUrl() {
		return url;
	}

	public Credentials getCredentials() {
		return credentials;
	}
}
