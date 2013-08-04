package org.kuali.common.util.feature;

import org.kuali.common.util.Assert;
import org.kuali.common.util.identity.BasicIdentifiable;
import org.kuali.common.util.project.ImmutableProjectIdentifier;
import org.kuali.common.util.project.ProjectIdentifier;

public final class ImmutableFeatureIdentifier extends BasicIdentifiable implements FeatureIdentifier {

	private final ProjectIdentifier project;
	private final String featureId;

	public ImmutableFeatureIdentifier(String groupId, String artifactId, String featureId) {
		this(new ImmutableProjectIdentifier(groupId, artifactId), featureId);
	}

	public ImmutableFeatureIdentifier(ProjectIdentifier project, String featureId) {
		this(new ImmutableProjectIdentifier(project.getGroupId(), project.getArtifactId()), featureId);
	}

	public ImmutableFeatureIdentifier(ImmutableProjectIdentifier project, String featureId) {
		super(project.getIdentity() + ":" + featureId);

		// Make sure we are being configured correctly
		Assert.noBlanks("featureId is blank", featureId);

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
