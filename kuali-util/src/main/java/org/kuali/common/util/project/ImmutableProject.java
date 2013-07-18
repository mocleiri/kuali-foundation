package org.kuali.common.util.project;

public final class ImmutableProject {

	final String groupId;
	final String artifactId;

	public ImmutableProject(String groupId, String artifactId) {
		super();
		this.groupId = groupId;
		this.artifactId = artifactId;
	}

	public String getId() {
		return getGroupId() + ":" + getArtifactId();
	}

	public String getGroupId() {
		return groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

}
