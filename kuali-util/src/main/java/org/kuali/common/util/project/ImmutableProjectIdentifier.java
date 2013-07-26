package org.kuali.common.util.project;

public final class ImmutableProjectIdentifier implements ProjectIdentifier {

	private final String groupId;
	private final String artifactId;

	public ImmutableProjectIdentifier(String groupId, String artifactId) {
		this.groupId = groupId;
		this.artifactId = artifactId;
	}

	@Override
	public String getGroupId() {
		return this.groupId;
	}

	@Override
	public String getArtifactId() {
		return this.artifactId;
	}

}
