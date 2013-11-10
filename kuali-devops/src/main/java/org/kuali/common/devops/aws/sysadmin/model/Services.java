package org.kuali.common.devops.aws.sysadmin.model;

public enum Services {

	SSHD("sshd", "/etc/ssh/sshd_config");

	private final Service service;

	private Services(String name, String configFileAbsolutePath) {
		this.service = new Service.Builder(name, configFileAbsolutePath).build();
	}

	public Service getService() {
		return service;
	}

}
