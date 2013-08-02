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

import static org.kuali.common.util.properties.LocationConstants.DEFAULT_ENCODING;
import static org.kuali.common.util.properties.LocationConstants.DEFAULT_MISSING_MODE;
import static org.kuali.common.util.properties.LocationConstants.DEFAULT_PROPERTY_FORMAT;

import org.kuali.common.util.Mode;
import org.kuali.common.util.property.PropertyFormat;

public class DefaultLocation implements Location {

	Mode missingMode;
	String encoding;
	PropertyFormat format;
	String value;

	public DefaultLocation() {
		this(null);
	}

	public DefaultLocation(String value) {
		this(value, DEFAULT_ENCODING, DEFAULT_MISSING_MODE, DEFAULT_PROPERTY_FORMAT);
	}

	public DefaultLocation(String value, String encoding) {
		this(value, encoding, DEFAULT_MISSING_MODE, DEFAULT_PROPERTY_FORMAT);
	}

	public DefaultLocation(String value, String encoding, Mode missingMode, PropertyFormat format) {
		super();
		this.missingMode = missingMode;
		this.encoding = encoding;
		this.value = value;
		this.format = format;
	}

	@Override
	public Mode getMissingMode() {
		return missingMode;
	}

	@Override
	public String getEncoding() {
		return encoding;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public PropertyFormat getFormat() {
		return format;
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
