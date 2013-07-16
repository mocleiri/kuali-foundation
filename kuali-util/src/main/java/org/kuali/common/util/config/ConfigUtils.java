package org.kuali.common.util.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class ConfigUtils {

	protected static final String DELIMITER = ":";

	public static List<String> getConfigIds(List<ConfigRequest> requests) {
		List<String> configIds = new ArrayList<String>();
		for (ConfigRequest request : requests) {
			configIds.add(request.getId());
		}
		return configIds;
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
