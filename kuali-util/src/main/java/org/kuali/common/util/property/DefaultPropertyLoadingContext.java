package org.kuali.common.util.property;

import java.util.List;

public class DefaultPropertyLoadingContext extends DefaultPropertyHandlingContext implements PropertyLoadContext {

	List<String> locations;
	boolean ignoreMissingLocations;

	@Override
	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	@Override
	public boolean isIgnoreMissingLocations() {
		return ignoreMissingLocations;
	}

	public void setIgnoreMissingLocations(boolean ignoreMissingLocations) {
		this.ignoreMissingLocations = ignoreMissingLocations;
	}

}
