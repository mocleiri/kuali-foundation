package org.kuali.common.util.properties;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.cache.Cache;
import org.kuali.common.util.cache.SimpleCache;
import org.kuali.common.util.property.processor.NoOpProcessor;
import org.kuali.common.util.property.processor.OverridingProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.resolver.PropertiesValueResolver;
import org.kuali.common.util.resolver.ValueResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DefaultPropertiesService implements PropertiesService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultPropertiesService.class);

	private static final Cache<String, Properties> CACHE = new SimpleCache<String, Properties>();
	private static final PropertyProcessor DEFAULT_POST_PROCESSOR = NoOpProcessor.INSTANCE;

	private final Properties overrides;
	private final PropertyProcessor postProcessor;

	public DefaultPropertiesService(Properties overrides) {
		this(overrides, DEFAULT_POST_PROCESSOR);
	}

	public DefaultPropertiesService(Properties overrides, PropertyProcessor postProcessor) {
		Assert.noNulls(overrides, postProcessor);
		this.overrides = PropertyUtils.toImmutable(overrides);
		this.postProcessor = postProcessor;
	}

	protected PropertiesLoader getLoader(Location location, Cache<String, Properties> cache) {
		return new CachingLoader(location, CACHE);
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

			Location realLocation = location;
			if (!resolvedLocation.equals(location.getValue())) {
				realLocation = new Location(location.getValue(), location.getEncoding(), location.getMissingMode(), location.getFormat(), location.isCacheable());
			}

			// Setup a loader capable of correctly handling things
			// It might be perfectly acceptable for the location to not even exist
			// The location might point to the default location for user specified overrides and the user hasn't provided any (for example)
			// The loader is allowed to ignore missing locations, emit a log message about missing locations, or throw an exception
			PropertiesLoader loader = getLoader(realLocation, CACHE);

			// This may return an empty properties object depending on the configuration of the corresponding Location object
			Properties loaded = loader.load();

			// Any freshly loaded properties "win" over previous properties
			new OverridingProcessor(loaded).process(properties);
		}

		// Do any post processing as needed
		postProcessor.process(properties);

		// This is expensive, only do this in debug mode
		if (logger.isDebugEnabled()) {
			logger.debug("Displaying {} property values:\n\n{}", properties.size(), PropertyUtils.toString(properties));
		}

		// Return what we've found
		return properties;
	}

	public void clearCache() {
		CACHE.clear();
	}

	public Properties getOverrides() {
		return overrides;
	}

	public PropertyProcessor getPostProcessor() {
		return postProcessor;
	}

}
