package org.kuali.common.util.feature;

import java.util.List;
import java.util.Properties;

public interface Feature {

	String getGroupId();

	String getArtifactId();

	String getFeatureId();

	List<FeatureContext> getContexts();

	Properties getProperties();

}
