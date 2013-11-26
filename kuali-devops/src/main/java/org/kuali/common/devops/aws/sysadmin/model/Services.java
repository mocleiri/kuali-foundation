package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.devops.model.Node;

public enum Services {

	SSHD("sshd", "/etc/ssh/sshd_config");

	private final Node service;

	private Services(String name, String configFileAbsolutePath) {
		this.service = new Node.Builder(name, configFileAbsolutePath).build();
	}

	public Node getService() {
		return service;
	}

}
