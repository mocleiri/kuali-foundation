package org.kuali.common.util.property;

import java.util.List;

public interface PropertyLoadingContext extends PropertyHandlingContext {

	List<String> getLocations();

	boolean isIgnoreMissingLocations();

}