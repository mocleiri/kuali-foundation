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
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.xml.service.XmlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @deprecated
 */
@Deprecated
public class DefaultConfigService extends AbstractCachingConfigService {

	public DefaultConfigService(ProjectService projectService, XmlService xmlService) {
		super(projectService, xmlService);
	}

	private static final Logger logger = LoggerFactory.getLogger(DefaultConfigService.class);

	private static final Map<String, org.kuali.common.util.config.ProjectConfigContainer> CACHE = new HashMap<String, org.kuali.common.util.config.ProjectConfigContainer>();
	private static final String FILE = "metadata.xml";

	@Override
	protected synchronized org.kuali.common.util.config.ProjectConfigContainer getCachedConfig(String groupId, String artifactId) {
		String cacheKey = groupId + ":" + artifactId;
		org.kuali.common.util.config.ProjectConfigContainer config = CACHE.get(cacheKey);
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
	protected org.kuali.common.util.config.ProjectConfigContainer getProjectConfig(String content, String encoding) {
		InputStream in = null;
		try {
			in = new ByteArrayInputStream(content.getBytes(encoding));
			return getXmlService().getObject(in, org.kuali.common.util.config.ProjectConfigContainer.class);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

}
