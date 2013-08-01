package org.kuali.common.util.properties;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.ModeUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.processor.OverrideProcessor;
import org.springframework.util.Assert;
import org.springframework.util.PropertyPlaceholderHelper;

public class OverridePropertiesService implements PropertiesService {

	final PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}", ":", false);
	final Properties overrides;

	public OverridePropertiesService(Properties overrides) {
		super();
		Assert.notNull(overrides, "overrides cannot be null");
		this.overrides = overrides;
	}

	@Override
	public Properties getProperties(List<Location> locations) {
		// Allocate some storage
		Properties properties = new Properties();
		// Get system/environment properties
		Properties global = PropertyUtils.getGlobalProperties();
		// Cycle through our list of locations
		for (Location location : locations) {
			// Combine properties we've already loaded with overrides and global properties
			Properties resolver = PropertyUtils.combine(properties, overrides, global);
			// Use the combined properties to resolve any placeholders in the location
			String resolvedLocation = helper.replacePlaceholders(location.getValue(), resolver);
			// If the location exists, load it
			if (LocationUtils.exists(resolvedLocation)) {
				Properties loaded = PropertyUtils.load(resolvedLocation, location.getEncoding());
				new OverrideProcessor(Mode.INFORM, loaded, 2).process(properties);
			} else {
				// Take appropriate action for missing locations (ignore, inform, warn, or error out)
				ModeUtils.validate(location.getMissingMode(), "Non-existent location [" + resolvedLocation + "]");
			}
		}
		// Override the loaded properties with overrides properties
		new OverrideProcessor(Mode.INFORM, overrides, 2).process(properties);
		// Override everything with system/environment properties
		new OverrideProcessor(Mode.INFORM, global, 2).process(properties);
		// Decrypt them
		PropertyUtils.decrypt(properties);
		// Resolve them, throwing an exception if any value cannot be fully resolved
		PropertyUtils.resolve(properties);
		// Return what we've found
		return properties;
	}

}
