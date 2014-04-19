package org.springframework.jdbc.datasource;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class OracleSession {

	private final String username;
	private final String osuser;
	private final String machine;
	private final Optional<String> ipAddress;

	private OracleSession(Builder builder) {
		this.username = builder.username;
		this.osuser = builder.osuser;
		this.machine = builder.machine;
		this.ipAddress = builder.ipAddress;
	}

	public static class Builder extends ValidatingBuilder<OracleSession> {

		private String username;
		private String osuser;
		private String machine;
		private Optional<String> ipAddress = absent();

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

		public Builder withIpAddress(String ipAddress) {
			return withIpAddress(fromNullable(ipAddress));
		}

		public Builder withIpAddress(Optional<String> ipAddress) {
			this.ipAddress = ipAddress;
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

	public Optional<String> getIpAddress() {
		return ipAddress;
	}

}
