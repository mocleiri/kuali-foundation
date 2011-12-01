package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Java;

/**
 * @goal getjob
 * @requiresDependencyResolution test
 */
public class GetJobMjo extends BaseMojo {

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
		String[] args = { "-s", getServer(), getCmd(), "cm-tools-1.1-publish" };
		try {
			Project antProject = generator.getAntProject(getLog());
			Java javaTask = generator.getJavaTask(antProject, getProject(), args, getPluginArtifacts());
			int result = javaTask.executeJava();
			getLog().info("Result: " + result);
		} catch (Throwable e) {
			e.printStackTrace();
		}
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