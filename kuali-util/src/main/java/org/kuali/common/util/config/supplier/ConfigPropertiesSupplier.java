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
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.config.ConfigService;
import org.kuali.common.util.config.DefaultConfigService;
import org.springframework.util.Assert;

public class ConfigPropertiesSupplier implements PropertiesSupplier {

	public static final ConfigService DEFAULT_CONFIG_SERVICE = new DefaultConfigService();

	ConfigService service = DEFAULT_CONFIG_SERVICE;
	List<String> configIds;
	List<String> locations;

	public ConfigPropertiesSupplier() {
		this(null, (String) null);
	}

	public ConfigPropertiesSupplier(List<String> configIds, String location) {
		this(configIds, Arrays.asList(location));
	}

	public ConfigPropertiesSupplier(List<String> configIds, List<String> locations) {
		super();
		this.configIds = configIds;
		this.locations = locations;
	}

	@Override
	public Properties getProperties() {

		Assert.notNull(service, "service is null");
		Assert.notNull(configIds, "configIds is null");

		Properties overrides = getOverrides(locations);
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
