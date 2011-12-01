package org.kuali.maven.mojo;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

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
		try {
			String jobName = generator.getJobName(name, getProject(), type);
			File output = new File(getWorkingDir() + FS + jobName + ".xml");
			FileUtils.touch(output);
			String[] args = getArgs("-s", getServer(), getCmd(), jobName);
			Project antProject = generator.getAntProject(getLog());
			AntContext context = generator.getAntContext(antProject, getProject(), args, output, getPluginArtifacts());
			Task task = generator.getJavaTask(context);
			getLog().info("");
			getLog().info("Jenkins Instance - " + getServer());
			getLog().info("Job Name - " + jobName);
			getLog().info("File - " + output.getAbsolutePath());
			getLog().info("");
			task.execute();
			int result = new Integer(antProject.getProperty(Generator.JAVA_RESULT_PROPERTY));
			generator.handleResult(context, result, getLog());
		} catch (Exception e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}

	protected String[] getArgs(String... args) {
		return args;
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