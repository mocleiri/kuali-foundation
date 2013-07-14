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
import org.kuali.common.util.JAXBUtil;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.nullify.Nullifier;
import org.springframework.util.Assert;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultProjectConfigService implements ProjectConfigService {

	protected static final String METAINF = "META-INF";
	protected static final String CLASSPATH = "classpath:";
	protected static final String CLASSPATH_PREFIX_KEY = "classpath.prefix";
	protected static final String CONFIG = "config";
	protected static final String FILE = "metadata.xml";
	protected static final String PROPS = "metadata.properties";
	protected static final Map<String, ProjectConfig> PROJECT_CONFIG_CACHE = new HashMap<String, ProjectConfig>();

	@Override
	public List<Location> getLocations(String groupId, String artifactId) {
		return getLocations(groupId, artifactId, null);
	}

	@Override
	public List<Location> getLocations(String groupId, String artifactId, String contextId) {
		return getLocations(new ConfigRequest(groupId, artifactId, contextId));
	}

	@Override
	public List<Location> getLocations(ConfigRequest request) {
		return getLocations(Arrays.asList(request));
	}

	@Override
	public List<Location> getLocations(List<ConfigRequest> requests) {
		List<Location> locations = new ArrayList<Location>();
		for (ConfigRequest request : requests) {
			List<Location> requestLocations = getRequestLocations(request);
			locations.addAll(requestLocations);
		}
		return locations;
	}

	protected List<Location> getRequestLocations(ConfigRequest request) {
		ProjectConfig config = getCachedConfig(request.getGroupId(), request.getArtifactId());

		List<Location> locations = new ArrayList<Location>();

		return locations;
	}

	protected synchronized ProjectConfig getCachedConfig(String groupId, String artifactId) {
		String cacheKey = groupId + ":" + artifactId;
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

	protected ProjectConfig loadMetadata(String groupId, String artifactId) {
		Project project = ProjectUtils.loadProject(groupId, artifactId);
		String classpathPrefix = ProjectUtils.getClassPathPrefix(project);
		String location = classpathPrefix + "/" + CONFIG + "/" + FILE;
		if (!LocationUtils.exists(location)) {
			return new ProjectConfig(groupId, artifactId);
		} else {
			Properties properties = getFilterProperties(project, classpathPrefix);
			String content = getFilteredContent(location, properties, project.getEncoding());
			return getProjectConfig(content, project.getEncoding());
		}
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

	protected Properties getFilterProperties(Project project, String classpathPrefix) {
		Properties duplicate = PropertyUtils.duplicate(project.getProperties());
		duplicate.setProperty(CLASSPATH_PREFIX_KEY, classpathPrefix);
		String location = classpathPrefix + "/" + CONFIG + "/" + PROPS;
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

		JAXBUtil.writeObject(clone, file);
	}

}
