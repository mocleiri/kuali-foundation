package org.kuali.common.deploy.execute;

import java.io.File;

import org.kuali.common.util.UnixUtils;

public class TomcatCopyWar extends CopyToRemote {

	public TomcatCopyWar() {
		this(null, null);
	}

	public TomcatCopyWar(String hostname, File war) {
		super();
		this.hostname = hostname;
		this.localFile = war;
		this.remoteFile = Constants.TOMCAT_ROOT_WAR;
	}

	@Override
	public void execute() {
		super.execute();
		int exitValue = UnixUtils.sshchown(hostname, Constants.TOMCAT_GROUP, Constants.TOMCAT_USER, getRemoteFile());
		UnixUtils.validate(exitValue, "Error changing WAR file ownership", nonZeroExitValueMode);
	}
}
