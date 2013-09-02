package org.kuali.common.deploy.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.project.model.Project;

public final class DeployEnvironment {

	public DeployEnvironment(Project project, String id, String name, String publicUrl) {
		Assert.noBlanks(id, name, publicUrl);
		Assert.noNulls(project);
		this.id = id;
		this.project = project;
		this.name = name;
		this.publicUrl = publicUrl;
	}

	private final String id;
	private final Project project;
	private final String name;
	private final String publicUrl;

	public String getId() {
		return id;
	}

	public Project getProject() {
		return project;
	}

	public String getName() {
		return name;
	}

	public String getPublicUrl() {
		return publicUrl;
	}

}
