package org.kuali.maven.mojo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

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
		try {
			String jobName = generator.getJobName(name, getProject(), type);
			File outputFile = new File(getWorkingDir() + FS + jobName + ".xml");
			FileUtils.touch(outputFile);
			String[] args = getArgs(jobName);
			Project antProject = generator.getAntProject(getLog());
			Task task = generator.getJavaTask(antProject, getProject(), args, getPluginArtifacts(), outputFile);
			task.execute();
			int result = new Integer(antProject.getProperty("java.result"));
			getLog().info("Result: " + result);
		} catch (Exception e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}

	protected String[] getArgs(String jobName) {
		List<String> args = new ArrayList<String>();
		args.add("-s");
		args.add(getServer());
		args.add(getCmd());
		args.add(jobName);
		return args.toArray(new String[args.size()]);
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