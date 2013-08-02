package org.kuali.common.util.properties;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.cache.Cache;
import org.kuali.common.util.cache.SimpleCache;
import org.kuali.common.util.property.processor.OverrideProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.resolver.PropertiesValueResolver;
import org.kuali.common.util.resolver.ValueResolver;
import org.springframework.util.Assert;

public class OverridePropertiesService implements PropertiesService {

	public static final Mode DEFAULT_OVERRIDE_MODE = Mode.INFORM;
	public static final boolean DEFAULT_CACHE_LOADED_PROPERTIES = true;
	private static final int DEFAULT_LOG_MESSAGE_INDENT = 2;
	private static final Cache<String, Properties> CACHE = new SimpleCache<String, Properties>();

	final List<Properties> overrides;
	final Mode overrideMode;
	final int logMessageIndent;
	final boolean cacheLoadedProperties;

	public OverridePropertiesService() {
		this(PropertyUtils.EMPTY);
	}

	public OverridePropertiesService(Properties... overrides) {
		this(Arrays.asList(overrides), DEFAULT_OVERRIDE_MODE);
	}

	public OverridePropertiesService(Properties overrides) {
		this(Arrays.asList(overrides), DEFAULT_OVERRIDE_MODE);
	}

	public OverridePropertiesService(List<Properties> overrides, Mode overrideMode) {
		this(overrides, overrideMode, DEFAULT_LOG_MESSAGE_INDENT);
	}

	public OverridePropertiesService(List<Properties> overrides, Mode overrideMode, int indent) {
		this(overrides, overrideMode, indent, DEFAULT_CACHE_LOADED_PROPERTIES);
	}

	public OverridePropertiesService(List<Properties> overrides, Mode overrideMode, int indent, boolean cacheLoadedProperties) {
		super();
		Assert.notNull(overrides, "overrides cannot be null");
		Assert.notNull(overrideMode, "overrideMode is null");
		this.overrides = overrides;
		this.overrideMode = overrideMode;
		this.logMessageIndent = indent;
		this.cacheLoadedProperties = cacheLoadedProperties;
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

			// Setup a loader capable of correctly handling things
			// It might be perfectly acceptable for the location to not even exist
			// The location might point to the default location for user specified overrides and the user hasn't provided any (for example)
			// The loader is allowed to ignore missing locations, emit a log message about missing locations, or throw an exception
			PropertiesLoader loader = getLoader(location, resolvedLocation, cacheLoadedProperties, CACHE);

			// This may return an empty properties object depending on the configuration of the corresponding Location object
			Properties loaded = loader.load();

			// Override what we've got so far with what we just loaded
			override(properties, loaded);
		}

		// Override the final set of loaded properties with overrides properties
		override(properties, overrides);

		// Decrypt them
		PropertyUtils.decrypt(properties);

		// Resolve them, throwing an exception if any value cannot be fully resolved
		PropertyUtils.resolve(properties);

		// Return what we've found
		return properties;
	}

	protected LocationLoader getLoader(Location location, String resolvedLocation, boolean useCache, Cache<String, Properties> cache) {
		if (useCache) {
			return new CachingLoader(location, resolvedLocation, cache);
		} else {
			return new LocationLoader(location, resolvedLocation);
		}
	}

	protected void override(Properties existing, Properties overrides) {
		override(existing, Arrays.asList(overrides));
	}

	protected void override(Properties existing, List<Properties> overrides) {
		for (Properties properties : overrides) {
			PropertyProcessor processor = new OverrideProcessor(overrideMode, properties, logMessageIndent);
			processor.process(existing);
		}
	}

}
