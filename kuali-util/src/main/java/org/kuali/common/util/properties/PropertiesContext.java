package org.kuali.common.util.properties;

import java.util.Arrays;
import java.util.List;

public class PropertiesContext {

	String id;
	List<PropertiesLocation> locations;
	List<PropertiesContext> contexts;

	public PropertiesContext() {
		this(null, (PropertiesLocation) null);
	}

	public PropertiesContext(String id, PropertiesLocation location) {
		this(id, Arrays.asList(location));
	}

	public PropertiesContext(String id, List<PropertiesLocation> locations) {
		this(id, locations, null);
	}

	public PropertiesContext(String id, List<PropertiesLocation> locations, List<PropertiesContext> contexts) {
		super();
		this.id = id;
		this.locations = locations;
		this.contexts = contexts;
	}

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
