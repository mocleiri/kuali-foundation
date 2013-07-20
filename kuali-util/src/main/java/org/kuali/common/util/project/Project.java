package org.kuali.common.util.project;

import java.util.Properties;

public interface Project {

	String groupId();

	String artifactId();

	Properties getProperties();

}
