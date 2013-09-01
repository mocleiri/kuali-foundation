package org.kuali.common.deploy.monitoring;

import org.kuali.common.deploy.Deployable;


public class MachineAgent {

	String startupCommand;
	String baseDir;
	String tmpDir;
	String logsDir;
	Deployable controller;
	String logFile;
	String logFileEncoding;
	String startupToken;
	int logFileIntervalMillis;
	int startupTimeoutMillis;

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

	public String getLogFile() {
		return logFile;
	}

	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}

	public String getStartupToken() {
		return startupToken;
	}

	public void setStartupToken(String startupToken) {
		this.startupToken = startupToken;
	}

	public int getStartupTimeoutMillis() {
		return startupTimeoutMillis;
	}

	public void setStartupTimeoutMillis(int startupTimeoutMillis) {
		this.startupTimeoutMillis = startupTimeoutMillis;
	}

	public int getLogFileIntervalMillis() {
		return logFileIntervalMillis;
	}

	public void setLogFileIntervalMillis(int logFileIntervalMillis) {
		this.logFileIntervalMillis = logFileIntervalMillis;
	}

	public String getLogFileEncoding() {
		return logFileEncoding;
	}

	public void setLogFileEncoding(String logFileEncoding) {
		this.logFileEncoding = logFileEncoding;
	}

}
