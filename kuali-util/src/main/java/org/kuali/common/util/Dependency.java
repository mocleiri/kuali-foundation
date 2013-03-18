package org.kuali.common.util;

/**
 * This is a simple pojo representing a Maven dependency
 */
public class Dependency {

	String groupId;
	String artifactId;
	String version;
	String classifier;
	// Type is usually the same thing as "packaging" from the Artifact object, but not always.
	// For example, "test-jar" is physically packaged into a jar file but is labeled in the Maven
	// dependency list as <type>test-jar</type>
	String type;
	String scope;

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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getClassifier() {
		return classifier;
	}

	public void setClassifier(String classifier) {
		this.classifier = classifier;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}
