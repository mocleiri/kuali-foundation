package org.kuali.common.deploy;

public class ServerAgent {

	String javaStartupOptions;
	Deployable controller;
	String baseDir;
	String logsDir;

	public String getJavaStartupOptions() {
		return javaStartupOptions;
	}

	public void setJavaStartupOptions(String javaStartupOptions) {
		this.javaStartupOptions = javaStartupOptions;
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

	public String getLogsDir() {
		return logsDir;
	}

	public void setLogsDir(String logsDir) {
		this.logsDir = logsDir;
	}

}
