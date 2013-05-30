package org.kuali.common.util;

import java.util.List;

public interface ProjectContext {

	String getGroupId();

	String getArtifactId();

	List<String> getPropertyLocations();

}
