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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.kuali.common.util.nullify.Nullifier;
import org.kuali.common.util.property.Constants;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultProjectConfigService implements ProjectConfigService {

	protected static final String METAINF = "META-INF";
	protected static final String CLASSPATH = "classpath:";
	protected static final String CLASSPATH_PREFIX_KEY = "classpath.prefix";
	protected static final String CONFIG = "config";
	protected static final String FILE = "metadata.xml";
	protected static final String PROPS = "metadata.properties";
	protected static final Map<String, ProjectConfig> PROJECT_CONFIG_CACHE = new HashMap<String, ProjectConfig>();
	protected static final String DELIMITER = ":";
	private static final PropertyPlaceholderHelper HELPER = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;

	@Override
	public Properties getProperties(Properties project, String configId) {
		return getProperties(project, Arrays.asList(configId));
	}

	@Override
	public Properties getProperties(Properties project, List<String> configIds) {
		List<ConfigRequest> requests = getRequests(configIds);
		return getPropertiesFromRequests(project, requests);
	}

	protected Properties getPropertiesFromRequests(Properties project, List<ConfigRequest> requests) {
		// Convert the ConfigRequest objects into Location objects
		List<Location> locations = getLocations(requests);
		// Allocate some storage
		Properties properties = new Properties();
		// Get system/environment properties
		Properties global = PropertyUtils.getGlobalProperties();
		// Cycle through our list of locations
		for (Location location : locations) {
			// Combine properties we've already loaded with project and global properties
			Properties resolver = PropertyUtils.combine(properties, project, global);
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
		// Override the loaded properties with project properties
		properties.putAll(project);
		// Override everything with system/environment properties
		properties.putAll(global);
		// Decrypt them
		PropertyUtils.decrypt(properties);
		// Resolve them, throw an exception if any values cannot be fully resolved
		PropertyUtils.resolve(properties);
		// Return what we've found
		return properties;
	}

	protected List<ConfigRequest> getRequests(List<String> configIds) {
		List<ConfigRequest> requests = new ArrayList<ConfigRequest>();
		for (String configId : configIds) {
			ConfigRequest request = getConfigRequest(configId);
			requests.add(request);
		}
		return requests;
	}

	protected List<Location> getLocations(String configId) {
		ConfigRequest request = getConfigRequest(configId);
		return getLocations(request.getGroupId(), request.getArtifactId(), request.getContextId());
	}

	protected ConfigRequest getConfigRequest(String configId) {

		String[] tokens = StringUtils.split(configId, DELIMITER);
		Assert.isTrue(tokens.length > 1, "2 tokens are required");

		String groupId = tokens[0];
		String artifactId = tokens[1];
		String contextId = getContextId(tokens);

		ConfigRequest request = new ConfigRequest();
		request.setGroupId(groupId);
		request.setArtifactId(artifactId);
		request.setContextId(contextId);
		return request;
	}

	protected String getContextId(String[] tokens) {
		if (tokens.length < 3) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i < tokens.length; i++) {
			if (i != 2) {
				sb.append(DELIMITER);
			}
			sb.append(tokens[i]);
		}
		return sb.toString();
	}

	protected List<Location> getLocations(String groupId, String artifactId) {
		return getLocations(groupId, artifactId, null);
	}

	protected List<Location> getLocations(String groupId, String artifactId, String contextId) {
		return getLocations(new ConfigRequest(groupId, artifactId, contextId));
	}

	protected List<Location> getLocations(ConfigRequest request) {
		return getLocations(Arrays.asList(request));
	}

	protected List<Location> getLocations(List<ConfigRequest> requests) {
		List<Location> locations = new ArrayList<Location>();
		for (ConfigRequest request : requests) {
			List<Location> requestLocations = findLocations(request);
			locations.addAll(requestLocations);
		}
		return locations;
	}

	protected List<Location> findLocations(ConfigRequest request) {
		ProjectConfig config = getCachedConfig(request.getGroupId(), request.getArtifactId());
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

	protected synchronized ProjectConfig getCachedConfig(String groupId, String artifactId) {
		String cacheKey = groupId + DELIMITER + artifactId;
		ProjectConfig config = PROJECT_CONFIG_CACHE.get(cacheKey);
		if (config == null) {
			config = loadMetadata(groupId, artifactId);
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

	protected ProjectConfig loadMetadata(String groupId, String artifactId) {
		Project project = ProjectUtils.loadProject(groupId, artifactId);
		String location = getMetadataConfigFilePath(project, FILE);

		// Throw an exception if they are asking for config metadata that doesn't exist
		Assert.exists(location, "[" + location + "] does not exist");

		Properties properties = getFilterProperties(project);
		String content = getFilteredContent(location, properties, project.getEncoding());
		return getProjectConfig(content, project.getEncoding());
	}

	protected ProjectConfig getProjectConfig(String content, String encoding) {
		InputStream in = null;
		try {
			in = new ByteArrayInputStream(content.getBytes(encoding));
			return JAXBUtil.getObject(in, ProjectConfig.class);
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

	protected void store(File file, ProjectConfig config) {

		Assert.notNull(file, "file is null");
		Assert.notNull(config, "config is null");

		ProjectConfig clone = new ProjectConfig(config);

		Nullifier nullifier = new ProjectConfigNullifier(clone);
		nullifier.nullify();

		JAXBUtil.write(clone, file);
	}

}
