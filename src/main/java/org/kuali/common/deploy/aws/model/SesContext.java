package org.kuali.common.deploy.aws.model;

import org.kuali.common.util.Assert;

public final class SesContext {

	private final String host;
	private final SesCredentials credentials;
	private final boolean debug;
	private final int port;
	private final boolean sslEnable;
	private final boolean auth;

	public static class Builder {

		// Required
		private final String host;
		private final SesCredentials credentials;

		// Optional
		private boolean debug = false;
		private int port = 465;
		private boolean sslEnable = true;
		private boolean auth = true;

		public Builder(String host, SesCredentials credentials) {
			this.host = host;
			this.credentials = credentials;
		}

		public Builder debug(boolean debug) {
			this.debug = debug;
			return this;
		}

		public Builder port(int port) {
			this.port = port;
			return this;
		}

		public Builder sslEnable(boolean sslEnable) {
			this.sslEnable = sslEnable;
			return this;
		}

		public Builder auth(boolean auth) {
			this.auth = auth;
			return this;
		}

		public SesContext build() {
			Assert.noBlanks(host);
			Assert.noNulls(credentials);
			Assert.isTrue(port > 0, "port must be a positive integer");
			return new SesContext(this);
		}
	}

	private SesContext(Builder builder) {
		this.host = builder.host;
		this.credentials = builder.credentials;
		this.debug = builder.debug;
		this.port = builder.port;
		this.sslEnable = builder.sslEnable;
		this.auth = builder.auth;
	}

	public String getHost() {
		return host;
	}

	public SesCredentials getCredentials() {
		return credentials;
	}

	public boolean isDebug() {
		return debug;
	}

	public int getPort() {
		return port;
	}

	public boolean isSslEnable() {
		return sslEnable;
	}

	public boolean isAuth() {
		return auth;
	}

}
