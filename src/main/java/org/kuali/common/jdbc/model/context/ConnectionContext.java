package org.kuali.common.jdbc.model.context;

import java.util.Properties;

import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;

public final class ConnectionContext {

	private static final Properties DEFAULT_PROPERTIES = PropertyUtils.EMPTY;

	public ConnectionContext(String url) {
		this(url, Credentials.NO_USERNAME);
	}

	public ConnectionContext(String url, String username) {
		this(url, username, Credentials.NO_PASSWORD);
	}

	public ConnectionContext(String url, String username, String password) {
		this(url, new Credentials(username, password), DEFAULT_PROPERTIES);
	}

	public ConnectionContext(String url, Credentials credentials, Properties properties) {
		Assert.noNulls(credentials, properties);
		Assert.noBlanks(url);
		this.url = url;
		this.credentials = credentials;
		this.properties = PropertyUtils.toImmutable(properties);
	}

	private final String url;
	private final Credentials credentials;
	private final Properties properties;

	public String getUrl() {
		return url;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public Properties getProperties() {
		return properties;
	}
}
