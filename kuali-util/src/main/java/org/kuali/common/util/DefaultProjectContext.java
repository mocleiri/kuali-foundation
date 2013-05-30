package org.kuali.common.util;

import java.util.Collections;
import java.util.List;

public class DefaultProjectContext implements ProjectContext {

	String groupId = ProjectUtils.KUALI_COMMON_GROUP_ID;
	String artifactId;
	List<String> propertyLocations = Collections.emptyList();

	@Override
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Override
	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	@Override
	public List<String> getPropertyLocations() {
		return propertyLocations;
	}

	public void setPropertyLocations(List<String> propertyLocations) {
		this.propertyLocations = propertyLocations;
	}

}
