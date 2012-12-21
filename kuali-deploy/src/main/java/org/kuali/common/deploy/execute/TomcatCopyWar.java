package org.kuali.common.deploy.execute;

import java.io.File;

public class TomcatCopyWar extends CopyToRemote {

	public TomcatCopyWar() {
		this(null, null);
	}

	public TomcatCopyWar(String hostname, File war) {
		super();
		this.hostname = hostname;
		this.localFile = war;
		this.remoteFile = Constants.TOMCAT_ROOT_WAR;
		this.owner = Constants.TOMCAT_USER;
		this.group = Constants.TOMCAT_GROUP;
	}
}
