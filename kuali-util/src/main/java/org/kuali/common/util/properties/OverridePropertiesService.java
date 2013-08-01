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
		// Allocate some storage
		Properties properties = new Properties();
		// Cycle through our list of locations
		for (Location location : locations) {
			Properties combined = PropertyUtils.combine(properties, overrides);
			ValueResolver resolver = new PropertiesValueResolver(combined);
			String resolvedLocation = resolver.resolve(location.getValue());
			LocationLoader loader = new ValidatingLoader(resolvedLocation);
			Properties loaded = loader.load(location);
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
