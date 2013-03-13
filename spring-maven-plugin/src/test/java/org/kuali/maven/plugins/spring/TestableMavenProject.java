package org.kuali.maven.plugins.spring;

import java.util.Properties;

import org.apache.maven.project.MavenProject;

public class TestableMavenProject extends MavenProject {

	Properties properties;

	public TestableMavenProject() {
		this(null);
	}

	public TestableMavenProject(Properties properties) {
		super();
		this.properties = properties;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
