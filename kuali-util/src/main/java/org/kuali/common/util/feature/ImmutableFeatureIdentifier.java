package org.kuali.common.util.feature;

import org.kuali.common.util.Assert;
import org.kuali.common.util.identifier.AbstractIdentifier;
import org.kuali.common.util.identifier.ImmutableIdentifier;
import org.kuali.common.util.project.ProjectIdentifier;

public final class ImmutableFeatureIdentifier extends AbstractIdentifier implements FeatureIdentifier {

	private final ProjectIdentifier project;
	private final String featureId;

	public ImmutableFeatureIdentifier(ProjectIdentifier project, String featureId) {
		super(new ImmutableIdentifier(project.getIdentifier() + ":" + featureId));

		// Make sure we are being configured correctly
		Assert.notNull(project, "project is null");
		Assert.notBlank(featureId, "featureId is blank");

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

}
