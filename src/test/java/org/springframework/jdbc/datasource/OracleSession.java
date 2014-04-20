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

import static com.google.common.base.Optional.fromNullable;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class OracleSession {

	private final String username;
	private final String osuser;
	private final String machine;
	private final Optional<String> clientInfo;

	private OracleSession(Builder builder) {
		this.username = builder.username;
		this.osuser = builder.osuser;
		this.machine = builder.machine;
		this.clientInfo = builder.clientInfo;
	}

	public static class Builder extends ValidatingBuilder<OracleSession> {

		private String username;
		private String osuser;
		private String machine;
		private Optional<String> clientInfo;

		public Builder withUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder withOsuser(String osuser) {
			this.osuser = osuser;
			return this;
		}

		public Builder withMachine(String machine) {
			this.machine = machine;
			return this;
		}

		public Builder withIpAddress(String clientInfo) {
			return withIpAddress(fromNullable(clientInfo));
		}

		public Builder withIpAddress(Optional<String> clientInfo) {
			this.clientInfo = clientInfo;
			return this;
		}

		@Override
		public OracleSession build() {
			return validate(new OracleSession(this));
		}
	}

	public String getUsername() {
		return username;
	}

	public String getOsuser() {
		return osuser;
	}

	public String getMachine() {
		return machine;
	}

	public Optional<String> getClientInfo() {
		return clientInfo;
	}

}
