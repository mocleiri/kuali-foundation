/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.util.properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.property.PropertyFormat;

public final class Location {

	public static final Mode DEFAULT_MISSING_MODE = Mode.ERROR;
	public static final PropertyFormat DEFAULT_PROPERTY_FORMAT = PropertyFormat.NORMAL;
	public static final String DEFAULT_ENCODING = "UTF-8";

	Mode missingMode;
	String encoding;
	PropertyFormat format;
	String value;

	public Location(Location location) {
		super();
		this.missingMode = location.getMissingMode();
		this.encoding = location.getEncoding();
		this.value = location.getValue();
		this.format = location.getFormat();
	}

	public Location() {
		this((String) null);
	}

	public Location(String value) {
		this(value, DEFAULT_ENCODING, DEFAULT_MISSING_MODE, DEFAULT_PROPERTY_FORMAT);
	}

	public Location(String value, String encoding) {
		this(value, encoding, DEFAULT_MISSING_MODE, DEFAULT_PROPERTY_FORMAT);
	}

	public Location(String value, String encoding, Mode missingMode, PropertyFormat format) {
		super();
		this.missingMode = missingMode;
		this.encoding = encoding;
		this.value = value;
		this.format = format;
	}

	public Mode getMissingMode() {
		return missingMode;
	}

	public String getEncoding() {
		return encoding;
	}

	public String getValue() {
		return value;
	}

	public PropertyFormat getFormat() {
		return format;
	}

	public static PropertyFormat getDefaultPropertyFormat() {
		return DEFAULT_PROPERTY_FORMAT;
	}

	public void setMissingMode(Mode missingMode) {
		this.missingMode = missingMode;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void setFormat(PropertyFormat format) {
		this.format = format;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
