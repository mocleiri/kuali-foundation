package org.kuali.common.util.properties;

import java.util.List;

import org.kuali.common.util.project.model.FeatureIdentifier;
import org.kuali.common.util.project.model.ProjectIdentifier;
import org.kuali.common.util.properties.model.ProjectResource;

public interface PropertiesLocationService {

	public List<Location> getLocations(ProjectIdentifier identifier, List<String> filenames);

	public List<Location> getLocations(ProjectIdentifier identifier, String... filenames);

	public Location getLocation(ProjectIdentifier identifier, String filename);

	public Location getLocation(FeatureIdentifier identifier, String filename);

	public List<Location> getLocations(FeatureIdentifier identifier, String... filenames);

	public List<Location> getLocations(FeatureIdentifier identifier, List<String> filenames);

	public Location getLocation(ProjectResource resource);

}
