package org.kuali.common.deploy.execute;

public class TomcatRemoveJdbcDrivers extends RemoteRm {

	public TomcatRemoveJdbcDrivers() {
		this(null);
	}

	public TomcatRemoveJdbcDrivers(String hostname) {
		super();
		this.hostname = hostname;
		this.file = Constants.TOMCAT_LIB + "/mysql*.jar " + Constants.TOMCAT_LIB + "/oracle*.jar";
	}
}
