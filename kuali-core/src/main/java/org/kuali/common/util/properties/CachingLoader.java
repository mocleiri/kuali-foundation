package org.kuali.common.util.properties;

import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.cache.Cache;
import org.kuali.common.util.property.ImmutableProperties;

public final class CachingLoader implements PropertiesLoader {

	private final Cache<String, Properties> cache;
	private final LocationLoader loader;

	public CachingLoader(Location location, Cache<String, Properties> cache) {
		this(location, location.getValue(), cache);
	}

	public CachingLoader(Location location, String value, Cache<String, Properties> cache) {
		Assert.noNulls(cache);
		this.cache = cache;
		this.loader = new LocationLoader(location, value);
	}

	@Override
	public Properties load() {

		if (!loader.getLocation().isCacheable()) {
			return loader.load();
		}

		Properties properties = cache.get(loader.getValue());
		if (properties == null) {
			properties = new ImmutableProperties(loader.load());
			this.cache.put(loader.getValue(), properties);
		}
		return properties;

	}

}
