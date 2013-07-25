package org.kuali.common.deploy;

public class ServerAgent {

	String appServerStartupOptions;
	Deployable controller;
	String baseDir;
	String logsDir;

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

	public String getLogsDir() {
		return logsDir;
	}

	public void setLogsDir(String logsDir) {
		this.logsDir = logsDir;
	}

	public String getAppServerStartupOptions() {
		return appServerStartupOptions;
	}

	public void setAppServerStartupOptions(String appServerStartupOptions) {
		this.appServerStartupOptions = appServerStartupOptions;
	}

}
