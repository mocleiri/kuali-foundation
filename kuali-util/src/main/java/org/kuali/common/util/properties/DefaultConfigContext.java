package org.kuali.common.util.properties;

import java.util.List;

import org.kuali.common.util.config.Location;

public class DefaultConfigContext implements ConfigContext {

	String id;
	List<Location> locations;
	List<ConfigContext> contexts;

	@Override
	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	@Override
	public List<ConfigContext> getContexts() {
		return contexts;
	}

	public void setContexts(List<ConfigContext> contexts) {
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
