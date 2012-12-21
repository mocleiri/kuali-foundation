package org.kuali.common.deploy.execute;

import java.util.Arrays;

public class TomcatRemoveJdbcDrivers extends RemoteRm {

	public TomcatRemoveJdbcDrivers() {
		this(null);
	}

	public TomcatRemoveJdbcDrivers(String hostname) {
		super();
		this.hostname = hostname;
		this.files = Arrays.asList(Constants.TOMCAT_LIB + "/mysql*.jar ", Constants.TOMCAT_LIB + "/oracle*.jar");
	}
}
