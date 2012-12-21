package org.kuali.common.deploy.execute;

import java.io.File;

import org.kuali.common.util.UnixUtils;

public class AppDynamicsSetup extends CopyToRemote {

	public AppDynamicsSetup() {
		this(null, null);
	}

	public AppDynamicsSetup(String hostname, File controller) {
		super();
		this.hostname = hostname;
		this.localFile = controller;
		this.remoteFile = Constants.APP_DYNAMICS_CONTROLLER;
		this.owner = Constants.TOMCAT_USER;
		this.group = Constants.TOMCAT_GROUP;
	}

	@Override
	public void execute() {
		int mkdir = UnixUtils.sshmkdir(user, hostname, Constants.APP_DYNAMICS_DIR);
		UnixUtils.validate(mkdir, "Error creating AppDynamics directory", nonZeroExitValueMode);
		super.execute();
	}

}
