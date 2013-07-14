package org.kuali.common.util.config;

public class ConfigRequest {

	String groupId;
	String artifactId;
	String contextId;

	public ConfigRequest() {
		this(null, null);
	}

	public ConfigRequest(String groupId, String artifactId) {
		this(groupId, artifactId, null);
	}

	public ConfigRequest(String groupId, String artifactId, String contextId) {
		super();
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.contextId = contextId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getContextId() {
		return contextId;
	}

	public void setContextId(String contextId) {
		this.contextId = contextId;
	}

}
