package org.kuali.common.util.project;

import java.util.Properties;

public interface Project {

	String getGroupId();

	String getArtifactId();

	String getVersion();

	Properties getProperties();

}
