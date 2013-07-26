/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @deprecated
 */
@Deprecated
public class DefaultProjectContext implements ProjectContext {

	public static final List<String> DEFAULT_PROPERTY_LOCATIONS = Collections.emptyList();

	@Deprecated
	public static final String DEFAULT_GROUP_ID = org.kuali.common.util.ProjectUtils.KUALI_COMMON_GROUP_ID;

	List<String> propertyLocations = DEFAULT_PROPERTY_LOCATIONS;
	@Deprecated
	String groupId = DEFAULT_GROUP_ID;
	String artifactId;

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

	public DefaultProjectContext(String artifactId, List<String> propertyLocations) {
		this(DEFAULT_GROUP_ID, artifactId, propertyLocations);
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

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public void setPropertyLocations(List<String> propertyLocations) {
		this.propertyLocations = propertyLocations;
	}
}
