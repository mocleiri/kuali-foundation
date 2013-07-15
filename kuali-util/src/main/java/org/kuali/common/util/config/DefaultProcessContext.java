package org.kuali.common.util.config;

import java.util.List;

public class DefaultProcessContext implements ProcessContext {

	String name;
	String groupId;
	String artifactId;
	List<String> configIds;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
	public List<String> getConfigIds() {
		return configIds;
	}

	public void setConfigIds(List<String> configIds) {
		this.configIds = configIds;
	}
}
