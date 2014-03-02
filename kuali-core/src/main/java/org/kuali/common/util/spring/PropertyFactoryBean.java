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

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.service.DefaultPropertyService;
import org.kuali.common.util.service.PropertyService;
import org.springframework.beans.factory.FactoryBean;

/**
 * @deprecated
 */
@Deprecated
public class PropertyFactoryBean extends org.kuali.common.util.property.DefaultPropertyLoadContext implements FactoryBean<Properties> {

	protected static Properties instance;
	boolean singleton = false;
	PropertyService service = new DefaultPropertyService();
	boolean show;

	@Override
	public Properties getObject() throws Exception {
		if (isSingleton()) {
			return getInstance();
		} else {
			Properties properties = service.load(this);
			if (show) {
				PropertyUtils.info(properties);
			}
			return properties;
		}
	}

	protected synchronized Properties getInstance() {
		if (instance == null) {
			instance = service.load(this);
		}
		if (show) {
			PropertyUtils.info(instance);
		}
		return instance;
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return this.singleton;
	}

	public void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}

	public PropertyService getService() {
		return service;
	}

	public void setService(PropertyService service) {
		this.service = service;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}
}
