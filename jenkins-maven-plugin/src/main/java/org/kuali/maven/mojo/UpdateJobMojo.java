package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * Contact a Jenkins server and update an existing jobs configuration
 * 
 * @goal updatejob
 * @requiresDependencyResolution test
 */
public class UpdateJobMojo extends AbstractJobConfigMojo {

	/**
	 * The command issued to Jenkins CLI
	 * 
	 * @parameter expression="${jenkins.cmd}" default-value="update-job"
	 * @required
	 */
	private String cmd;

	/**
	 * The type of job to update
	 * 
	 * @parameter expression="${jenkins.type}" default-value="publish"
	 * @required
	 */
	private String type;

	@Override
	public void execute() throws MojoExecutionException {
		helper.pushJobToJenkins(this, type);
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