package org.kuali.common.util.properties;

import java.util.Properties;

import org.kuali.common.util.cache.Cache;
import org.kuali.common.util.property.ImmutableProperties;

public class CachingLoader extends LocationLoader {

	final Cache<String, Properties> cache;

	public CachingLoader(Location location, Cache<String, Properties> cache) {
		this(location, location.getValue(), cache);
	}

	public CachingLoader(Location location, String value, Cache<String, Properties> cache) {
		super(location, value);
		this.cache = cache;
	}

	@Override
	public Properties load() {
		Properties properties = cache.get(getValue());
		if (properties == null) {
			properties = new ImmutableProperties(super.load());
			this.cache.put(getValue(), properties);
		}
		return properties;
	}

}
