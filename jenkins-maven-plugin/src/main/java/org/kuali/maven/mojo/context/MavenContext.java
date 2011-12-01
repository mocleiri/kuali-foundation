package org.kuali.maven.mojo.context;

import java.util.List;
import java.util.Properties;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

public class MavenContext {
	MavenProject project;
	List<Artifact> pluginArtifacts;
	Log log;
	String scmType;
	String scmUrl;
	String majorVersion;
	Properties properties;

	public MavenProject getProject() {
		return project;
	}

	public void setProject(MavenProject mavenProject) {
		this.project = mavenProject;
	}

	public String getScmType() {
		return scmType;
	}

	public void setScmType(String scmType) {
		this.scmType = scmType;
	}

	public String getScmUrl() {
		return scmUrl;
	}

	public void setScmUrl(String scmUrl) {
		this.scmUrl = scmUrl;
	}

	public String getMajorVersion() {
		return majorVersion;
	}

	public void setMajorVersion(String majorVersion) {
		this.majorVersion = majorVersion;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public List<Artifact> getPluginArtifacts() {
		return pluginArtifacts;
	}

	public void setPluginArtifacts(List<Artifact> pluginArtifacts) {
		this.pluginArtifacts = pluginArtifacts;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
