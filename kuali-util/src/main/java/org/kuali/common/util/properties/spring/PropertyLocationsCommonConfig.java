package org.kuali.common.util.properties.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.FeatureIdentifier;
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

	private static final boolean DEFAULT_CACHE_PROPERTIES_VALUE = true;
	private static final String PROPERTIES_CACHE_KEY = "properties.cache";

	@Autowired
	Environment env;

	@Autowired
	ProjectService projectService;

	public List<Location> getLocations(ProjectIdentifier identifier, List<String> filenames) {
		List<Location> locations = new ArrayList<Location>();
		for (String filename : filenames) {
			locations.add(getLocation(identifier, filename));
		}
		return locations;
	}

	public List<Location> getLocations(ProjectIdentifier identifier, String... filenames) {
		return getLocations(identifier, Arrays.asList(filenames));
	}

	public Location getLocation(ProjectIdentifier identifier, String filename) {
		boolean cache = SpringUtils.getBoolean(env, PROPERTIES_CACHE_KEY, DEFAULT_CACHE_PROPERTIES_VALUE);
		Project project = projectService.getProject(identifier);
		String value = ProjectUtils.getClasspathPrefix(identifier) + "/" + filename;
		String encoding = ProjectUtils.getEncoding(project);
		return new Location(value, encoding, cache);
	}

	public Location getLocation(FeatureIdentifier identifier, String filename) {
		boolean cache = SpringUtils.getBoolean(env, PROPERTIES_CACHE_KEY, DEFAULT_CACHE_PROPERTIES_VALUE);
		Project project = projectService.getProject(identifier.getGroupId(), identifier.getArtifactId());
		String value = ProjectUtils.getClasspathPrefix(identifier) + "/" + filename;
		String encoding = ProjectUtils.getEncoding(project);
		return new Location(value, encoding, cache);
	}

}
