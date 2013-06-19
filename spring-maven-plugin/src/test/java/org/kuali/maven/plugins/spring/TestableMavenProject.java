package org.kuali.maven.plugins.spring;

import java.io.File;
import java.util.Properties;

import org.apache.maven.project.MavenProject;

public class TestableMavenProject extends MavenProject {

	Properties properties;
	File basedir;
	String artifactId;
	String groupId;

	@Override
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public File getBasedir() {
		return basedir;
	}

	public void setBasedir(File basedir) {
		this.basedir = basedir;
	}

	@Override
	public String getArtifactId() {
		return artifactId;
	}

	@Override
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	@Override
	public String getGroupId() {
		return groupId;
	}

	@Override
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}
