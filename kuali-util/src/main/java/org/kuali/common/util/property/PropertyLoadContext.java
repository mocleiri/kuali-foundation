package org.kuali.common.util.property;

import java.util.List;

import org.kuali.common.util.property.modifier.PropertyModifier;

public interface PropertyLoadContext extends PropertyContext {

	List<String> getLocations();

	boolean isIgnoreMissingLocations();

	List<PropertyModifier> getLoadModifiers();

	void initializeForLoading();

}