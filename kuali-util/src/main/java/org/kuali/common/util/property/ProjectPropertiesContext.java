package org.kuali.common.util.property;

import java.util.Properties;

import org.kuali.common.util.Project;

public class ProjectPropertiesContext {

	Project project;
	Properties properties;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
