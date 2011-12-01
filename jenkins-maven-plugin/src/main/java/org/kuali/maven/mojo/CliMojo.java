package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * Execute a Jenkins CLI command
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