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

import java.nio.charset.Charset;
import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.cache.Cache;
import org.kuali.common.util.cache.SimpleCache;
import org.kuali.common.util.maven.MavenConstants;
import org.kuali.common.util.project.model.ImmutableProject;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.model.ProjectIdentifier;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultProjectService implements ProjectService {

	private static final Cache<String, Project> CACHE = new SimpleCache<String, Project>();
	private static final PropertyPlaceholderHelper PPH = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	private static final String PROPERTIES_ENCODING_KEY = "project.properties.encoding";
	private static final String PROPERTIES_ENCODING_DEFAULT = Charset.defaultCharset().toString();

	private final EnvironmentService env;

	public DefaultProjectService(EnvironmentService env) {
		Assert.noNulls(env);
		this.env = env;
	}

	/**
	 * @deprecated Use ProjectUtils.getProject(properties) instead
	 */
	@Deprecated
	@Override
	public Project getProject(Properties properties) {
		String groupId = properties.getProperty(MavenConstants.GROUP_ID_KEY);
		String artifactId = properties.getProperty(MavenConstants.ARTIFACT_ID_KEY);
		String version = properties.getProperty(MavenConstants.VERSION_KEY);
		return new ImmutableProject(groupId, artifactId, version, properties);
	}

	@Override
	public Project getProject(ProjectIdentifier identifier) {
		return getProject(identifier.getGroupId(), identifier.getArtifactId());
	}

	@Override
	public Project getProject(String groupId, String artifactId) {

		// Both of these are required
		Assert.noBlanks("groupId and artifactId are required", groupId, artifactId);

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

	protected void clearCache() {
		CACHE.clear();
	}

	protected Project load(String groupId, String artifactId) {

		// Get the unique path to the project.properties file
		String location = getPropertiesFileLocation(groupId, artifactId);

		// If that location doesn't exist, we have issues
		Assert.exists(location, "[" + location + "] does not exist");

		// Use platform default encoding to load project.properties
		// Set the system property "project.properties.encoding" or the environment variable "PROJECT_PROPERTIES_ENCODING" to override
		String encoding = env.getString(PROPERTIES_ENCODING_KEY, PROPERTIES_ENCODING_DEFAULT);

		// Load the properties from disk
		Properties properties = PropertyUtils.load(location, encoding);

		// Convert the properties into a project
		return getProject(properties);
	}

	protected String getPropertiesFileLocation(String groupId, String artifactId) {
		Properties properties = new Properties();
		properties.setProperty(Constants.GROUP_ID_PATH_KEY, Str.getPath(groupId));
		properties.setProperty(Constants.ARTIFACT_ID_KEY, artifactId);
		return PPH.replacePlaceholders(Constants.PROJECT_PROPERTIES_LOCATION, properties);
	}

	public EnvironmentService getEnv() {
		return env;
	}

}
