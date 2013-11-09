package org.kuali.common.devops.aws.sysadmin.model;

public enum Services {

	SSHD("sshd", "/etc/ssh/sshd_config");

	private final Service service;

	private Services(String name, String configFileLocation) {
		this.service = new Service.Builder(name, configFileLocation).build();
	}

	public Service getService() {
		return service;
	}

}
