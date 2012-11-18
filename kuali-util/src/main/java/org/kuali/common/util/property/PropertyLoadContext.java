package org.kuali.common.util.property;

import java.util.List;
import java.util.Properties;

import org.springframework.util.PropertyPlaceholderHelper;

public interface PropertyLoadContext extends PropertyContext {

	List<String> getLocations();

	boolean isIgnoreMissingLocations();

	void initialize(Properties properties);

	PropertyPlaceholderHelper getHelper();
}