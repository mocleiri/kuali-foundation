package org.kuali.common.util.project;

import java.util.List;

public interface ProjectContext {

	String getGroupId();

	String getArtifactId();

	List<String> getPropertyLocations();

}
