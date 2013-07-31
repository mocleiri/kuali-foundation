package org.kuali.common.util.properties.model;

import java.util.List;

public class Context {

	String id;
	List<Location> locations;
	List<Context> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public List<Context> getChildren() {
		return children;
	}

	public void setChildren(List<Context> children) {
		this.children = children;
	}

}
