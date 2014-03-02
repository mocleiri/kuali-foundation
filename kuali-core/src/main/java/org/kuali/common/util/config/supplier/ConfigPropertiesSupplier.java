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

import org.springframework.util.Assert;

/**
 * @deprecated
 */
@Deprecated
public class ConfigPropertiesSupplier implements PropertiesSupplier {

	org.kuali.common.util.config.service.ConfigService service;
	List<String> configIds;
	Properties overrides;

	public ConfigPropertiesSupplier() {
		this(null, null);
	}

	public ConfigPropertiesSupplier(List<String> configIds, org.kuali.common.util.config.service.ConfigService service) {
		this(configIds, service, null);
	}

	public ConfigPropertiesSupplier(List<String> configIds, org.kuali.common.util.config.service.ConfigService service, Properties overrides) {
		super();
		this.configIds = configIds;
		this.service = service;
		this.overrides = overrides;
	}

	@Override
	public Properties getProperties() {

		// Make sure we are configured correctly
		Assert.notNull(service, "service is null");

		// Load properties for the configIds they've provided (if any)
		return service.getProperties(configIds, overrides);
	}

	public org.kuali.common.util.config.service.ConfigService getService() {
		return service;
	}

	public void setService(org.kuali.common.util.config.service.ConfigService service) {
		this.service = service;
	}

	public List<String> getConfigIds() {
		return configIds;
	}

	public void setConfigIds(List<String> configIds) {
		this.configIds = configIds;
	}

	public Properties getOverrides() {
		return overrides;
	}

	public void setOverrides(Properties overrides) {
		this.overrides = overrides;
	}

}
