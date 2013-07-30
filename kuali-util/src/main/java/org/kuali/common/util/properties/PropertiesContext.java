package org.kuali.common.util.properties;

import java.util.List;

public class PropertiesContext {

	String id;
	List<PropertiesLocation> locations;
	List<PropertiesContext> contexts;

	public List<PropertiesLocation> getLocations() {
		return locations;
	}

	public void setLocations(List<PropertiesLocation> locations) {
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
