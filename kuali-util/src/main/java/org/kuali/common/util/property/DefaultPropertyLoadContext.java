package org.kuali.common.util.property;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.Mode;
import org.kuali.common.util.property.processor.AddEnvPropertiesModifier;
import org.kuali.common.util.property.processor.AddSystemPropertiesModifier;
import org.kuali.common.util.property.processor.PathModifier;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.property.processor.VersionModifier;

public class DefaultPropertyLoadContext extends DefaultPropertyContext implements PropertyLoadContext {

	List<String> locations;
	Mode missingLocationsMode = Mode.ERROR;
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
			loadProcessors.addAll(0, getDefaultModifiers());
		}
	}

	protected List<PropertyProcessor> getDefaultLoadProcessors() {
		List<PropertyProcessor> modifiers = new ArrayList<PropertyProcessor>();
		modifiers.add(new AddEnvPropertiesModifier());
		modifiers.add(new AddSystemPropertiesModifier());
		if (pathProperties != null) {
			modifiers.add(new PathModifier(pathProperties));
		}
		if (versionProperties != null) {
			modifiers.add(new VersionModifier(versionProperties));
		}
		return modifiers;
	}

	@Override
	public Mode getMissingLocationsMode() {
		return missingLocationsMode;
	}

	public void setMissingLocationsMode(Mode missingLocationsMode) {
		this.missingLocationsMode = missingLocationsMode;
	}

}
