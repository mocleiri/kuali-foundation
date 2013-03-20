package org.kuali.common.util.property;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Project;

public class ProjectPropertiesContext {

	Project project;
	Properties properties;
	List<String> locations;
	String label;

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

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
