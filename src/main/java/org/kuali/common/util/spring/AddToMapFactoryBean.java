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

import java.util.Map;

import org.kuali.common.util.Assert;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Copy all of the mappings from <code>source</code> to <code>target</code>
 */
public class AddToMapFactoryBean<K, V> implements FactoryBean<Map<K, V>>, InitializingBean {

	Map<K, V> source;
	Map<K, V> target;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(source, "source is null");
		Assert.notNull(target, "target is null");
		target.putAll(source);
	}

	@Override
	public Map<K, V> getObject() throws Exception {
		return target;
	}

	@Override
	public Class<?> getObjectType() {
		return Map.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public Map<K, V> getSource() {
		return source;
	}

	public void setSource(Map<K, V> source) {
		this.source = source;
	}

	public Map<K, V> getTarget() {
		return target;
	}

	public void setTarget(Map<K, V> target) {
		this.target = target;
	}

}
