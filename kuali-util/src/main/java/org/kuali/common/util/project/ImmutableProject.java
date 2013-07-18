package org.kuali.common.util.project;

import org.springframework.util.Assert;

public final class ImmutableProject {

	final String groupId;
	final String artifactId;
	final String id;

	public ImmutableProject(String groupId, String artifactId) {
		super();
		Assert.hasText(groupId, "groupId is blank");
		Assert.hasText(artifactId, "artifactId is blank");
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.id = groupId + ":" + artifactId;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return getId();
	}

	public String getGroupId() {
		return groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artifactId == null) ? 0 : artifactId.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImmutableProject other = (ImmutableProject) obj;
		if (artifactId == null) {
			if (other.artifactId != null)
				return false;
		} else if (!artifactId.equals(other.artifactId))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		return true;
	}

}
