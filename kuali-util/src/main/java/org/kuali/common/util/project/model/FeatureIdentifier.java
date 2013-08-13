package org.kuali.common.util.project.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.ObjectUtils;
import org.kuali.common.util.identify.Identifiable;

public final class FeatureIdentifier implements Identifiable {

	private final String groupId;
	private final String artifactId;
	private final String featureId;

	private final String identifier;
	private final int hashCode;

	public FeatureIdentifier(ProjectIdentifier projectId, String featureId) {
		this(projectId.getGroupId(), projectId.getArtifactId(), featureId);
	}

	public FeatureIdentifier(String groupId, String artifactId, String featureId) {
		// Make sure we are being configured correctly
		Assert.noBlanks("groupId, artifactId, and featureId are required", groupId, artifactId, featureId);

		// Store the groupId, artifactId, and featureId
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.featureId = featureId;

		// Cache the identifier string + the hashcode of the identifier string to speed up hashing functions
		this.identifier = groupId + ":" + artifactId;
		this.hashCode = identifier.hashCode();
	}

	public String getGroupId() {
		return groupId;
	}

	public String getArtifactId() {
		return artifactId;
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
		return ObjectUtils.equalsByHashCode(this, object);
	}

}
