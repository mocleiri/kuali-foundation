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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.ModeUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.nullify.Nullifier;
import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.processor.OverrideProcessor;
import org.kuali.common.util.xml.service.XmlService;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 * @deprecated
 */
@Deprecated
public abstract class AbstractCachingConfigService implements ConfigService {

	public AbstractCachingConfigService(ProjectService projectService, XmlService xmlService) {
		Assert.noNulls(projectService, xmlService);
		this.projectService = projectService;
		this.xmlService = xmlService;
	}

	private static final String METAINF = "META-INF";
	private static final String CLASSPATH = "classpath:";
	private static final String CLASSPATH_PREFIX_KEY = "classpath.prefix";
	private static final String CONFIG = "config";
	private static final String PROPS = "metadata.properties";
	private static final String DELIMITER = ":";
	private static final PropertyPlaceholderHelper HELPER = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;

	private final ProjectService projectService;
	private final XmlService xmlService;

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
		List<org.kuali.common.util.config.ProjectConfig> requests = org.kuali.common.util.config.ConfigUtils.getProjectConfigs(CollectionUtils.toEmptyList(configIds));
		return loadProperties(requests, PropertyUtils.toEmpty(overrides));
	}

	protected abstract org.kuali.common.util.config.ProjectConfigContainer getCachedConfig(String groupId, String artifactId);

	protected abstract void clearCache();

	protected abstract org.kuali.common.util.config.ProjectConfigContainer getProjectConfig(String content, String encoding);

	protected abstract String getFilename();

	protected Properties loadProperties(List<org.kuali.common.util.config.ProjectConfig> requests, Properties overrides) {
		// Convert the ConfigRequest objects into Location objects
		List<org.kuali.common.util.config.Location> locations = getLocations(requests);
		// Allocate some storage
		Properties properties = new Properties();
		// Get system/environment properties
		Properties global = PropertyUtils.getGlobalProperties();
		// Cycle through our list of locations
		for (org.kuali.common.util.config.Location location : locations) {
			// Combine properties we've already loaded with overrides and global properties
			Properties resolver = PropertyUtils.combine(properties, overrides, global);
			// Use the combined properties to resolve any placeholders in the location
			String resolvedLocation = HELPER.replacePlaceholders(location.getValue(), resolver);
			// If the location exists, load it
			if (LocationUtils.exists(resolvedLocation)) {
				Properties loaded = PropertyUtils.load(resolvedLocation, location.getEncoding());
				new OverrideProcessor(Mode.INFORM, loaded, 2).process(properties);
			} else {
				// Take appropriate action for missing locations (ignore, inform, warn, or error out)
				ModeUtils.validate(location.getMissingMode(), "Non-existent location [" + resolvedLocation + "]");
			}
		}
		// Override the loaded properties with overrides properties
		new OverrideProcessor(Mode.INFORM, overrides, 2).process(properties);
		// Override everything with system/environment properties
		new OverrideProcessor(Mode.INFORM, global, 2).process(properties);
		// Decrypt them
		PropertyUtils.decrypt(properties);
		// Resolve them, throwing an exception if any value cannot be fully resolved
		PropertyUtils.resolve(properties);
		// Return what we've found
		return properties;
	}

	protected Properties getBaseFilterProperties() {
		return new Properties();
	}

	protected org.kuali.common.util.config.ProjectConfigContainer loadMetadata(String groupId, String artifactId) {

		Assert.notNull(projectService, "projectService is null");

		Project project = projectService.getProject(groupId, artifactId);
		String location = getMetadataConfigFilePath(project, getFilename());

		// Throw an exception if they are asking for config metadata that doesn't exist
		Assert.exists(location, "[" + location + "] does not exist");
		Properties properties = getBaseFilterProperties();
		Properties projectProperties = getFilterProperties(project);
		properties.putAll(projectProperties);
		String encoding = ProjectUtils.getEncoding(project);
		String content = getFilteredContent(location, properties, encoding);
		return getProjectConfig(content, encoding);
	}

	protected List<org.kuali.common.util.config.Location> getLocations(List<org.kuali.common.util.config.ProjectConfig> configs) {
		List<org.kuali.common.util.config.Location> locations = new ArrayList<org.kuali.common.util.config.Location>();
		for (org.kuali.common.util.config.ProjectConfig config : configs) {
			List<org.kuali.common.util.config.Location> requestLocations = findLocations(config);
			locations.addAll(requestLocations);
		}
		return locations;
	}

	protected List<org.kuali.common.util.config.Location> findLocations(org.kuali.common.util.config.ProjectConfig request) {
		org.kuali.common.util.config.ProjectConfigContainer config = getCachedConfig(request.getGroupId(), request.getArtifactId());
		if (StringUtils.isBlank(request.getContextId())) {
			return new ArrayList<org.kuali.common.util.config.Location>(CollectionUtils.toEmptyList(config.getLocations()));
		} else {
			String[] tokens = StringUtils.split(request.getContextId(), DELIMITER);
			List<org.kuali.common.util.config.ContextConfig> contexts = config.getContexts();
			org.kuali.common.util.config.ContextConfig context = null;
			for (String token : tokens) {
				context = findContextConfig(contexts, token);
				contexts = context.getContexts();
			}
			return context.getLocations();
		}
	}

	protected org.kuali.common.util.config.ContextConfig findContextConfig(List<org.kuali.common.util.config.ContextConfig> contexts, String contextId) {
		for (org.kuali.common.util.config.ContextConfig context : contexts) {
			if (StringUtils.equals(contextId, context.getId())) {
				return context;
			}
		}
		throw new IllegalArgumentException("Unknown contextId [" + contextId + "]");
	}

	protected String getMetadataConfigFilePath(Project project, String filename) {
		String resourcePath = ProjectUtils.getResourcePath(project.getGroupId(), project.getArtifactId());
		return CLASSPATH + METAINF + "/" + resourcePath + "/" + CONFIG + "/" + filename;
	}

	protected String getFilteredContent(String location, Properties properties, String encoding) {
		PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}", ":", true);
		String originalContent = LocationUtils.toString(location, encoding);
		String filteredContent = helper.replacePlaceholders(originalContent, properties);
		return filteredContent;
	}

	protected Properties getFilterProperties(Project project) {
		String classpathPrefix = ProjectUtils.getClasspathPrefix(project.getGroupId(), project.getArtifactId());
		Properties duplicate = PropertyUtils.duplicate(project.getProperties());
		duplicate.setProperty(CLASSPATH_PREFIX_KEY, classpathPrefix);
		String location = getMetadataConfigFilePath(project, PROPS);
		Properties metadata = new Properties();
		if (LocationUtils.exists(location)) {
			String encoding = ProjectUtils.getEncoding(project);
			metadata = PropertyUtils.load(location, encoding);
		}
		duplicate.putAll(metadata);
		return duplicate;
	}

	protected void store(File file, org.kuali.common.util.config.ProjectConfigContainer config) {

		Assert.notNull(file, "file is null");
		Assert.notNull(config, "config is null");

		org.kuali.common.util.config.ProjectConfigContainer clone = new org.kuali.common.util.config.ProjectConfigContainer(config);

		Nullifier nullifier = new org.kuali.common.util.config.ProjectConfigContainerNullifier();
		nullifier.nullify();

		xmlService.write(file, clone);
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public XmlService getXmlService() {
		return xmlService;
	}

}
