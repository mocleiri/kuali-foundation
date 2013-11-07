package org.kuali.common.devops.sysadmin.model;

public final class SSHD {

	public SSHD(String localConfigLocation) {
		this("sshd", localConfigLocation, "/etc/ssh/sshd_config");
	}

	public SSHD(String serviceName, String localConfigLocation, String remoteConfigLocation) {
		this.serviceName = serviceName;
		this.localConfigLocation = localConfigLocation;
		this.remoteConfigLocation = remoteConfigLocation;
	}

	private final String serviceName;
	private final String localConfigLocation;
	private final String remoteConfigLocation;

	public String getServiceName() {
		return serviceName;
	}

	public String getLocalConfigLocation() {
		return localConfigLocation;
	}

	public String getRemoteConfigLocation() {
		return remoteConfigLocation;
	}

}
