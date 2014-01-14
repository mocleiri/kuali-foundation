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
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Copy properties from <code>source</code> to <code>target</code>. Properties from <code>source</code> override properties from <code>target</code>
 */
public class AddToPropertiesFactoryBean implements FactoryBean<Properties>, InitializingBean {

	Properties source;
	Properties target;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(source, "source is null");
		Assert.notNull(target, "target is null");
		target.putAll(source);
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

	public Properties getSource() {
		return source;
	}

	public void setSource(Properties source) {
		this.source = source;
	}

	public Properties getTarget() {
		return target;
	}

	public void setTarget(Properties target) {
		this.target = target;
	}

}
