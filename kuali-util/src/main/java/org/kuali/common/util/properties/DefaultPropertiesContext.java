package org.kuali.common.util.properties;

import java.util.List;

import org.kuali.common.util.config.Location;

public class DefaultPropertiesContext implements PropertiesContext {

	String id;
	List<Location> locations;
	List<PropertiesContext> contexts;

	@Override
	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	@Override
	public List<PropertiesContext> getContexts() {
		return contexts;
	}

	public void setContexts(List<PropertiesContext> contexts) {
		this.contexts = contexts;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
