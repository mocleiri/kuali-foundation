package org.kuali.common.util.config;

import org.kuali.common.util.project.ImmutableProject;
import org.springframework.util.Assert;

public final class ImmutableConfig {

	final ImmutableProject project;
	final String contextId;

	public ImmutableConfig(ImmutableProject project, String contextId) {
		super();
		Assert.notNull(project, "project is null");

		this.project = project;
		this.contextId = contextId;
	}

	public ImmutableProject getProject() {
		return project;
	}

	public String getContextId() {
		return contextId;
	}

}
