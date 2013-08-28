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
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 * @deprecated
 */
@Deprecated
public class ConfigUtils {

	public static final String DELIMITER = ":";

	public static List<ProjectConfig> getProjectConfigs(List<String> configIds) {
		List<ProjectConfig> requests = new ArrayList<ProjectConfig>();
		for (String configId : configIds) {
			ProjectConfig request = ConfigUtils.getProjectConfig(configId);
			requests.add(request);
		}
		return requests;
	}

	public static String getConfigId(ProjectConfig config) {
		return getConfigId(config.getGroupId(), config.getArtifactId(), config.getContextId());
	}

	public static String getConfigId(String groupId, String artifactId, String contextId) {
		Assert.hasText(groupId, "groupId is blank");
		Assert.hasText(artifactId, "artifactId is blank");
		StringBuilder sb = new StringBuilder();
		sb.append(StringUtils.trimToEmpty(groupId));
		sb.append(DELIMITER);
		sb.append(StringUtils.trimToEmpty(artifactId));
		if (!StringUtils.isBlank(contextId)) {
			sb.append(DELIMITER);
			sb.append(StringUtils.trimToEmpty(contextId));
		}
		return sb.toString();
	}

	public static ProjectConfig getProjectConfig(String configId) {

		// Split the id up into tokens
		String[] tokens = StringUtils.split(configId, DELIMITER);

		// A config id with less than 2 tokens is invalid
		// In other words, groupId + artifactId are required
		Assert.isTrue(tokens.length > 1, "2 tokens are required");

		// Extract each portion into an explicit variable
		String groupId = tokens[0];
		String artifactId = tokens[1];
		String contextId = getContextId(tokens);

		// Store the variable inside an object
		DefaultProjectConfig config = new DefaultProjectConfig();
		config.setGroupId(groupId);
		config.setArtifactId(artifactId);
		config.setContextId(contextId);
		return config;
	}

	protected static String getContextId(String[] tokens) {
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

}
