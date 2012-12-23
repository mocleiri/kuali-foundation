package org.kuali.common.deploy.context;

import java.io.File;

public class AppDynamicsContext {

	String remoteControllerDir;
	String remoteControllerFile;
	File localControllerFile;

	public String getRemoteControllerDir() {
		return remoteControllerDir;
	}

	public void setRemoteControllerDir(String remoteControllerDir) {
		this.remoteControllerDir = remoteControllerDir;
	}

	public String getRemoteControllerFile() {
		return remoteControllerFile;
	}

	public void setRemoteControllerFile(String remoteControllerFile) {
		this.remoteControllerFile = remoteControllerFile;
	}

	public File getLocalControllerFile() {
		return localControllerFile;
	}

	public void setLocalControllerFile(File localControllerFile) {
		this.localControllerFile = localControllerFile;
	}

}
