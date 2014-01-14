package org.kuali.common.deploy.monitoring;

import org.kuali.common.deploy.Deployable;
import org.kuali.common.util.Assert;

public class ServerAgent {

	public ServerAgent(String appServerStartupOptions, Deployable controller, String baseDir, String logsDir) {
		Assert.noBlanks(appServerStartupOptions, baseDir, logsDir);
		Assert.noNulls(controller);
		this.appServerStartupOptions = appServerStartupOptions;
		this.controller = controller;
		this.baseDir = baseDir;
		this.logsDir = logsDir;
	}

	private final String appServerStartupOptions;
	private final Deployable controller;
	private final String baseDir;
	private final String logsDir;

	public String getAppServerStartupOptions() {
		return appServerStartupOptions;
	}

	public Deployable getController() {
		return controller;
	}

	public String getBaseDir() {
		return baseDir;
	}

	public String getLogsDir() {
		return logsDir;
	}

}
