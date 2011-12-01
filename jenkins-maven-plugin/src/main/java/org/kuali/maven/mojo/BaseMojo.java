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