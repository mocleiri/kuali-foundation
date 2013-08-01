package org.kuali.common.util.properties;

import java.util.List;

import org.kuali.common.util.property.processor.PropertyProcessor;

public class PropertiesContext {

	List<Location> locations;
	LocationResolver resolver;
	PropertyProcessor processor;

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public LocationResolver getResolver() {
		return resolver;
	}

	public void setResolver(LocationResolver resolver) {
		this.resolver = resolver;
	}

	public PropertyProcessor getProcessor() {
		return processor;
	}

	public void setProcessor(PropertyProcessor processor) {
		this.processor = processor;
	}

}
