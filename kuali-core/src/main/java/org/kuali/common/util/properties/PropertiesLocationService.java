package org.kuali.common.util.properties;

import java.util.List;

import org.kuali.common.util.project.model.ProjectIdentifier;
import org.kuali.common.util.project.model.ProjectResource;

public interface PropertiesLocationService {

	public List<Location> getLocations(ProjectIdentifier identifier, List<String> filenames);

	public List<Location> getLocations(ProjectIdentifier identifier, String... filenames);

	public Location getLocation(ProjectIdentifier identifier, String filename);

	public Location getLocation(ProjectResource resource);

	/**
	 * @deprecated
	 */
	@Deprecated
	public Location getLocation(org.kuali.common.util.project.model.FeatureIdentifier identifier, String filename);

	/**
	 * @deprecated
	 */
	@Deprecated
	public List<Location> getLocations(org.kuali.common.util.project.model.FeatureIdentifier identifier, String... filenames);

	/**
	 * @deprecated
	 */
	@Deprecated
	public List<Location> getLocations(org.kuali.common.util.project.model.FeatureIdentifier identifier, List<String> filenames);

}
