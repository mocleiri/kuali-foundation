package org.kuali.common.util.config.properties;

import java.util.Properties;

import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.springframework.util.Assert;

public class ProjectPropertiesSupplier implements PropertiesSupplier {

	String groupId;
	String artifactId;

	@Override
	public Properties getProperties() {

		Assert.hasText(groupId, "groupId has no text");
		Assert.hasText(artifactId, "artifactId has no text");

		Project project = ProjectUtils.loadProject(groupId, artifactId);
		return project.getProperties();
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

}
