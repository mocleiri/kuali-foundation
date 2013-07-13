package org.kuali.common.util.feature;

import org.kuali.common.util.Mode;

public class LocationContext {

	public static final Mode DEFAULT_MISSING_MODE = Mode.ERROR;
	public static final String DEFAULT_ENCODING = "UTF-8";

	Mode missingMode = DEFAULT_MISSING_MODE;
	String encoding = DEFAULT_ENCODING;
	String location;

	public LocationContext() {
		this(null);
	}

	public LocationContext(String location) {
		this(location, DEFAULT_ENCODING, DEFAULT_MISSING_MODE);
	}

	public LocationContext(String location, String encoding, Mode missingMode) {
		super();
		this.missingMode = missingMode;
		this.encoding = encoding;
		this.location = location;
	}

	public Mode getMissingMode() {
		return missingMode;
	}

	public void setMissingMode(Mode missingMode) {
		this.missingMode = missingMode;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
