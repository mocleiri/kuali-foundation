package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.util.Assert;

public final class SSHD {

	private final String localConfigLocation;
	private final String serviceName;
	private final String remoteConfigLocation;
	private final String configFilename;

	public static class Builder {

		private final String localConfigLocation;
		private String serviceName = "sshd";
		private String remoteConfigLocation = "/etc/ssh/sshd_config";
		private String configFilename = "sshd_config";

		public Builder(String localConfigLocation) {
			this.localConfigLocation = localConfigLocation;
		}

		public Builder serviceName(String serviceName) {
			this.serviceName = serviceName;
			return this;
		}

		public Builder remoteConfigLocation(String remoteConfigLocation) {
			this.remoteConfigLocation = remoteConfigLocation;
			return this;
		}

		public Builder configFilename(String configFilename) {
			this.configFilename = configFilename;
			return this;
		}

		public SSHD build() {
			Assert.noBlanks(localConfigLocation, serviceName, remoteConfigLocation, configFilename);
			Assert.exists(localConfigLocation);
			return new SSHD(this);
		}

	}

	private SSHD(Builder builder) {
		this.localConfigLocation = builder.localConfigLocation;
		this.serviceName = builder.serviceName;
		this.remoteConfigLocation = builder.remoteConfigLocation;
		this.configFilename = builder.configFilename;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getLocalConfigLocation() {
		return localConfigLocation;
	}

	public String getRemoteConfigLocation() {
		return remoteConfigLocation;
	}

	public String getConfigFilename() {
		return configFilename;
	}

}
