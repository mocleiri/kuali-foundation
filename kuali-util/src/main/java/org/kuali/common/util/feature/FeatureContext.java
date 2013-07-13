package org.kuali.common.util.feature;

import java.util.List;

public class FeatureContext {

	String name;
	List<LocationContext> locationContexts;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<LocationContext> getLocationContexts() {
		return locationContexts;
	}

	public void setLocationContexts(List<LocationContext> locationContexts) {
		this.locationContexts = locationContexts;
	}

}
