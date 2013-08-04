package org.kuali.common.util.feature;

import org.kuali.common.util.Assert;
import org.kuali.common.util.project.ImmutableProjectIdentifier;
import org.kuali.common.util.project.ProjectIdentifier;

public final class ImmutableFeatureIdentifier implements FeatureIdentifier {

	private final ImmutableProjectIdentifier project;
	private final String featureId;

	public ImmutableFeatureIdentifier(ProjectIdentifier project, String featureId) {
		this(project.getGroupId(), project.getArtifactId(), featureId);
	}

	public ImmutableFeatureIdentifier(String groupId, String artifactId, String featureId) {
		this(new ImmutableProjectIdentifier(groupId, artifactId), featureId);
	}

	public ImmutableFeatureIdentifier(ImmutableProjectIdentifier project, String featureId) {
		// Make sure we are being configured correctly
		Assert.noBlanks("featureId is blank", featureId);
		Assert.notNull(project, "project is null");

		// Store the project identifier and featureId
		this.project = project;
		this.featureId = featureId;
	}

	@Override
	public String getGroupId() {
		return project.getGroupId();
	}

	@Override
	public String getArtifactId() {
		return project.getArtifactId();
	}

	@Override
	public String getFeatureId() {
		return featureId;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		return toString().equals(object.toString());
	}

	@Override
	public String toString() {
		return project.toString() + ":" + featureId;
	}

}
