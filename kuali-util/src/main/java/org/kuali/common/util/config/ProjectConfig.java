package org.kuali.common.util.config;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.CollectionUtils;

@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProjectConfig {

	String groupId;
	String artifactId;
	List<Location> locations = new ArrayList<Location>();
	List<ContextConfig> contexts = new ArrayList<ContextConfig>();

	public ProjectConfig() {
		super();
	}

	public ProjectConfig(ProjectConfig config) {
		super();
		this.groupId = config.getGroupId();
		this.artifactId = config.getArtifactId();
		for (Location location : CollectionUtils.toEmptyList(config.getLocations())) {
			this.locations.add(new Location(location));
		}
		for (ContextConfig context : CollectionUtils.toEmptyList(config.getContexts())) {
			this.contexts.add(new ContextConfig(context));
		}
	}

	@XmlAttribute
	public String getGroupId() {
		return groupId;
	}

	@XmlAttribute
	public String getArtifactId() {
		return artifactId;
	}

	@XmlElement(name = "location")
	public List<Location> getLocations() {
		return locations;
	}

	@XmlElement(name = "context")
	public List<ContextConfig> getContexts() {
		return contexts;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public void setContexts(List<ContextConfig> contexts) {
		this.contexts = contexts;
	}

}
