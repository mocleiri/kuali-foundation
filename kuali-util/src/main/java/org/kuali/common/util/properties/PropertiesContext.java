package org.kuali.common.util.properties;

import java.util.List;

import org.kuali.common.util.config.Location;

public class PropertiesContext {

	String id;
	List<Location> locations;
	List<PropertiesContext> contexts;

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public List<PropertiesContext> getContexts() {
		return contexts;
	}

	public void setContexts(List<PropertiesContext> contexts) {
		this.contexts = contexts;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
