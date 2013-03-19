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

import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.ModeUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.PropertiesLoaderContext;
import org.springframework.beans.factory.FactoryBean;

/**
 * 
 */
public class PropertiesLoaderFactoryBean extends PropertiesLoaderContext implements FactoryBean<Properties> {

	protected Properties global = PropertyUtils.getGlobalProperties();

	@Override
	public Properties getObject() throws Exception {
		Assert.notNull(helper, "helper is null");
		Assert.notNull(locations, "locations are null");
		Assert.notNull(encoding, "encoding is null");
		Assert.notNull(missingLocationsMode, "missingLocationsMode is null");
		this.properties = PropertyUtils.toEmpty(properties);
		Properties returnProperties = new Properties();
		for (String location : locations) {
			Properties resolverProperties = PropertyUtils.combine(properties, returnProperties, global);
			String resolvedLocation = helper.replacePlaceholders(location, resolverProperties);
			if (LocationUtils.exists(resolvedLocation)) {
				Properties properties = PropertyUtils.load(location, encoding);
				returnProperties.putAll(properties);
			} else {
				ModeUtils.validate(missingLocationsMode, "Skipping non-existent location [" + resolvedLocation + "]");
			}
		}
		return returnProperties;
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
}
