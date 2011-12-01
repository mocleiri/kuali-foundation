package org.kuali.maven.mojo;

import hudson.cli.CLI;

import java.io.IOException;
import java.util.Map;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.tools.ant.BuildLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Java;
import org.apache.tools.ant.types.Commandline.Argument;
import org.apache.tools.ant.types.Path;
import org.kuali.maven.common.AntMavenUtils;

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
			Project antProject = getAntProject();
			Java javaTask = getJavaTask(antProject, args);
			int result = javaTask.executeJava();
			getLog().info("Result: " + result);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected Java getJavaTask(Project antProject, String[] args) throws DependencyResolutionRequiredException {
		Java task = new Java();
		task.setProject(antProject);
		task.setClassname(CLI.class.getName());
		task.setFork(true);
		for (String arg : args) {
			Argument argument = task.createArg();
			argument.setValue(arg);
		}
		Map<String, Path> pathRefs = antMvnUtils.getPathRefs(antProject, getProject(), getPluginArtifacts());
		Path pluginClasspath = pathRefs.get(AntMavenUtils.MVN_PLUGIN_CLASSPATH_KEY);
		task.setClasspath(pluginClasspath);
		return task;
	}

	/**
	 * 
	 */
	protected Project getAntProject() throws IOException {
		Project antProject = new Project();
		antProject.init();
		BuildLogger logger = antMvnUtils.getBuildLogger(getLog());
		antProject.addBuildListener(logger);
		return antProject;
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