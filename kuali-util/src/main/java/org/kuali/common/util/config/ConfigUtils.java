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
import org.kuali.common.util.CollectionUtils;
import org.springframework.util.Assert;

public class ConfigUtils {

	protected static final String DELIMITER = ":";

	public static List<ConfigRequest> getRequests(List<String> configIds) {
		List<ConfigRequest> requests = new ArrayList<ConfigRequest>();
		for (String configId : configIds) {
			ConfigRequest request = ConfigUtils.getConfigRequest(configId);
			requests.add(request);
		}
		return requests;
	}

	public static List<String> getConfigIds(List<? extends ConfigRequest> requests) {
		List<String> configIds = new ArrayList<String>();
		for (ConfigRequest request : requests) {
			configIds.add(request.getId());
		}
		return configIds;
	}

	public static List<String> unmodifiableList(List<? extends ConfigRequest> requests) {
		List<String> configIds = getConfigIds(requests);
		return Collections.unmodifiableList(configIds);
	}

	public static String getConfigId(ConfigRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append(StringUtils.trimToEmpty(request.getGroupId()));
		sb.append(":");
		sb.append(StringUtils.trimToEmpty(request.getArtifactId()));
		if (!StringUtils.isBlank(request.getContextId())) {
			sb.append(":");
			sb.append(StringUtils.trimToEmpty(request.getContextId()));
		}
		return sb.toString();
	}

	/**
	 * Return a String containing the tokens passed in separated by <code>:</code>
	 */
	public static String getId(String... tokens) {
		return CollectionUtils.getStringWithSeparator(Arrays.asList(tokens), DELIMITER);
	}

	/**
	 * Convert tokens representing a single configId into an unmodifiable list with one element in it
	 */
	public static List<String> unmodifiableSingleElementList(String... configIdTokens) {
		String configId = getId(configIdTokens);
		return Collections.unmodifiableList(Arrays.asList(configId));
	}

	public static ConfigRequest getConfigRequest(String configId) {

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
		ConfigRequest request = new ConfigRequest();
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
