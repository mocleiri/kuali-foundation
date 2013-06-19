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
package org.kuali.common.util;

import java.util.Properties;

public class LocationPropertiesContext {

	Properties properties;
	String encoding = "UTF-8";
	String keySuffix = ".list";
	String locationPropertiesSuffix = ".properties";

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getKeySuffix() {
		return keySuffix;
	}

	public void setKeySuffix(String keySuffix) {
		this.keySuffix = keySuffix;
	}

	public String getLocationPropertiesSuffix() {
		return locationPropertiesSuffix;
	}

	public void setLocationPropertiesSuffix(String locationPropertiesSuffix) {
		this.locationPropertiesSuffix = locationPropertiesSuffix;
	}

}
