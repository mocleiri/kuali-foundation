/**
 * Copyright 2010-2012 The Kuali Foundation
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

public class DefaultSpringContext implements SpringContext {

	List<String> locations;
	String propertiesBeanName;
	Properties properties;
	boolean injectProperties;

	@Override
	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	@Override
	public String getPropertiesBeanName() {
		return propertiesBeanName;
	}

	public void setPropertiesBeanName(String propertiesBeanName) {
		this.propertiesBeanName = propertiesBeanName;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public boolean isInjectProperties() {
		return injectProperties;
	}

	public void setInjectProperties(boolean injectProperties) {
		this.injectProperties = injectProperties;
	}

}
