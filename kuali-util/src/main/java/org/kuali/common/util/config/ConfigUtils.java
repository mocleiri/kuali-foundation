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
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.KualiProjectConstants;
import org.kuali.common.util.Str;
import org.kuali.common.util.project.ImmutableProject;
import org.springframework.util.Assert;

public class ConfigUtils {

	protected static final String DELIMITER = ":";

	public static ProjectConfig getCommonConfigRequest(String artifactId, String contextId) {
		return new DefaultProjectConfig(KualiProjectConstants.COMMON_GROUP_ID, artifactId, contextId);
	}

	public static ProjectConfig getUtilConfigRequest(String contextId) {
		return getCommonConfigRequest(KualiProjectConstants.UTIL_ARTIFACT_ID, contextId);
	}

	public static List<DefaultProjectConfig> getRequests(List<String> configIds) {
		List<DefaultProjectConfig> requests = new ArrayList<DefaultProjectConfig>();
		for (String configId : configIds) {
			DefaultProjectConfig request = ConfigUtils.getConfigRequest(configId);
			requests.add(request);
		}
		return requests;
	}

	public static List<String> getConfigIds(List<? extends DefaultProjectConfig> requests) {
		List<String> configIds = new ArrayList<String>();
		for (ProjectConfig request : requests) {
			configIds.add(request.getConfigId());
		}
		return configIds;
	}

	public static List<String> unmodifiableList(List<? extends DefaultProjectConfig> requests) {
		List<String> configIds = getConfigIds(requests);
		return Collections.unmodifiableList(configIds);
	}

	public static String getConfigId(ProjectConfig request) {
		return getConfigId(request.getGroupId(), request.getArtifactId(), request.getContextId());
	}

	public static String getConfigId(ImmutableProject project, String contextId) {
		return getConfigId(project.getGroupId(), project.getArtifactId(), contextId);
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

	/**
	 * Convert tokens representing a single configId into an unmodifiable list with one element in it
	 */
	public static List<String> unmodifiableSingleElementList(String... configIdTokens) {
		String configId = Str.getId(configIdTokens);
		return Collections.unmodifiableList(Arrays.asList(configId));
	}

	public static DefaultProjectConfig getConfigRequest(String configId) {

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
		DefaultProjectConfig request = new DefaultProjectConfig();
		request.setGroupId(groupId);
		request.setArtifactId(artifactId);
		request.setContextId(contextId);
		return request;
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
