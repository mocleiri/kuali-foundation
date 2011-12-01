package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * @goal getjob
 * @requiresDependencyResolution test
 */
public class GetJobMojo extends BaseMojo {

	/**
	 * @parameter expression="${jenkins.cmd}" default-value="get-job"
	 * @required
	 */
	private String cmd;

	/**
	 * @parameter expression="${jenkins.type}" default-value="publish"
	 */
	private String type;

	/**
	 * @parameter expression="${jenkins.name}"
	 */
	private String name;

	@Override
	public void execute() throws MojoExecutionException {
		generator.getJob(name, getProject(), type, getWorkingDir(), getServer(), cmd, getLog(), getPluginArtifacts());
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}