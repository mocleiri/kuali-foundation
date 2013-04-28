package org.kuali.common.deploy;

public class MachineAgent {

	String startupCommand;
	String tmpDir;
	String logsDir;
	Deployable controller;

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

}
