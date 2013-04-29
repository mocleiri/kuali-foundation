package org.kuali.common.deploy;

import java.io.File;

public class MachineAgent {

	String startupCommand;
	String baseDir;
	String tmpDir;
	String logsDir;
	Deployable controller;
	File logFile;
	String startupToken;

	public String getStartupCommand() {
		return startupCommand;
	}

	public void setStartupCommand(String startupCommand) {
		this.startupCommand = startupCommand;
	}

	public String getTmpDir() {
		return tmpDir;
	}

	public void setTmpDir(String tmpDir) {
		this.tmpDir = tmpDir;
	}

	public String getLogsDir() {
		return logsDir;
	}

	public void setLogsDir(String logsDir) {
		this.logsDir = logsDir;
	}

	public Deployable getController() {
		return controller;
	}

	public void setController(Deployable controller) {
		this.controller = controller;
	}

	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	public File getLogFile() {
		return logFile;
	}

	public void setLogFile(File logFile) {
		this.logFile = logFile;
	}

	public String getStartupToken() {
		return startupToken;
	}

	public void setStartupToken(String startupToken) {
		this.startupToken = startupToken;
	}

}
