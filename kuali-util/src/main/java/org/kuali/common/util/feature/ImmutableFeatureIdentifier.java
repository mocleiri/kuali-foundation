package org.kuali.common.util.feature;

import org.kuali.common.util.Assert;
import org.kuali.common.util.ObjectUtils;
import org.kuali.common.util.project.ProjectIdentifier;

public final class ImmutableFeatureIdentifier implements FeatureIdentifier {

	private final String groupId;
	private final String artifactId;
	private final String featureId;

	// This is used to simplify hashCode() and equals()
	private final String identifier;

	public ImmutableFeatureIdentifier(ProjectIdentifier project, String featureId) {
		this(project.getGroupId(), project.getArtifactId(), featureId);
	}

	public ImmutableFeatureIdentifier(String groupId, String artifactId, String featureId) {
		// Make sure we are being configured correctly
		Assert.noBlanks("groupId, artifactId, and featureId are required", groupId, artifactId, featureId);

		// Store the groupId, artifactId, and featureId
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.featureId = featureId;

		// Changing this affects both hashCode() and equals(), be careful ...
		this.identifier = groupId + ":" + artifactId + ":" + featureId;
	}

	@Override
	public String getGroupId() {
		return groupId;
	}

	@Override
	public String getArtifactId() {
		return artifactId;
	}

	@Override
	public String getFeatureId() {
		return featureId;
	}

	@Override
	public String toString() {
		// Changing this affects both hashCode() and equals(), be careful ...
		return identifier;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public boolean equals(Object object) {
		return ObjectUtils.equalsByToString(this, object);
	}

}
