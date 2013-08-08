package org.kuali.common.util.properties.spring;

import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Feature;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.model.ProjectIdentifier;
import org.kuali.common.util.project.spring.ProjectServiceConfig;
import org.kuali.common.util.properties.Location;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ ProjectServiceConfig.class })
public class PropertyLocationsCommonConfig {

	public static final boolean DEFAULT_CACHE_LOCATION_PROPERTIES_VALUE = true;
	private static final String PROPERTIES_CACHE_KEY = "properties.cache";

	@Autowired
	Environment env;

	@Autowired
	ProjectService projectService;

	public Location getLocation(ProjectIdentifier identifier, String filename) {
		boolean cache = SpringUtils.getBoolean(env, PROPERTIES_CACHE_KEY, DEFAULT_CACHE_LOCATION_PROPERTIES_VALUE);
		Project project = projectService.getProject(identifier);
		String value = ProjectUtils.getClasspathPrefix(identifier) + "/" + filename;
		String encoding = ProjectUtils.getEncoding(project);
		return new Location(value, encoding, cache);
	}

	/**
	 * 
	 */
	public Location getLocation(Feature feature, String filename) {
		boolean cache = SpringUtils.getBoolean(env, PROPERTIES_CACHE_KEY, DEFAULT_CACHE_LOCATION_PROPERTIES_VALUE);
		String groupId = feature.getIdentifier().getGroupId();
		String artifactId = feature.getIdentifier().getArtifactId();
		Project project = projectService.getProject(groupId, artifactId);
		String value = ProjectUtils.getClasspathPrefix(feature.getIdentifier()) + "/" + filename;
		String encoding = ProjectUtils.getEncoding(project);
		return new Location(value, encoding, cache);
	}

}
