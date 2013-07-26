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
package org.kuali.common.util.config.supplier;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.config.service.ConfigService;
import org.springframework.util.Assert;

public class ConfigPropertiesSupplier implements PropertiesSupplier {

	List<String> configIds;
	List<String> locations;
	ConfigService service;

	public ConfigPropertiesSupplier() {
		this(null, (String) null);
	}

	public ConfigPropertiesSupplier(List<String> configIds, String location) {
		this(configIds, CollectionUtils.toEmptyList(location));
	}

	public ConfigPropertiesSupplier(List<String> configIds, List<String> locations) {
		this(configIds, locations, null);
	}

	public ConfigPropertiesSupplier(List<String> configIds, List<String> locations, ConfigService service) {
		super();
		this.configIds = configIds;
		this.locations = locations;
		this.service = service;
	}

	@Override
	public Properties getProperties() {

		// Make sure we are configured correctly
		Assert.notNull(service, "service is null");

		// Load properties from the locations they've provided (if any)
		Properties overrides = getOverrides(locations);

		// Load properties for the configIds they've provided (if any)
		return service.getProperties(configIds, overrides);
	}

	protected Properties getOverrides(List<String> locations) {
		Properties overrides = new Properties();
		for (String location : CollectionUtils.toEmptyList(locations)) {
			Properties loaded = PropertyUtils.load(location);
			overrides.putAll(loaded);
		}
		return overrides;
	}

	public ConfigService getService() {
		return service;
	}

	public void setService(ConfigService service) {
		this.service = service;
	}

	public List<String> getConfigIds() {
		return configIds;
	}

	public void setConfigIds(List<String> configIds) {
		this.configIds = configIds;
	}

}
