package org.kuali.common.util.property;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.property.modifier.AddEnvPropertiesModifier;
import org.kuali.common.util.property.modifier.AddSystemPropertiesModifier;
import org.kuali.common.util.property.modifier.PathModifier;
import org.kuali.common.util.property.modifier.PropertyModifier;
import org.kuali.common.util.property.modifier.VersionModifier;

public class DefaultPropertyLoadContext extends DefaultPropertyContext implements PropertyLoadContext {

	List<String> locations;
	boolean ignoreMissingLocations;
	List<PropertyModifier> loadModifiers;

	@Override
	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	@Override
	public boolean isIgnoreMissingLocations() {
		return ignoreMissingLocations;
	}

	public void setIgnoreMissingLocations(boolean ignoreMissingLocations) {
		this.ignoreMissingLocations = ignoreMissingLocations;
	}

	@Override
	public List<PropertyModifier> getLoadModifiers() {
		return loadModifiers;
	}

	public void setLoadModifiers(List<PropertyModifier> loadModifiers) {
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

	protected List<PropertyModifier> getDefaultLoadModifiers() {
		List<PropertyModifier> modifiers = new ArrayList<PropertyModifier>();
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

}
