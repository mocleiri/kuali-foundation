package org.kuali.common.devops.util;

import java.util.Properties;

import org.kuali.common.util.project.model.Project;

public class Application {

	Project project;
	Properties configuration;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Properties getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Properties configuration) {
		this.configuration = configuration;
	}
}
