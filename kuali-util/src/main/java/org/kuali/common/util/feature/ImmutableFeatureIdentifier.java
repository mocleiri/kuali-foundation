package org.kuali.common.util.feature;

import org.kuali.common.util.Assert;
import org.kuali.common.util.project.ProjectIdentifier;

public final class ImmutableFeatureIdentifier implements FeatureIdentifier {

	private final ProjectIdentifier project;
	private final String featureId;
	private final String identifier;
	private final int hashCode;

	public ImmutableFeatureIdentifier(ProjectIdentifier project, String featureId) {

		// Make sure we are being configured correctly
		Assert.notNull(project, "project is null");
		Assert.notBlank(featureId, "featureId is blank");

		// Store the project identifier and featureId
		this.project = project;
		this.featureId = featureId;

		// Cache a reference to the fully qualified feature identifier string
		this.identifier = project.getGroupId() + ":" + project.getArtifactId() + ":" + featureId;

		// Cache the hash code of the identifier string
		this.hashCode = identifier.hashCode();
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
	public String toString() {
		return identifier;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public boolean equals(Object object) {

		// They are the exact same physical object
		if (this == object) {
			return true;
		}

		// If object is null or a different runtime type, it's not equal
		if (object == null || getClass() != object.getClass()) {
			return false;
		}

		// Cast object to a FeatureIdentifier
		ImmutableFeatureIdentifier other = (ImmutableFeatureIdentifier) object;

		// The hashcode's being equal AND the identifier strings being equal, constitutes equality
		return hashCode == other.hashCode && identifier.equals(other.identifier);
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

}
