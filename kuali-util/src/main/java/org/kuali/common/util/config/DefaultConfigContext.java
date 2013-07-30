package org.kuali.common.util.config;

import java.util.List;

public class DefaultConfigContext implements ConfigContext {

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

}
