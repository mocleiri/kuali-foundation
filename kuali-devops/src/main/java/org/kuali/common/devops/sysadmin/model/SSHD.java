package org.kuali.common.devops.sysadmin.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;

public final class SSHD {

	public SSHD(String localConfigLocation) {
		this("sshd", localConfigLocation, "/etc/ssh/sshd_config");
	}

	public SSHD(String serviceName, String localConfigLocation, String remoteConfigLocation) {
		Assert.noBlanks(serviceName, localConfigLocation, remoteConfigLocation);
		Assert.exists(localConfigLocation);
		this.serviceName = serviceName;
		this.localConfigLocation = localConfigLocation;
		this.remoteConfigLocation = remoteConfigLocation;
		this.configFilename = LocationUtils.getResource(localConfigLocation).getFilename();
	}

	private final String serviceName;
	private final String localConfigLocation;
	private final String remoteConfigLocation;
	private final String configFilename;

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
