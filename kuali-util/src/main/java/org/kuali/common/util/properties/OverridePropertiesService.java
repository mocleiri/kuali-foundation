package org.kuali.common.util.properties;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.processor.OverrideProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.resolver.PropertiesValueResolver;
import org.kuali.common.util.resolver.ValueResolver;
import org.springframework.util.Assert;

public class OverridePropertiesService implements PropertiesService {

	public static final Mode DEFAULT_OVERRIDE_MODE = Mode.INFORM;

	final Properties overrides;
	final Mode overrideMode;

	public OverridePropertiesService() {
		this(new Properties());
	}

	public OverridePropertiesService(Properties overrides) {
		this(overrides, DEFAULT_OVERRIDE_MODE);
	}

	public OverridePropertiesService(Properties overrides, Mode overrideMode) {
		super();
		Assert.notNull(overrides, "overrides cannot be null");
		Assert.notNull(overrideMode, "overrideMode is null");
		this.overrides = overrides;
		this.overrideMode = overrideMode;
	}

	protected void override(Properties existing, Properties overrides) {
		PropertyProcessor processor = new OverrideProcessor(overrideMode, overrides, 2);
		processor.process(existing);
	}

	@Override
	public Properties getProperties(List<Location> locations) {

		// Allocate a new properties object
		Properties properties = new Properties();

		// Cycle through our list of locations
		for (Location location : locations) {

			// Override anything we've loaded with properties from overrides
			Properties combined = PropertyUtils.combine(properties, overrides);

			// Use the combined properties to resolve values
			ValueResolver resolver = new PropertiesValueResolver(combined);

			// Resolve the location using the resolver
			String resolvedLocation = resolver.resolve(location.getValue());

			// Setup a loader that capable of correctly validating the resolved location
			// It might be perfectly acceptable for the location to not even exist (eg default user override locations)
			// The loader is allowed to ignore missing locations, emit a log message about missing locations, or throw an exception
			LocationLoader loader = new ValidatingLoader(resolvedLocation);

			// This may return an empty properties object depending on the configuration of the corresponding Location object
			Properties loaded = loader.load(location);

			// Override what we've got so far with what we just loaded
			override(properties, loaded);
		}

		// Override the loaded properties with overrides properties
		override(properties, overrides);

		// Decrypt them
		PropertyUtils.decrypt(properties);

		// Resolve them, throwing an exception if any value cannot be fully resolved
		PropertyUtils.resolve(properties);

		// Return what we've found
		return properties;
	}

}
