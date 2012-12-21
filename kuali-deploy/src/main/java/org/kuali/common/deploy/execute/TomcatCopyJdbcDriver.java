package org.kuali.common.deploy.execute;

import java.io.File;

public class TomcatCopyJdbcDriver extends CopyToRemoteDir {

	public TomcatCopyJdbcDriver() {
		this(null, null);
	}

	public TomcatCopyJdbcDriver(String hostname, File jdbcDriver) {
		super();
		this.hostname = hostname;
		this.localFile = jdbcDriver;
		this.remoteDir = Constants.TOMCAT_LIB;
		this.owner = Constants.TOMCAT_USER;
		this.group = Constants.TOMCAT_GROUP;
	}

}
