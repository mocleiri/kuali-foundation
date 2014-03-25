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
package org.kuali.common.util.property;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Encodings;
import org.kuali.common.util.Mode;
import org.springframework.util.PropertyPlaceholderHelper;

public class PropertiesContext {

	protected PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	protected String encoding = Encodings.UTF8;
	protected Mode missingLocationsMode = Mode.ERROR;
	protected Properties properties;
	protected List<String> locations;

	public PropertiesContext() {
		this((Properties) null);
	}

	public PropertiesContext(Properties properties) {
		super();
		this.properties = properties;
	}

	public PropertiesContext(List<String> locations) {
		this(locations, Encodings.UTF8);
	}

	public PropertiesContext(List<String> locations, String encoding) {
		super();
		this.encoding = encoding;
		this.locations = locations;
	}

	public PropertyPlaceholderHelper getHelper() {
		return helper;
	}

	public void setHelper(PropertyPlaceholderHelper helper) {
		this.helper = helper;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public Mode getMissingLocationsMode() {
		return missingLocationsMode;
	}

	public void setMissingLocationsMode(Mode missingLocationsMode) {
		this.missingLocationsMode = missingLocationsMode;
	}
}
