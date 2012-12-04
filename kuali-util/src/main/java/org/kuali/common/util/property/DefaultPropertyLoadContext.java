package org.kuali.common.util.property;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.Mode;
import org.kuali.common.util.property.processor.AddEnvPropertiesProcessor;
import org.kuali.common.util.property.processor.AddSystemPropertiesProcessor;
import org.kuali.common.util.property.processor.HomeProcessor;
import org.kuali.common.util.property.processor.OrgProcessor;
import org.kuali.common.util.property.processor.PathProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.property.processor.VersionProcessor;

public class DefaultPropertyLoadContext extends DefaultPropertyContext implements PropertyLoadContext {

	List<String> locations;
	List<String> localLocations;
	Mode missingLocationsMode = Mode.INFORM;
	List<PropertyProcessor> loadProcessors;

	@Override
	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	@Override
	public List<PropertyProcessor> getLoadProcessors() {
		return loadProcessors;
	}

	public void setLoadProcessors(List<PropertyProcessor> loadProcessors) {
		this.loadProcessors = loadProcessors;
	}

	@Override
	public void initializeLoadProcessors() {
		if (loadProcessors == null) {
			loadProcessors = getDefaultLoadProcessors();
		} else {
			loadProcessors.addAll(0, getDefaultProcessors());
		}
	}

	protected List<PropertyProcessor> getDefaultLoadProcessors() {
		List<PropertyProcessor> processors = new ArrayList<PropertyProcessor>();

		processors.add(new AddEnvPropertiesProcessor());
		processors.add(new AddSystemPropertiesProcessor());

		if (pathProperties != null) {
			processors.add(new PathProcessor(pathProperties));
		}

		if (versionProperties != null) {
			processors.add(new VersionProcessor(versionProperties));
		}

		if (orgGroupIdKey != null && projectGroupIdKey != null) {
			processors.add(new OrgProcessor(orgGroupIdKey, projectGroupIdKey));
			processors.add(new HomeProcessor(orgGroupIdKey, projectGroupIdKey, globalPropertiesOverrideMode));
		}

		return processors;
	}

	@Override
	public Mode getMissingLocationsMode() {
		return missingLocationsMode;
	}

	public void setMissingLocationsMode(Mode missingLocationsMode) {
		this.missingLocationsMode = missingLocationsMode;
	}

	public List<String> getLocalLocations() {
		return localLocations;
	}

	public void setLocalLocations(List<String> localLocations) {
		this.localLocations = localLocations;
	}

}
