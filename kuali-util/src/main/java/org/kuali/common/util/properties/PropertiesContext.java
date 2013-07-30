package org.kuali.common.util.properties;

import java.util.List;

import org.kuali.common.util.config.Location;

public interface PropertiesContext {

	String getId();

	List<Location> getLocations();

	List<PropertiesContext> getContexts();

}
