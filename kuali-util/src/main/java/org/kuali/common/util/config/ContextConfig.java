package org.kuali.common.util.config;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.CollectionUtils;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ContextConfig {

	String name;
	List<Location> locations = new ArrayList<Location>();
	List<ContextConfig> children = new ArrayList<ContextConfig>();

	public ContextConfig(ContextConfig config) {
		super();
		this.name = config.getName();
		for (Location location : CollectionUtils.toEmptyList(config.getLocations())) {
			this.locations.add(new Location(location));
		}
		for (ContextConfig child : CollectionUtils.toEmptyList(config.getChildren())) {
			this.children.add(new ContextConfig(child));
		}
	}

	public ContextConfig() {
		this((String) null);
	}

	public ContextConfig(String name) {
		this(name, null);
	}

	public ContextConfig(String name, List<Location> locations) {
		super();
		this.name = name;
		this.locations = locations;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	@XmlElement(name = "location")
	public List<Location> getLocations() {
		return locations;
	}

	@XmlElement(name = "context")
	public List<ContextConfig> getChildren() {
		return children;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public void setChildren(List<ContextConfig> children) {
		this.children = children;
	}

	public void setName(String name) {
		this.name = name;
	}

}
