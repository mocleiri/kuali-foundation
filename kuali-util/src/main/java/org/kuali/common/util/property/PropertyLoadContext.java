package org.kuali.common.util.property;

import java.util.List;

public interface PropertyLoadContext extends PropertyContext {

	List<String> getLocations();

	boolean isIgnoreMissingLocations();

}