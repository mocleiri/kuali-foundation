package org.kuali.common.deploy.env.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.project.model.Project;

public final class DeployEnvironment {

	public DeployEnvironment(Project project, String id, String name) {
		Assert.noBlanks(id, name);
		Assert.noNulls(project);
		this.id = id;
		this.project = project;
		this.name = name;
	}

	private final String id;
	private final Project project;
	private final String name;

	public String getId() {
		return id;
	}

	public Project getProject() {
		return project;
	}

	public String getName() {
		return name;
	}

}
