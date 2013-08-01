package org.kuali.common.util.properties;

import java.util.Properties;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.ModeUtils;
import org.kuali.common.util.PropertyUtils;

public class ValidatingLoader implements LocationLoader {

	final String value;

	public ValidatingLoader(String value) {
		super();
		this.value = value;
	}

	@Override
	public Properties load(Location location) {
		if (!LocationUtils.exists(value)) {
			// Take appropriate action for missing locations (ignore, inform, warn, or error out)
			ModeUtils.validate(location.getMissingMode(), "Non-existent location [" + value + "]");
			return PropertyUtils.EMPTY;
		} else {
			return PropertyUtils.load(value, location.getEncoding());
		}
	}

}
