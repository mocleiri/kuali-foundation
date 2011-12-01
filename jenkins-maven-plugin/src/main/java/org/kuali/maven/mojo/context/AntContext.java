package org.kuali.maven.mojo.context;

import java.io.File;
import java.util.List;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.project.MavenProject;
import org.apache.tools.ant.Project;

public class AntContext {
	Project antProject;
	MavenProject mavenProject;
	String resultProperty;
	File outputFile;
	List<Artifact> pluginArtifacts;
	String[] args;

	public Project getAntProject() {
		return antProject;
	}

	public void setAntProject(Project antProject) {
		this.antProject = antProject;
	}

	public MavenProject getMavenProject() {
		return mavenProject;
	}

	public void setMavenProject(MavenProject mavenProject) {
		this.mavenProject = mavenProject;
	}

	public String getResultProperty() {
		return resultProperty;
	}

	public void setResultProperty(String resultProperty) {
		this.resultProperty = resultProperty;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

	public List<Artifact> getPluginArtifacts() {
		return pluginArtifacts;
	}

	public void setPluginArtifacts(List<Artifact> pluginArtifacts) {
		this.pluginArtifacts = pluginArtifacts;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

}
