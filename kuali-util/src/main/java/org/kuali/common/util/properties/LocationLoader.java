package org.kuali.common.util.properties;

import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.ModeUtils;
import org.kuali.common.util.PropertyUtils;

public final class LocationLoader implements PropertiesLoader {

	private final String value;
	private final Location location;

	public LocationLoader(Location location) {
		this(location, location.getValue());
	}

	public LocationLoader(Location location, String value) {
		Assert.noNulls(location);
		Assert.noBlanks(value);
		this.value = value;
		this.location = location;
	}

	@Override
	public Properties load() {
		if (!LocationUtils.exists(value)) {
			// Take appropriate action for missing locations (ignore, inform, warn, or error out)
			ModeUtils.validate(location.getMissingMode(), "Non-existent location [" + value + "]");
			return PropertyUtils.EMPTY;
		} else {
			return PropertyUtils.load(value, location.getEncoding(), location.getFormat());
		}
	}

	public String getValue() {
		return value;
	}

	public Location getLocation() {
		return location;
	}

}
