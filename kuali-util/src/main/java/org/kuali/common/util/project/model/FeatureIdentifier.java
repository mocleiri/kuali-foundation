package org.kuali.common.util.project.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.ObjectUtils;
import org.kuali.common.util.identify.Identifiable;

public final class FeatureIdentifier implements Identifiable {

	private final ProjectIdentifier project;
	private final String featureId;

	private final String identifier;
	private final int hashCode;

	public FeatureIdentifier(ProjectIdentifier project, String featureId) {
		// Make sure we are being configured correctly
		Assert.noNulls(project);
		Assert.noBlanks(featureId);

		// Finish setting things up
		this.project = project;
		this.featureId = featureId;
		this.identifier = project.getIdentifier() + ":" + featureId;
		this.hashCode = identifier.hashCode();
	}

	public FeatureIdentifier(String groupId, String artifactId, String featureId) {
		this(new ProjectIdentifier(groupId, artifactId), featureId);
	}

	public ProjectIdentifier getProject() {
		return project;
	}

	public String getFeatureId() {
		return featureId;
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public String toString() {
		return identifier;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public boolean equals(Object object) {
		return ObjectUtils.equalsByToString(this, object);
	}

}
