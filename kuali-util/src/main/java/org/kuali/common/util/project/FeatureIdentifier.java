package org.kuali.common.util.project;

import org.kuali.common.util.Assert;

public final class FeatureIdentifier {

	private final ProjectIdentifier projectId;
	private final String featureId;

	public FeatureIdentifier(ProjectIdentifier projectId, String featureId) {
		super();
		Assert.notNull(projectId, "projectId is null");
		Assert.notBlank(featureId, "featureId is required");
		this.projectId = projectId;
		this.featureId = featureId;
	}

	public ProjectIdentifier getProjectId() {
		return projectId;
	}

	public String getFeatureId() {
		return featureId;
	}

}
