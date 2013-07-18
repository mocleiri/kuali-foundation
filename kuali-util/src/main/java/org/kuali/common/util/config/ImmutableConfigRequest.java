package org.kuali.common.util.config;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.project.ImmutableProject;
import org.springframework.util.Assert;

public final class ImmutableConfigRequest {

	final ImmutableProject project;
	final String contextId;
	final String id;

	public ImmutableConfigRequest(ImmutableProject project, String contextId) {
		super();
		Assert.notNull(project, "project is null");

		this.project = project;
		this.contextId = contextId;
		this.id = project.getId() + (StringUtils.isBlank(contextId) ? "" : ":" + contextId);
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return id;
	}

	public ImmutableProject getProject() {
		return project;
	}

	public String getContextId() {
		return contextId;
	}

}
