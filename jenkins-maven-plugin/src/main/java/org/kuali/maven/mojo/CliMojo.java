package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * Contact a Jenkins job and schedule a job to run
 * 
 * @goal cli
 * @requiresDependencyResolution test
 */
public class CliMojo extends AbstractCliMojo {

	/**
	 * The command issued to Jenkins CLI eg "help, version, who-ami-i etc"
	 * 
	 * @parameter expression="${jenkins.cmd}" default-value="help"
	 * @required
	 */
	private String cmd;

	@Override
	public void execute() throws MojoExecutionException {
		helper.executeCliCommand(this);
	}

	@Override
	public String getCmd() {
		return cmd;
	}

	@Override
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

}