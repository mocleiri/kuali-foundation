package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * Contact a Jenkins server and schedule a job to run
 * 
 * @goal runjob
 * @requiresDependencyResolution test
 */
public class RunJobMojo extends AbstractCliMojo {

	/**
	 * The command issued to Jenkins CLI
	 * 
	 * @parameter expression="${jenkins.cmd}" default-value="build"
	 * @required
	 */
	private String cmd;

	/**
	 * The type of job to run
	 * 
	 * @parameter expression="${jenkins.type}" default-value="publish"
	 * @required
	 */
	private String type;

	@Override
	public void execute() throws MojoExecutionException {
		helper.executeCliJobCommand(this, type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

}