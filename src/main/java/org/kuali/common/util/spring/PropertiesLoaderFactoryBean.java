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

import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.ModeUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.PropertiesContext;
import org.springframework.beans.factory.FactoryBean;

/**
 * The improvement over Springs <code>PropertiesFactoryBean</code> is the ability to dynamically resolve placeholders in the property locations themselves.
 */
public class PropertiesLoaderFactoryBean extends PropertiesContext implements FactoryBean<Properties> {

	protected Properties global = PropertyUtils.getGlobalProperties();
	boolean singleton = true;

	@Override
	public Properties getObject() throws Exception {
		Assert.notNull(helper, "helper is null");
		Assert.notNull(locations, "locations are null");
		Assert.notNull(encoding, "encoding is null");
		Assert.notNull(missingLocationsMode, "missingLocationsMode is null");
		Properties global = PropertyUtils.getGlobalProperties();
		this.properties = PropertyUtils.toEmpty(properties);
		Properties result = new Properties();
		for (String location : locations) {
			Properties resolverProperties = PropertyUtils.combine(properties, result, global);
			String resolvedLocation = helper.replacePlaceholders(location, resolverProperties);
			if (LocationUtils.exists(resolvedLocation)) {
				Properties properties = PropertyUtils.load(location, encoding);
				result.putAll(properties);
			} else {
				ModeUtils.validate(missingLocationsMode, "Skipping non-existent location [" + resolvedLocation + "]");
			}
		}
		return result;
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return singleton;
	}

	public void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}
}
