package org.kuali.maven.mojo;

import hudson.cli.CLI;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.apache.tools.ant.BuildLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Java;
import org.apache.tools.ant.types.Commandline.Argument;
import org.apache.tools.ant.types.Path;
import org.kuali.maven.common.AntMavenUtils;

/**
 * @goal cli
 * @requiresDependencyResolution test
 */
public class JenkinsCLIMojo extends AbstractMojo {
	AntMavenUtils antMvnUtils = new AntMavenUtils();

	/**
	 * The Maven project object
	 * 
	 * @parameter expression="${project}"
	 * @readonly
	 */
	private MavenProject project;

	/**
	 * The plugin dependencies.
	 * 
	 * @parameter expression="${plugin.artifacts}"
	 * @required
	 * @readonly
	 */
	private List<Artifact> pluginArtifacts;

	/**
	 * @parameter expression="${jenkins.server}" default-value="${project.ciManagement.url}"
	 * @required
	 */
	private String server;

	@Override
	public void execute() throws MojoExecutionException {
		String[] args = { "-s", server, "get-job", "cm-tools-1.1-publish" };
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
		Map<String, Path> pathRefs = antMvnUtils.getPathRefs(antProject, project, pluginArtifacts);
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

	public MavenProject getProject() {
		return project;
	}

	public List<Artifact> getPluginArtifacts() {
		return pluginArtifacts;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

}