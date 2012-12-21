package org.kuali.common.deploy.execute;

import java.io.File;

public class TomcatCopySetEnv extends CopyToRemote {

	public TomcatCopySetEnv() {
		this(null, null);
	}

	public TomcatCopySetEnv(String hostname, File setenv) {
		super();
		this.hostname = hostname;
		this.localFile = setenv;
		this.remoteFile = Constants.TOMCAT_SETENV;
		this.owner = Constants.TOMCAT_USER;
		this.group = Constants.TOMCAT_GROUP;
	}
}
