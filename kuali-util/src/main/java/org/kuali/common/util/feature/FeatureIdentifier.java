package org.kuali.common.util.feature;

import org.kuali.common.util.Assert;
import org.kuali.common.util.project.ProjectIdentifier;

public final class FeatureIdentifier {

	private final ProjectIdentifier project;
	private final String featureId;
	private final String identifier;
	private final int hashCode;

	public FeatureIdentifier(ProjectIdentifier project, String featureId) {
		// Make sure we are being configured correctly
		Assert.notNull(project, "project is null");
		Assert.notBlank(featureId, "featureId is required");

		// Store the project identifier and featureId
		this.project = project;
		this.featureId = featureId;

		// Cache a reference to the fully qualified identifier string
		this.identifier = project.getIdentifier() + ":" + featureId;

		// Cache the hash code of the identifier string
		this.hashCode = identifier.hashCode();
	}

	public ProjectIdentifier getProject() {
		return project;
	}

	@Override
	public String toString() {
		return identifier;
	}

	public String getFeatureId() {
		return featureId;
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
		FeatureIdentifier other = (FeatureIdentifier) object;

		// The hashcode's being equal AND the identifier strings being equal, constitutes equality
		return hashCode == other.hashCode && identifier.equals(other.identifier);
	}

	public String getIdentifier() {
		return identifier;
	}

}
