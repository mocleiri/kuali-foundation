package org.kuali.common.util.properties;

import java.util.Properties;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.ModeUtils;
import org.kuali.common.util.PropertyUtils;

public class LocationLoader implements PropertiesLoader {

	final String value;
	final Location location;

	public LocationLoader(Location location) {
		this(location, location.getValue());
	}

	public LocationLoader(Location location, String value) {
		super();
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
