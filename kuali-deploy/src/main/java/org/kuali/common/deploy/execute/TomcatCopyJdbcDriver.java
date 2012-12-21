package org.kuali.common.deploy.execute;

public class TomcatCopyJdbcDriver extends ScpFileToRemote {

	public TomcatCopyJdbcDriver() {
		super();
		this.destinationDirectory = "/usr/local/tomcat/lib";
	}

}
