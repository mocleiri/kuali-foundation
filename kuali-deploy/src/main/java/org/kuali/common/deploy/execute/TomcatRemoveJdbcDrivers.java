package org.kuali.common.deploy.execute;


public class TomcatRemoveJdbcDrivers extends RemoteRm {

	public TomcatRemoveJdbcDrivers() {
		this(null);
	}

	public TomcatRemoveJdbcDrivers(String hostname) {
		super();
		this.hostname = hostname;
		this.files = Constants.JDBC_DRIVER_PATTERNS;
	}
}
