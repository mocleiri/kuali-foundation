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
package org.kuali.common.util.config.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.JAXBUtil;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.ModeUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.config.ConfigUtils;
import org.kuali.common.util.config.ContextConfig;
import org.kuali.common.util.config.Location;
import org.kuali.common.util.config.ProjectConfig;
import org.kuali.common.util.config.ProjectConfigContainer;
import org.kuali.common.util.config.ProjectConfigContainerNullifier;
import org.kuali.common.util.nullify.Nullifier;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

public class SpringConfigService implements ConfigService {

	private static final Logger logger = LoggerFactory.getLogger(SpringConfigService.class);

	protected static final String METAINF = "META-INF";
	protected static final String CLASSPATH = "classpath:";
	protected static final String CLASSPATH_PREFIX_KEY = "classpath.prefix";
	protected static final String CONFIG = "config";
	protected static final String FILE = "metadata.xml";
	protected static final String PROPS = "metadata.properties";
	protected static final String DELIMITER = ConfigUtils.DELIMITER;
	protected static final Map<String, ProjectConfigContainer> PROJECT_CONFIG_CACHE = new HashMap<String, ProjectConfigContainer>();
	protected static final PropertyPlaceholderHelper HELPER = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;

	@Override
	public Properties getProperties(String configId) {
		return getProperties(configId, (Properties) null);
	}

	@Override
	public Properties getProperties(List<String> configIds) {
		return getProperties(configIds, null);
	}

	@Override
	public Properties getProperties(String configId, Properties overrides) {
		return getProperties(CollectionUtils.toEmptyList(configId), overrides);
	}

	@Override
	public Properties getProperties(List<String> configIds, Properties overrides) {
		List<ProjectConfig> requests = ConfigUtils.getProjectConfigs(CollectionUtils.toEmptyList(configIds));
		return getPropertiesFromRequests(PropertyUtils.toEmpty(overrides), requests);
	}

	protected Properties getPropertiesFromRequests(Properties overrides, List<ProjectConfig> requests) {
		// Convert the ConfigRequest objects into Location objects
		List<Location> locations = getLocations(requests);
		// Allocate some storage
		Properties properties = new Properties();
		// Get system/environment properties
		Properties global = PropertyUtils.getGlobalProperties();
		// Cycle through our list of locations
		for (Location location : locations) {
			// Combine properties we've already loaded with overrides and global properties
			Properties resolver = PropertyUtils.combine(properties, overrides, global);
			// Use the combined properties to resolve any placeholders in the location
			String resolvedLocation = HELPER.replacePlaceholders(location.getValue(), resolver);
			// If the location exists, load it
			if (LocationUtils.exists(resolvedLocation)) {
				Properties loaded = PropertyUtils.load(resolvedLocation, location.getEncoding());
				properties.putAll(loaded);
			} else {
				// Take appropriate action for missing locations (ignore, inform, warn, or error out)
				ModeUtils.validate(location.getMissingMode(), "Non-existent location [" + resolvedLocation + "]");
			}
		}
		// Override the loaded properties with overrides properties
		properties.putAll(overrides);
		// Override everything with system/environment properties
		properties.putAll(global);
		// Decrypt them
		PropertyUtils.decrypt(properties);
		// Resolve them, throw an exception if any values cannot be fully resolved
		PropertyUtils.resolve(properties);
		// Return what we've found
		return properties;
	}

	protected List<Location> getLocations(List<ProjectConfig> requests) {
		List<Location> locations = new ArrayList<Location>();
		for (ProjectConfig request : requests) {
			List<Location> requestLocations = findLocations(request);
			locations.addAll(requestLocations);
		}
		return locations;
	}

	protected List<Location> findLocations(ProjectConfig request) {
		ProjectConfigContainer config = getCachedConfig(request.getGroupId(), request.getArtifactId());
		if (StringUtils.isBlank(request.getContextId())) {
			return new ArrayList<Location>(CollectionUtils.toEmptyList(config.getLocations()));
		} else {
			String[] tokens = StringUtils.split(request.getContextId(), DELIMITER);
			List<ContextConfig> contexts = config.getContexts();
			ContextConfig context = null;
			for (String token : tokens) {
				context = findContextConfig(contexts, token);
				contexts = context.getChildren();
			}
			return context.getLocations();
		}
	}

	protected ContextConfig findContextConfig(List<ContextConfig> contexts, String contextId) {
		for (ContextConfig context : contexts) {
			if (StringUtils.equals(contextId, context.getId())) {
				return context;
			}
		}
		throw new IllegalArgumentException("Unknown contextId [" + contextId + "]");
	}

	protected synchronized ProjectConfigContainer getCachedConfig(String groupId, String artifactId) {
		String cacheKey = groupId + DELIMITER + artifactId;
		ProjectConfigContainer config = PROJECT_CONFIG_CACHE.get(cacheKey);
		if (config == null) {
			config = loadMetadata(groupId, artifactId);
			logger.debug("Caching [{}]", cacheKey);
			PROJECT_CONFIG_CACHE.put(cacheKey, config);
		}
		return config;
	}

	protected synchronized void clearCache() {
		PROJECT_CONFIG_CACHE.clear();
	}

	protected String getMetadataConfigFilePath(Project project, String filename) {
		String resourcePath = ProjectUtils.getResourcePath(project);
		return CLASSPATH + METAINF + "/" + resourcePath + "/" + CONFIG + "/" + filename;
	}

	protected ProjectConfigContainer loadMetadata(String groupId, String artifactId) {
		Project project = ProjectUtils.loadProject(groupId, artifactId);
		String location = getMetadataConfigFilePath(project, FILE);

		// Throw an exception if they are asking for config metadata that doesn't exist
		Assert.exists(location, "[" + location + "] does not exist");

		Properties properties = getFilterProperties(project);
		String content = getFilteredContent(location, properties, project.getEncoding());
		return getProjectConfig(content, project.getEncoding());
	}

	protected ProjectConfigContainer getProjectConfig(String content, String encoding) {
		InputStream in = null;
		try {
			in = new ByteArrayInputStream(content.getBytes(encoding));
			return JAXBUtil.getObject(in, ProjectConfigContainer.class);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	protected String getFilteredContent(String location, Properties properties, String encoding) {
		PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}", ":", true);
		String originalContent = LocationUtils.toString(location, encoding);
		String filteredContent = helper.replacePlaceholders(originalContent, properties);
		return filteredContent;
	}

	protected Properties getFilterProperties(Project project) {
		String classpathPrefix = ProjectUtils.getClassPathPrefix(project);
		Properties duplicate = PropertyUtils.duplicate(project.getProperties());
		duplicate.setProperty(CLASSPATH_PREFIX_KEY, classpathPrefix);
		String location = getMetadataConfigFilePath(project, PROPS);
		Properties metadata = new Properties();
		if (LocationUtils.exists(location)) {
			metadata = PropertyUtils.load(location, project.getEncoding());
		}
		duplicate.putAll(metadata);
		return duplicate;
	}

	protected void store(File file, ProjectConfigContainer config) {

		Assert.notNull(file, "file is null");
		Assert.notNull(config, "config is null");

		ProjectConfigContainer clone = new ProjectConfigContainer(config);

		Nullifier nullifier = new ProjectConfigContainerNullifier(clone);
		nullifier.nullify();

		JAXBUtil.write(clone, file);
	}

}
