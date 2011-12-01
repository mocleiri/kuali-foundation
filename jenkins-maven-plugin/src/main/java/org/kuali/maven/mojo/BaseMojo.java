package org.kuali.maven.mojo;

import java.util.List;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;
import org.kuali.maven.common.AntMavenUtils;

/**
 * 
 */
public abstract class BaseMojo extends AbstractMojo {
	AntMavenUtils antMvnUtils = new AntMavenUtils();
	Generator generator = new Generator();

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

	/**
	 * The format for timestamp displays
	 * 
	 * @parameter expression="${jenkins.timestampFormat}" default-value="yyyy-MM-dd HH:mm:ss z"
	 * @required
	 */
	private String timestampFormat;

	/**
	 * The working directory for the plugin
	 * 
	 * @parameter expression="${jenkins.workingDir}" default-value="${project.build.directory}/jenkins"
	 * @required
	 */
	private String workingDir;

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

	public String getTimestampFormat() {
		return timestampFormat;
	}

	public void setTimestampFormat(String timestampFormat) {
		this.timestampFormat = timestampFormat;
	}

	public String getWorkingDir() {
		return workingDir;
	}

	public void setWorkingDir(String workingDir) {
		this.workingDir = workingDir;
	}

}