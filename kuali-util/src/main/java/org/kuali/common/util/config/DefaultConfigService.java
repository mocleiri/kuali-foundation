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
package org.kuali.common.util.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultConfigService implements ConfigService {

	protected static final String COMMON_PROPERTIES_FILENAME = "common.properties";
	protected static final String METAINF_DIR = "META-INF";
	protected static final String FEATURE_DIR = "feature";
	protected static final String CLASSPATH_PREFIX = "classpath:";
	protected static final String CONTEXTS_KEY = "feature.contexts";
	protected static final PropertyPlaceholderHelper PPH = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	protected static final Map<String, Properties> FEATURE_PROPERTIES_CACHE = new HashMap<String, Properties>();

	@Override
	public String getId(ConfigMetadata feature) {
		StringBuilder sb = new StringBuilder();
		sb.append(feature.getGroupId());
		sb.append(":");
		sb.append(feature.getArtifactId());
		sb.append(":");
		sb.append(feature.getFeatureId());
		if (!StringUtils.isBlank(feature.getContextId())) {
			sb.append(":");
			sb.append(feature.getContextId());
		}
		return sb.toString();
	}

	@Override
	public ConfigMetadata loadMetadata(ConfigMetadata cm) {
		Project project = ProjectUtils.loadProject(cm.getGroupId(), cm.getArtifactId());
		Properties featureProperties = loadAndCache(project, cm.getFeatureId());
		Properties enhanced = getEnhanced(project, cm.getFeatureId(), cm.getContextId());
		Properties resolved = getResolved(featureProperties, enhanced);
		List<LocationContext> locationContexts = getLocationContexts(project, cm.getFeatureId(), cm.getContextId(), resolved);
		cm.setLocationContexts(locationContexts);
		return cm;
	}

	@Override
	public ConfigMetadata loadMetadata(String id) {
		ConfigMetadata cm = getConfigMetadata(id);
		return loadMetadata(cm);
	}

	protected ConfigMetadata getConfigMetadata(String id) {

		Assert.hasText(id, "id is blank");

		String[] tokens = StringUtils.split(id, ":");

		String gid = (tokens.length > 0) ? StringUtils.trimToNull(tokens[0]) : null;
		String aid = (tokens.length > 1) ? StringUtils.trimToNull(tokens[1]) : null;
		String fid = (tokens.length > 2) ? StringUtils.trimToNull(tokens[2]) : null;
		String cid = (tokens.length > 3) ? StringUtils.trimToNull(tokens[3]) : null;

		ConfigMetadata cm = new ConfigMetadata();
		cm.setGroupId(gid);
		cm.setArtifactId(aid);
		cm.setFeatureId(fid);
		cm.setContextId(cid);
		return cm;
	}

	protected List<LocationContext> getLocationContexts(Project project, String featureName, String contextId, Properties properties) {
		// Either there isn't a feature.properties file, or there are no entries in the feature.properties file
		if (StringUtils.isBlank(contextId) || PropertyUtils.isEmpty(properties)) {
			return Arrays.asList(getDefaultLocationContext(project, featureName));
		}

		// If we get here we located a feature.properties file with at least one entry
		String csv = properties.getProperty(CONTEXTS_KEY);
		Assert.hasText(csv);
		List<String> contextIds = CollectionUtils.getTrimmedListFromCSV(csv);
		if (!contextIds.contains(contextId)) {
			throw new IllegalStateException("[" + contextId + "] is an unknown contextId");
		}
		String contextLocationsKey = contextId + ".locations";
		String locationKeysCSV = properties.getProperty(contextLocationsKey);
		if (StringUtils.isBlank(locationKeysCSV)) {
			locationKeysCSV = MagicValue.COMMON + "," + MagicValue.DEFAULT;
		}
		List<String> locationKeys = CollectionUtils.getTrimmedListFromCSV(locationKeysCSV);
		return getLocationContexts(project, featureName, contextId, locationKeys, properties);
	}

	protected List<LocationContext> getLocationContexts(Project project, String featureName, String contextId, List<String> locationKeys, Properties properties) {
		List<LocationContext> locationContexts = new ArrayList<LocationContext>();
		for (String locationKey : locationKeys) {
			MagicValue magicValue = getMagicValue(locationKey);
			if (magicValue != null) {
				LocationContext locationContext = getMagicValueLocationContext(project, magicValue, featureName, contextId);
				locationContexts.add(locationContext);
			} else {
				String modeKey = locationKey + ".mode";
				String encodingKey = locationKey + ".encoding";
				String location = properties.getProperty(locationKey);
				Assert.hasText(location, "[" + locationKey + "] is not set");
				String encoding = PropertyUtils.getProperty(properties, encodingKey, project.getEncoding());
				String modeString = PropertyUtils.getProperty(properties, modeKey, LocationContext.DEFAULT_MISSING_MODE.name());
				Mode missingLocationMode = Mode.valueOf(StringUtils.upperCase(modeString));
				LocationContext locationContext = new LocationContext(location, encoding, missingLocationMode);
				locationContexts.add(locationContext);
			}
		}
		return locationContexts;
	}

	protected MagicValue getMagicValue(String s) {
		MagicValue[] values = MagicValue.values();
		for (MagicValue value : values) {
			if (StringUtils.equals(value.name(), s)) {
				return value;
			}
		}
		return null;
	}

	protected LocationContext getMagicValueLocationContext(Project project, MagicValue value, String featureName, String contextId) {
		switch (value) {
		case COMMON:
			return getDefaultLocationContext(project, featureName);
		case DEFAULT:
			return getDefaultLocationContext(project, featureName, contextId);
		default:
			throw new IllegalArgumentException("[" + value + "] is an unknown magic value");
		}
	}

	protected LocationContext getDefaultLocationContext(Project project, String featureName) {
		String location = getClasspathLocation(project, featureName) + "/" + COMMON_PROPERTIES_FILENAME;
		LocationContext context = new LocationContext();
		context.setEncoding(project.getEncoding());
		context.setLocation(location);
		return context;
	}

	protected LocationContext getDefaultLocationContext(Project project, String featureName, String contextId) {
		String location = getClasspathLocation(project, featureName) + "/" + contextId + ".properties";
		LocationContext context = new LocationContext();
		context.setEncoding(project.getEncoding());
		context.setLocation(location);
		return context;
	}

	protected Properties getEnhanced(Project project, String featureName, String contextId) {
		String resourcePath = getClasspathLocation(project, featureName);
		Properties props = PropertyUtils.duplicate(project.getProperties());
		props.setProperty("feature.name", featureName);
		if (!StringUtils.isBlank(contextId)) {
			props.setProperty("feature.contextId", contextId);
		}
		props.setProperty("feature.classpath.prefix", resourcePath);
		return props;
	}

	protected String getFeatureMetaInfLocation(Project project) {
		return CLASSPATH_PREFIX + METAINF_DIR + "/" + ProjectUtils.getResourcePath(project) + "/config/metadata.properties";
	}

	protected String getFeatureMetaInfLocation(Project project, String featureName) {
		return CLASSPATH_PREFIX + METAINF_DIR + "/" + ProjectUtils.getResourcePath(project) + "/config/" + featureName + "/metadata.properties";
	}

	protected String getClasspathLocation(Project project, String featureName) {
		return CLASSPATH_PREFIX + ProjectUtils.getResourcePath(project) + "/" + featureName;
	}

	protected String getClasspathLocation(Project project, String featureName, String filename) {
		return getClasspathLocation(project, featureName) + "/" + filename;
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

	protected synchronized void clearCache() {
		FEATURE_PROPERTIES_CACHE.clear();
	}

	protected synchronized Properties loadAndCache(Project project, String feature) {
		String cacheKey = ProjectUtils.getGav(project) + ":" + feature;
		Properties properties = FEATURE_PROPERTIES_CACHE.get(cacheKey);
		if (properties == null) {
			String location = getFeatureMetaInfLocation(project, feature);
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
