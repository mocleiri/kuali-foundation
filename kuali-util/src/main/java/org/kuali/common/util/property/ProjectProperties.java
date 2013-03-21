package org.kuali.common.util.property;

import java.util.List;

import org.kuali.common.util.Project;

public class ProjectProperties {

	Project project;
	String label;
	List<PropertiesLoaderContext> loaderContexts;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<PropertiesLoaderContext> getLoaderContexts() {
		return loaderContexts;
	}

	public void setLoaderContexts(List<PropertiesLoaderContext> loaderContexts) {
		this.loaderContexts = loaderContexts;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
