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

import org.kuali.common.util.Assert;
import org.kuali.common.util.KeyValue;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Call <code>setProperty(key,value)</code> on <code>properties</code>
 */
public class SetPropertiesFactoryBean implements FactoryBean<Properties>, InitializingBean {

	Properties properties;
	List<KeyValue> keyValuePairs;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(properties, "properties is null");
		Assert.notNull(keyValuePairs, "keyValuePairs is null");
		for (KeyValue keyValuePair : keyValuePairs) {
			String key = keyValuePair.getKey();
			String value = keyValuePair.getValue();
			properties.setProperty(key, value);
		}
	}

	@Override
	public Properties getObject() throws Exception {
		return properties;
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public List<KeyValue> getKeyValuePairs() {
		return keyValuePairs;
	}

	public void setKeyValuePairs(List<KeyValue> keyValuePairs) {
		this.keyValuePairs = keyValuePairs;
	}

}
