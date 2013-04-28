package org.kuali.common.deploy;

public class ServerAgent {

	String javaStartupOptions;
	Deployable controller;

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

}
