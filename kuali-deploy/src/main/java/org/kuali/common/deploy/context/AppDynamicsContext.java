package org.kuali.common.deploy.context;

import java.io.File;

public class AppDynamicsContext {

	String remoteControllerDir;
	String remoteControllerFile;
	File controller;

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

	public File getController() {
		return controller;
	}

	public void setController(File controller) {
		this.controller = controller;
	}

}
