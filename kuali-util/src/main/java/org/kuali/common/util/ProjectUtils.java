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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.property.PropertiesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 * 
 */
public class ProjectUtils {

	private static final Logger logger = LoggerFactory.getLogger(ProjectUtils.class);
	private static final PropertyPlaceholderHelper PPH = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;

	public static final String KUALI_COMMON_GROUP_ID = "org.kuali.common";
	public static final String KUALI_UTIL_ARTIFACT_ID = "kuali-util";

	private static final Map<String, Properties> PROJECT_PROPERTIES_CACHE = new HashMap<String, Properties>();

	/**
	 * 
	 */
	public static ProjectProperties loadProjectProperties(ProjectContext context) {

		// Get a project object based on the context information
		Project project = loadProject(context);

		// Create a properties context object from the project.properties file from META-INF
		PropertiesContext propertiesContext = new PropertiesContext(project.getProperties());
		propertiesContext.setEncoding(project.getEncoding());
		propertiesContext.setLocations(context.getPropertyLocations());

		// Return a project properties object
		return new ProjectProperties(project, propertiesContext);
	}

	/**
	 * Create a <code>Project</code> object from the <code>context</code>. This includes loading the corresponding <code>project.properties</code> file from disk.
	 */
	public static Project loadProject(ProjectContext context) {
		return loadProject(getGav(context));
	}

	public static String getGav(ProjectContext context) {
		return getGav(context.getGroupId(), context.getArtifactId());
	}

	public static String getGav(Project project) {
		return getGav(project.getGroupId(), project.getArtifactId());
	}

	public static String getGav(String groupId, String artifactId) {
		return groupId + ":" + artifactId;
	}

	/**
	 * Create a <code>Project</code> object from the <code>gav</code>. This includes loading the corresponding <code>project.properties</code> file from disk.
	 */
	public static Project loadProject(String gav) {
		// Convert the gav into a Project
		Project project = getProject(gav);

		// Load properties from a .properties file for this project
		Properties properties = loadProperties(project);

		// Return a fully configured project object based on the properties
		return getProject(properties);
	}

	/**
	 * Provide a way to clear the cache
	 */
	public synchronized static void clearProjectPropertiesCache() {
		PROJECT_PROPERTIES_CACHE.clear();
	}

	/**
	 * Create a skeleton <code>Project</code> object from the <code>gav</code>. Nothing but the GAV info gets filled in. Does not read <code>project.properties</code> from disk.
	 */
	public static Project getProject(String gav) {
		logger.debug("Processing [{}]", gav);
		String[] tokens = StringUtils.split(gav, ":");

		Project project = new Project();
		if (tokens.length > 0) {
			project.setGroupId(RepositoryUtils.toNull(tokens[0]));
		}
		if (tokens.length > 1) {
			project.setArtifactId(RepositoryUtils.toNull(tokens[1]));
		}
		if (tokens.length > 2) {
			project.setPackaging(RepositoryUtils.toNull(tokens[2]));
		}
		if (tokens.length > 3) {
			project.setVersion(RepositoryUtils.toNull(tokens[3]));
		}
		if (tokens.length > 4) {
			project.setClassifier(RepositoryUtils.toNull(tokens[4]));
		}
		return project;
	}

	public static List<Dependency> getDependencies(String csv) {
		List<String> tokens = CollectionUtils.getTrimmedListFromCSV(csv);
		List<Dependency> dependencies = new ArrayList<Dependency>();
		for (String token : tokens) {
			Dependency dependency = RepositoryUtils.parseDependency(token);
			dependencies.add(dependency);
		}
		return dependencies;
	}

	/**
	 * Return a <code>Project</code> object by copying values from the <code>properties</code> object into a <code>Project</code> object.
	 */
	public static Project getProject(Properties properties) {
		List<String> skipKeys = Arrays.asList("project.dependencies");
		String startsWith = "project.";
		List<String> keys = PropertyUtils.getStartsWithKeys(properties, startsWith);
		Project project = new Project();
		project.setProperties(properties);
		Map<String, Object> description = ReflectionUtils.describe(project);
		Set<String> beanProperties = description.keySet();
		for (String key : keys) {
			if (skipKeys.contains(key)) {
				continue;
			}
			String value = properties.getProperty(key);
			String beanProperty = getBeanProperty(key, startsWith);
			if (beanProperties.contains(beanProperty)) {
				ReflectionUtils.copyProperty(project, beanProperty, value);
			}
		}
		String csv = RepositoryUtils.toNull(properties.getProperty("project.dependencies"));
		List<Dependency> dependencies = getDependencies(csv);
		project.setDependencies(dependencies);
		return project;
	}

	protected static String getBeanProperty(String key, String startsWith) {
		String s = StringUtils.substring(key, startsWith.length());
		String[] tokens = StringUtils.split(s, ".");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tokens.length; i++) {
			String token = tokens[i];
			if (i == 0) {
				sb.append(token);
			} else {
				sb.append(StringUtils.capitalize(token));
			}
		}
		return sb.toString();
	}

	public static Properties loadProperties(String gav) {
		return loadProperties(getProject(gav));
	}

	public static synchronized Properties loadProperties(Project project) {
		String gav = getGav(project.getGroupId(), project.getArtifactId());
		Properties properties = PROJECT_PROPERTIES_CACHE.get(gav);
		if (properties != null) {
			return properties;
		} else {
			return loadAndCache(project, gav);
		}
	}

	protected static Properties loadAndCache(Project project, String gav) {
		String location = getPropertiesFileLocation(project);
		if (!LocationUtils.exists(location)) {
			throw new IllegalArgumentException("[" + location + "] does not exist");
		}
		Properties properties = PropertyUtils.load(location);
		PROJECT_PROPERTIES_CACHE.put(gav, properties);
		return properties;
	}

	public static String getPropertiesFileLocation(Project project) {
		Assert.hasText(project.getGroupId(), "groupId has no text");
		Assert.hasText(project.getArtifactId(), "artifactId has no text");

		Properties properties = new Properties();
		properties.setProperty("project.groupId.path", Str.getPath(project.getGroupId()));
		properties.setProperty("project.artifactId", project.getArtifactId());

		return PPH.replacePlaceholders(Constants.PROJECT_PROPERTIES_LOCATION, properties);
	}

}
