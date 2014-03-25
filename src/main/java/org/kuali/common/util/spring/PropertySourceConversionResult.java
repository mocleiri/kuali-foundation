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
package org.kuali.common.util.spring;

import java.util.List;
import java.util.Properties;

import org.springframework.core.env.PropertySource;

public class PropertySourceConversionResult {

	List<Properties> propertiesList;
	List<PropertySource<?>> converted;
	List<PropertySource<?>> skipped;

	public List<Properties> getPropertiesList() {
		return propertiesList;
	}

	public void setPropertiesList(List<Properties> propertiesList) {
		this.propertiesList = propertiesList;
	}

	public List<PropertySource<?>> getConverted() {
		return converted;
	}

	public void setConverted(List<PropertySource<?>> converted) {
		this.converted = converted;
	}

	public List<PropertySource<?>> getSkipped() {
		return skipped;
	}

	public void setSkipped(List<PropertySource<?>> skipped) {
		this.skipped = skipped;
	}
}
