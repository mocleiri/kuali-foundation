package org.kuali.common.devops.aws.sysadmin.model;

public enum Services {

	SSHD("sshd", "/etc/ssh/sshd_config");

	private final DevOpsService service;

	private Services(String name, String configFileAbsolutePath) {
		this.service = new DevOpsService.Builder(name, configFileAbsolutePath).build();
	}

	public DevOpsService getService() {
		return service;
	}

}
