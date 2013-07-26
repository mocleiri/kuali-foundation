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
package org.kuali.common.util.project;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.Charsets;
import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.maven.MavenConstants;
import org.kuali.common.util.property.Constants;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultProjectService implements ProjectService {

	private static final Map<String, Project> CACHE = new HashMap<String, Project>();
	private static final PropertyPlaceholderHelper PPH = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	private static final String PROPERTIES_ENCODING_KEY = "project.properties.encoding";
	private static final String PROPERTIES_ENCODING_DEFAULT = Charsets.UTF_8.toString();

	@Override
	public Project getProject(Properties properties) {
		String groupId = properties.getProperty(MavenConstants.GROUP_ID_KEY);
		String artifactId = properties.getProperty(MavenConstants.ARTIFACT_ID_KEY);
		String version = properties.getProperty(MavenConstants.VERSION_KEY);
		return new ImmutableProject(groupId, artifactId, version, properties);

	}

	@Override
	public synchronized Project getProject(String groupId, String artifactId) {

		// Both of these are required
		Assert.notBlank(groupId, artifactId, "groupId and artifactId are required");

		// Construct the cache key
		String cacheKey = groupId + ":" + artifactId;

		// Check the cache
		Project project = CACHE.get(cacheKey);
		if (project == null) {
			// Construct a project object from project.properties
			project = load(groupId, artifactId);
			// Cache it
			CACHE.put(cacheKey, project);
		}
		return project;
	}

	protected Project load(String groupId, String artifactId) {

		// Get the unique path to the project.properties file
		String location = getPropertiesFileLocation(groupId, artifactId);

		// If that location doesn't exist, we have issues
		Assert.exists(location, "[" + location + "] does not exist");

		// Load system/environment properties
		Properties global = PropertyUtils.getGlobalProperties();

		// Use UTF-8 to load project.properties, unless they've set the system property "project.properties.encoding"
		String encoding = global.getProperty(PROPERTIES_ENCODING_KEY, PROPERTIES_ENCODING_DEFAULT);

		// Load the properties from disk
		Properties properties = PropertyUtils.load(location, encoding);

		// Convert the properties into a project
		return getProject(properties);
	}

	protected String getPropertiesFileLocation(String groupId, String artifactId) {
		Properties properties = new Properties();
		properties.setProperty(Constants.GROUP_ID_BASE_PATH_KEY, Str.getPath(groupId));
		properties.setProperty(Constants.ARTIFACT_ID_KEY, artifactId);
		return PPH.replacePlaceholders(Constants.PROJECT_PROPERTIES_LOCATION, properties);
	}

}
