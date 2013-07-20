package org.kuali.common.util.project;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.Charsets;
import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.property.Constants;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultProjectService implements ProjectService {

	private static final Map<String, FullImmutableProject> PROJECT_CACHE = new HashMap<String, FullImmutableProject>();
	private static final PropertyPlaceholderHelper PPH = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	private static final String PROJECT_PROPERTIES_ENCODING_KEY = "project.properties.encoding";
	private static final String PROJECT_VERSION_KEY = "project.version";
	private static final String PROJECT_PROPERTIES_ENCODING_DEFAULT = Charsets.UTF_8.toString();

	@Override
	public Project loadProject(String groupId, String artifactId) {
		Assert.hasText(groupId, "groupId has no text");
		Assert.hasText(artifactId, "artifactId has no text");

		String key = groupId + ":" + artifactId;
		Project project = PROJECT_CACHE.get(key);
		if (project == null) {
			project = loadAndCache(groupId, artifactId);
		}
		return project;
	}

	protected Project loadAndCache(String groupId, String artifactId) {

		// Get the unique path to the project.properties file
		String location = getPropertiesFileLocation(groupId, artifactId);

		// If that location doesn't exist, we have issues
		Assert.exists(location, "[" + location + "] does not exist");

		// Load system/environment properties
		Properties global = PropertyUtils.getGlobalProperties();

		// Use UTF-8 to load project.properties, unless they've set the system property "project.properties.encoding"
		String encoding = global.getProperty(PROJECT_PROPERTIES_ENCODING_KEY, PROJECT_PROPERTIES_ENCODING_DEFAULT);

		// Load the properties from disk
		Properties properties = PropertyUtils.load(location, encoding);

		// Extract the project version from the properties
		String version = properties.getProperty(PROJECT_VERSION_KEY);

		// Make sure we found a version
		Assert.hasText(version, "no version for [" + groupId + ":" + artifactId + "]");

		// Create a new immutable project
		FullImmutableProject project = new FullImmutableProject(groupId, artifactId, version, properties);

		String key = groupId + ":" + artifactId;
		PROJECT_CACHE.put(key, project);
		return project;
	}

	protected String getPropertiesFileLocation(String groupId, String artifactId) {
		Properties properties = new Properties();
		properties.setProperty(Constants.GROUP_ID_BASE_PATH_KEY, Str.getPath(groupId));
		properties.setProperty(Constants.ARTIFACT_ID_KEY, artifactId);
		return PPH.replacePlaceholders(Constants.PROJECT_PROPERTIES_LOCATION, properties);
	}

}
