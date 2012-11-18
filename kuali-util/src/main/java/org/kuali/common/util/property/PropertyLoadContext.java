package org.kuali.common.util.property;

import java.util.List;
import java.util.Properties;

public interface PropertyLoadContext extends PropertyContext {

	List<String> getLocations();

	boolean isIgnoreMissingLocations();

	void initialize(Properties properties);

}