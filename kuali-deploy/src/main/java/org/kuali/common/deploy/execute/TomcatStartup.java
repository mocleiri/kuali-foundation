package org.kuali.common.deploy.execute;


public class TomcatStartup extends RemoteShellScript {

	public TomcatStartup() {
		this(null);
	}

	public TomcatStartup(String hostname) {
		super();
		this.hostname = hostname;
		this.login = Constants.TOMCAT_USER;
		this.script = Constants.TOMCAT_STARTUP;
	}
}
