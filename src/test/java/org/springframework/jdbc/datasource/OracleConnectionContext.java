/**
 * Copyright 2010-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
