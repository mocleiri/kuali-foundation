package org.kuali.maven.mojo;

public class CliContext {
	String server;
	String cmd;
	AntContext antContext;
	JobContext jobContext;

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public AntContext getAntContext() {
		return antContext;
	}

	public void setAntContext(AntContext antContext) {
		this.antContext = antContext;
	}

	public JobContext getJobContext() {
		return jobContext;
	}

	public void setJobContext(JobContext jobContext) {
		this.jobContext = jobContext;
	}

}
