package org.kuali.common.util.feature;

import java.util.List;
import java.util.Properties;

public class DefaultFeature implements Feature {

	String groupId;
	String artifactId;
	String featureId;
	List<FeatureContext> contexts;
	Properties properties;

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
	public String getFeatureId() {
		return featureId;
	}

	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}

	@Override
	public List<FeatureContext> getContexts() {
		return contexts;
	}

	public void setContexts(List<FeatureContext> contexts) {
		this.contexts = contexts;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
