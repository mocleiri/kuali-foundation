package org.kuali.common.util.config;

import java.util.List;

public interface ConfigContext {

	List<Location> getLocations();

	List<ConfigContext> getContexts();

}
