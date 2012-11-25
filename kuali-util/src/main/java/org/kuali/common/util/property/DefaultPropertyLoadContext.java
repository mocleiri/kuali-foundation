package org.kuali.common.util.property;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.Mode;
import org.kuali.common.util.property.modifier.AddEnvPropertiesModifier;
import org.kuali.common.util.property.modifier.AddSystemPropertiesModifier;
import org.kuali.common.util.property.modifier.PathModifier;
import org.kuali.common.util.property.modifier.PropertyProcessor;
import org.kuali.common.util.property.modifier.VersionModifier;

public class DefaultPropertyLoadContext extends DefaultPropertyContext implements PropertyLoadContext {

	List<String> locations;
	Mode missingLocationsMode = Mode.ERROR;
	List<PropertyProcessor> loadModifiers;

	@Override
	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	@Override
	public List<PropertyProcessor> getLoadModifiers() {
		return loadModifiers;
	}

	public void setLoadModifiers(List<PropertyProcessor> loadModifiers) {
		this.loadModifiers = loadModifiers;
	}

	@Override
	public void initializeLoadModifiers() {
		if (loadModifiers == null) {
			loadModifiers = getDefaultLoadModifiers();
		} else {
			loadModifiers.addAll(0, getDefaultModifiers());
		}
	}

	protected List<PropertyProcessor> getDefaultLoadModifiers() {
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
