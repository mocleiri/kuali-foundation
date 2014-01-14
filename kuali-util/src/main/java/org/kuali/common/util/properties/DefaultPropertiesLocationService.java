package org.kuali.common.util.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.FeatureIdentifier;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.model.ProjectIdentifier;
import org.kuali.common.util.properties.model.ProjectResource;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

public class DefaultPropertiesLocationService implements PropertiesLocationService {

	public DefaultPropertiesLocationService(ProjectService projectService) {
		this(projectService, DEFAULT_CACHE_PROPERTIES_VALUE);
	}

	public DefaultPropertiesLocationService(ProjectService projectService, boolean cache) {
		Preconditions.checkNotNull(projectService, "'projectService' cannot be null");
		this.projectService = projectService;
		this.cache = cache;
	}

	private static final boolean DEFAULT_CACHE_PROPERTIES_VALUE = true;

	private final ProjectService projectService;
	private final boolean cache;

	@Override
	public List<Location> getLocations(ProjectIdentifier identifier, List<String> filenames) {
		List<Location> locations = new ArrayList<Location>();
		for (String filename : filenames) {
			locations.add(getLocation(identifier, filename));
		}
		return locations;
	}

	@Override
	public List<Location> getLocations(ProjectIdentifier identifier, String... filenames) {
		return getLocations(identifier, Arrays.asList(filenames));
	}

	@Override
	public Location getLocation(ProjectIdentifier identifier, String filename) {
		Project project = projectService.getProject(identifier);
		String value = ProjectUtils.getClasspathPrefix(identifier) + "/" + filename;
		String encoding = ProjectUtils.getEncoding(project);
		return new Location(value, encoding, cache);
	}

	@Override
	public List<Location> getLocations(FeatureIdentifier identifier, String... filenames) {
		return getLocations(identifier, ImmutableList.copyOf(filenames));
	}

	@Override
	public List<Location> getLocations(FeatureIdentifier identifier, List<String> filenames) {
		List<Location> locations = new ArrayList<Location>();
		for (String filename : filenames) {
			locations.add(getLocation(identifier, filename));
		}
		return locations;
	}

	@Override
	public Location getLocation(FeatureIdentifier identifier, String filename) {
		Project project = projectService.getProject(identifier.getProject());
		String value = ProjectUtils.getClasspathPrefix(identifier) + "/" + filename;
		String encoding = ProjectUtils.getEncoding(project);
		return new Location(value, encoding, cache);
	}

	@Override
	public Location getLocation(ProjectResource resource) {
		return getLocation(resource.getProject(), resource.getFilename());
	}
}
