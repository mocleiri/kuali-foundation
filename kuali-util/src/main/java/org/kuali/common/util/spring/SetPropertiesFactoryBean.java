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
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.KeyValue;
import org.kuali.common.util.PropertyUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Call <code>setProperty(key,value)</code> on <code>properties</code>
 */
public class SetPropertiesFactoryBean implements FactoryBean<Properties>, InitializingBean {

	Properties target;
	Properties source;
	List<KeyValue> keyValuePairs;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(target, "target is null");
		doKeyValuePairs(CollectionUtils.toEmptyList(keyValuePairs));
		target.putAll(PropertyUtils.toEmpty(source));
	}

	protected void doKeyValuePairs(List<KeyValue> keyValuePairs) {
		for (KeyValue keyValuePair : keyValuePairs) {
			String key = keyValuePair.getKey();
			String value = keyValuePair.getValue();
			Assert.notNull(key, "key is null");
			Assert.notNull(value, "value for [" + key + "] is null");
			target.setProperty(key, value);
		}
	}

	@Override
	public Properties getObject() throws Exception {
		return target;
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public List<KeyValue> getKeyValuePairs() {
		return keyValuePairs;
	}

	public void setKeyValuePairs(List<KeyValue> keyValuePairs) {
		this.keyValuePairs = keyValuePairs;
	}

	public Properties getTarget() {
		return target;
	}

	public void setTarget(Properties target) {
		this.target = target;
	}

	public Properties getSource() {
		return source;
	}

	public void setSource(Properties source) {
		this.source = source;
	}

}
