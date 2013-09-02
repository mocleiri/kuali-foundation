package org.kuali.common.deploy.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.project.model.Project;

public final class DeployEnvironment {

	public DeployEnvironment(Project project, int sequence, String name, String publicUrl) {
		Assert.noBlanks(name, publicUrl);
		Assert.noNulls(project);
		Assert.isTrue(sequence > 0, "sequence must be a positive integer");
		this.sequence = sequence;
		this.project = project;
		this.name = name;
		this.publicUrl = publicUrl;
	}

	private final int sequence;
	private final Project project;
	private final String name;
	private final String publicUrl;

	public int getSequence() {
		return sequence;
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
