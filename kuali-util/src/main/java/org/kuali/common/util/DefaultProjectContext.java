package org.kuali.common.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DefaultProjectContext implements ProjectContext {

	public static final List<String> DEFAULT_PROPERTY_LOCATIONS = Collections.emptyList();
	public static final String DEFAULT_GROUP_ID = ProjectUtils.KUALI_COMMON_GROUP_ID;

	String groupId = DEFAULT_GROUP_ID;
	String artifactId;
	List<String> propertyLocations = DEFAULT_PROPERTY_LOCATIONS;

	public DefaultProjectContext() {
		this(null);
	}

	public DefaultProjectContext(String artifactId) {
		this(DEFAULT_GROUP_ID, artifactId, DEFAULT_PROPERTY_LOCATIONS);
	}

	public DefaultProjectContext(String groupId, String artifactId) {
		this(groupId, artifactId, DEFAULT_PROPERTY_LOCATIONS);
	}

	public DefaultProjectContext(String groupId, String artifactId, String propertyLocation) {
		this(groupId, artifactId, Arrays.asList(propertyLocation));
	}

	public DefaultProjectContext(String groupId, String artifactId, List<String> propertyLocations) {
		super();
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.propertyLocations = propertyLocations;
	}

	@Override
	public String getGroupId() {
		return groupId;
	}

	@Override
	public String getArtifactId() {
		return artifactId;
	}

	@Override
	public List<String> getPropertyLocations() {
		return propertyLocations;
	}
}
