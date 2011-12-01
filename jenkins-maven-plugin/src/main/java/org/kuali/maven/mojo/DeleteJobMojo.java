package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * Connect to a Jenkins server and delete a job
 * 
 * @goal deletejob
 * @requiresDependencyResolution test
 */
public class DeleteJobMojo extends AbstractCliMojo {

	/**
	 * The command issued to Jenkins CLI
	 * 
	 * @parameter expression="${jenkins.cmd}" default-value="delete-job"
	 * @required
	 */
	private String cmd;

	/**
	 * The type of job to delete
	 * 
	 * @parameter expression="${jenkins.type}" default-value="publish"
	 * @required
	 */
	private String type;

	/**
	 * The name of the job to delete. If name is provided, 'type' is ignored
	 * 
	 * @parameter expression="${jenkins.name}"
	 * @required
	 */
	private String name;

	@Override
	public void execute() throws MojoExecutionException {
		helper.executeCliJobCommand(this, name, type);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}