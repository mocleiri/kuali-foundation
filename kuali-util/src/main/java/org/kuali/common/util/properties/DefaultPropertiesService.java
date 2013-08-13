package org.kuali.common.util.properties;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.cache.Cache;
import org.kuali.common.util.cache.SimpleCache;
import org.kuali.common.util.property.processor.NoOpProcessor;
import org.kuali.common.util.property.processor.OverrideProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.resolver.PropertiesValueResolver;
import org.kuali.common.util.resolver.ValueResolver;

public final class DefaultPropertiesService implements PropertiesService {

	private static final Cache<String, Properties> CACHE = new SimpleCache<String, Properties>();

	private static final Mode DEFAULT_OVERRIDE_MODE = Mode.INFORM;
	private static int DEFAULT_LOG_MESSAGE_INDENT = 2;
	private static final PropertyProcessor DEFAULT_POST_PROCESSOR = NoOpProcessor.INSTANCE;

	private final Properties overrides;
	private final Mode overrideMode;
	private final int logMessageIndent;
	private final PropertyProcessor postProcessor;

	public DefaultPropertiesService() {
		this(PropertyUtils.EMPTY);
	}

	public DefaultPropertiesService(Properties overrides) {
		this(overrides, DEFAULT_OVERRIDE_MODE);
	}

	public DefaultPropertiesService(Properties overrides, PropertyProcessor postProcessor) {
		this(overrides, DEFAULT_OVERRIDE_MODE, postProcessor);
	}

	public DefaultPropertiesService(Properties overrides, Mode overrideMode) {
		this(overrides, overrideMode, DEFAULT_POST_PROCESSOR, DEFAULT_LOG_MESSAGE_INDENT);
	}

	public DefaultPropertiesService(Properties overrides, Mode overrideMode, PropertyProcessor postProcessor) {
		this(overrides, overrideMode, postProcessor, DEFAULT_LOG_MESSAGE_INDENT);
	}

	public DefaultPropertiesService(Properties overrides, Mode overrideMode, PropertyProcessor postProcessor, int indent) {
		Assert.noNulls(overrides, overrideMode, postProcessor);
		this.overrides = PropertyUtils.toImmutable(overrides);
		this.overrideMode = overrideMode;
		this.logMessageIndent = indent;
		this.postProcessor = postProcessor;
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
			PropertiesLoader loader = new CachingLoader(location, resolvedLocation, CACHE);

			// This may return an empty properties object depending on the configuration of the corresponding Location object
			Properties loaded = loader.load();

			// Override what we've got so far with what we just loaded
			override(properties, loaded);
		}

		// Do any post processing as needed
		postProcessor.process(properties);

		// Return what we've found
		return properties;
	}

	public void clearCache() {
		CACHE.clear();
	}

	protected void override(Properties existing, Properties overrides) {
		PropertyProcessor processor = new OverrideProcessor(overrideMode, overrides, logMessageIndent);
		processor.process(existing);
	}

}
