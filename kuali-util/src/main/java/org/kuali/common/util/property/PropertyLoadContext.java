package org.kuali.common.util.property;

import java.util.List;

import org.kuali.common.util.Mode;
import org.kuali.common.util.property.processor.PropertyProcessor;

public interface PropertyLoadContext extends PropertyContext {

	List<String> getLocations();

	Mode getMissingLocationsMode();

	List<PropertyProcessor> getLoadModifiers();

	void initializeLoadModifiers();

}