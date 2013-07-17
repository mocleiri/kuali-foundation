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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.config.ConfigService;
import org.kuali.common.util.config.DefaultConfigService;
import org.springframework.util.Assert;

public class ConfigPropertiesSupplier implements PropertiesSupplier {

	public static final ConfigService DEFAULT_CONFIG_SERVICE = new DefaultConfigService();

	// Required
	ConfigService service = DEFAULT_CONFIG_SERVICE;

	// Optional
	List<String> configIds;
	List<String> locations;

	public ConfigPropertiesSupplier() {
		this(null, (String) null);
	}

	public ConfigPropertiesSupplier(List<String> configIds, String location) {
		this(configIds, StringUtils.isBlank(location) ? Collections.<String> emptyList() : Arrays.asList(location));
	}

	public ConfigPropertiesSupplier(List<String> configIds, List<String> locations) {
		super();
		this.configIds = configIds;
		this.locations = locations;
	}

	@Override
	public Properties getProperties() {

		// Make sure we are configured correctly
		Assert.notNull(service, "service is null");

		// Load properties from the locations they've provided
		// Returns an empty Properties object if no locations were provided
		Properties overrides = getOverrides(locations);

		// Use the service to load and process properties
		return service.getProperties(CollectionUtils.toEmptyList(configIds), overrides);
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
