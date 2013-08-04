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

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.config.ProjectConfigContainer;
import org.kuali.common.util.project.KualiUtilProjectConstants;
import org.kuali.common.util.project.Project;
import org.kuali.common.util.project.ProjectIdentifier;
import org.kuali.common.util.project.ProjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

public class SpringConfigService extends AbstractCachingConfigService {

	private static final Logger logger = LoggerFactory.getLogger(SpringConfigService.class);

	private static final Map<String, ProjectConfigContainer> CACHE = new HashMap<String, ProjectConfigContainer>();
	private static final String FILE = "metadata-spring.xml";
	private static final String PROPS = "spring.properties";
	private static final String BEAN = "projectConfig";

	@Override
	protected synchronized ProjectConfigContainer getCachedConfig(String groupId, String artifactId) {
		String cacheKey = groupId + ":" + artifactId;
		ProjectConfigContainer config = CACHE.get(cacheKey);
		if (config == null) {
			config = loadMetadata(groupId, artifactId);
			logger.debug("Caching [{}]", cacheKey);
			CACHE.put(cacheKey, config);
		}
		return config;
	}

	@Override
	protected synchronized void clearCache() {
		CACHE.clear();
	}

	@Override
	protected String getFilename() {
		return FILE;
	}

	@Override
	protected Properties getBaseFilterProperties() {
		ProjectIdentifier identifier = KualiUtilProjectConstants.PROJECT_IDENTIFIER;
		Project project = projectService.getProject(identifier);
		String location = getMetadataConfigFilePath(project, PROPS);
		String encoding = ProjectUtils.getEncoding(project);
		return PropertyUtils.load(location, encoding);
	}

	@Override
	protected ProjectConfigContainer getProjectConfig(String content, String encoding) {
		GenericXmlApplicationContext context = null;
		try {
			Resource resource = new ByteArrayResource(content.getBytes(encoding));
			context = new GenericXmlApplicationContext();
			context.load(resource);
			return (ProjectConfigContainer) context.getBean(BEAN);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		} finally {
			if (context != null) {
				context.close();
			}
		}
	}

}
