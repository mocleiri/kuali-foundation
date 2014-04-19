package org.springframework.jdbc.datasource;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.core.validate.annotation.ValidPort;

@IdiotProofImmutable
public final class OracleConnectionContext {

	private final String username;
	private final String password;
	private final String driver;
	private final String host;
	@ValidPort
	private final int port;
	private final String sid;

	private OracleConnectionContext(Builder builder) {
		this.username = builder.username;
		this.password = builder.password;
		this.driver = builder.driver;
		this.host = builder.host;
		this.port = builder.port;
		this.sid = builder.sid;
	}

	public static OracleConnectionContext build(String username, String password, String host) {
		return builder().withUsername(username).withPassword(password).withHost(host).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<OracleConnectionContext> {

		private String username;
		private String password;
		private String host;
		private String driver = "oracle.jdbc.driver.OracleDriver";
		private int port = 1521;
		private String sid = "XE";

		public Builder withUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder withPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder withDriver(String driver) {
			this.driver = driver;
			return this;
		}

		public Builder withHost(String host) {
			this.host = host;
			return this;
		}

		public Builder withPort(int port) {
			this.port = port;
			return this;
		}

		public Builder withSid(String sid) {
			this.sid = sid;
			return this;
		}

		@Override
		public OracleConnectionContext build() {
			return validate(new OracleConnectionContext(this));
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public String getDriver() {
			return driver;
		}

		public void setDriver(String driver) {
			this.driver = driver;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public String getSid() {
			return sid;
		}

		public void setSid(String sid) {
			this.sid = sid;
		}
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getDriver() {
		return driver;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getSid() {
		return sid;
	}
}
