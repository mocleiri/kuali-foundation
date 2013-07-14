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
package org.kuali.common.util.feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultFeatureService implements FeatureService {

	protected static final String COMMON_PROPERTIES_FILENAME = "common.properties";
	protected static final String FEATURE_PROPERTIES_FILENAME = "feature.properties";
	protected static final String CLASSPATH_PREFIX = "classpath:";
	protected static final String CONTEXTS_KEY = "project.feature.contexts";
	protected static final String MAGIC_COMMON_VALUE = "COMMON";
	protected static final String MAGIC_DEFAULT_VALUE = "DEFAULT";
	protected static final PropertyPlaceholderHelper PPH = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	protected static final Map<String, Properties> FEATURE_PROPERTIES_CACHE = new HashMap<String, Properties>();

	@Override
	public Feature loadFeature(Feature feature) {
		return loadFeature(feature.getGroupId(), feature.getArtifactId(), feature.getName(), feature.getContextId());
	}

	@Override
	public Feature loadFeature(String id) {
		Assert.hasText(id, "id is blank");
		String[] tokens = StringUtils.split(id, ":");
		Assert.isTrue(tokens.length >= 3, "groupId, artifactId, and name are required");
		String groupId = tokens[0];
		String artifactId = tokens[1];
		String name = tokens[2];
		String contextId = (tokens.length >= 3) ? tokens[3] : null;
		return loadFeature(groupId, artifactId, name, contextId);
	}

	@Override
	public Feature loadFeature(String groupId, String artifactId, String name, String contextId) {
		Assert.notBlank(groupId, artifactId, name, "groupId, artifactId, and name cannot be blank");

		Project project = ProjectUtils.loadProject(groupId, artifactId);
		Properties featureProperties = loadAndCache(project, name);
		Properties enhanced = getEnhanced(project, name, contextId);
		Properties resolved = getResolved(featureProperties, enhanced);

		Feature feature = new Feature();
		feature.setGroupId(groupId);
		feature.setArtifactId(artifactId);
		feature.setName(name);
		feature.setContextId(contextId);
		return feature;
	}

	@Override
	public Feature loadFeature(String groupId, String artifactId, String name) {
		return loadFeature(groupId, artifactId, name, null);
	}

	protected Properties getEnhanced(Project project, String featureName, String contextId) {
		String resourcePath = getResourcePath(project, featureName);
		Properties props = PropertyUtils.duplicate(project.getProperties());
		props.setProperty("feature.name", featureName);
		if (!StringUtils.isBlank(contextId)) {
			props.setProperty("feature.contextId", contextId);
		}
		props.setProperty("feature.classpath.prefix", resourcePath);
		return props;
	}

	protected String getResourcePath(Project project, String featureName) {
		return ProjectUtils.getResourcePath(project) + "/" + featureName;
	}

	protected String getClasspathLocation(Project project, String featureName, String filename) {
		String resourcePath = getResourcePath(project, featureName);
		return CLASSPATH_PREFIX + resourcePath + "/" + filename;
	}

	protected Properties getResolved(Properties properties, Properties resolverProperties) {
		Properties duplicate = PropertyUtils.duplicate(resolverProperties);
		duplicate.putAll(properties);
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String originalValue = properties.getProperty(key);
			String resolvedValue = PPH.replacePlaceholders(originalValue, duplicate);
			properties.setProperty(key, resolvedValue);
		}
		return properties;
	}

	protected List<LocationContext> getLocationContexts(Project project, List<String> locationKeys, Properties properties) {
		List<LocationContext> locationContexts = new ArrayList<LocationContext>();
		for (String locationKey : locationKeys) {
			String modeKey = locationKey + ".mode";
			String encodingKey = locationKey + ".encoding";

			String location = properties.getProperty(locationKey);
			Assert.hasText(location, "[" + locationKey + "] is not set");
			Assert.exists(location);

			String encoding = PropertyUtils.getProperty(properties, encodingKey, project.getEncoding());

			String modeValue = properties.getProperty(modeKey);

			Mode missingLocationMode = LocationContext.DEFAULT_MISSING_MODE;
			if (!StringUtils.isBlank(modeValue)) {
				missingLocationMode = Mode.valueOf(StringUtils.upperCase(modeValue));
			}
			LocationContext locationContext = new LocationContext(location, encoding, missingLocationMode);
			locationContexts.add(locationContext);
		}
		return locationContexts;
	}

	protected synchronized void clearCache() {
		FEATURE_PROPERTIES_CACHE.clear();
	}

	protected synchronized Properties loadAndCache(Project project, String feature) {
		String cacheKey = ProjectUtils.getGav(project) + ":" + feature;
		Properties properties = FEATURE_PROPERTIES_CACHE.get(cacheKey);
		if (properties == null) {
			String location = getClasspathLocation(project, feature, FEATURE_PROPERTIES_FILENAME);
			if (LocationUtils.exists(location)) {
				properties = PropertyUtils.load(location, project.getEncoding());
			} else {
				properties = new Properties();
			}
			FEATURE_PROPERTIES_CACHE.put(cacheKey, properties);
		}
		return properties;
	}
}
