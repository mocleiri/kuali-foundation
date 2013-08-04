package org.kuali.common.util.feature;

import org.kuali.common.util.Assert;
import org.kuali.common.util.identifier.ImmutableIdentifier;
import org.kuali.common.util.project.ProjectIdentifier;

public final class ImmutableFeatureIdentifier implements FeatureIdentifier {

	private final ProjectIdentifier project;
	private final String featureId;
	private final ImmutableIdentifier identifier;

	public ImmutableFeatureIdentifier(ProjectIdentifier project, String featureId) {

		// Make sure we are being configured correctly
		Assert.notNull(project, "project is null");
		Assert.notBlank(featureId, "featureId is blank");

		// Store the project identifier and featureId
		this.project = project;
		this.featureId = featureId;

		// Cache a reference to the fully qualified feature identifier string
		this.identifier = new ImmutableIdentifier(project.getIdentifier() + ":" + featureId);
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
		return identifier.toString();
	}

	@Override
	public int hashCode() {
		return identifier.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		return identifier.equals(object);
	}

	@Override
	public String getIdentifier() {
		return identifier.getIdentifier();
	}

}
