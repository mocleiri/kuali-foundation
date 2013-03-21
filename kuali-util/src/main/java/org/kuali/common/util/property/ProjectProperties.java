package org.kuali.common.util.property;

import java.util.List;

import org.kuali.common.util.Project;

public class ProjectProperties implements Comparable<ProjectProperties> {

	Project project;
	int sequence;
	List<PropertiesLoaderContext> loaderContexts;

	@Override
	public int compareTo(ProjectProperties other) {
		return Double.compare(sequence, other.getSequence());
	}

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

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

}