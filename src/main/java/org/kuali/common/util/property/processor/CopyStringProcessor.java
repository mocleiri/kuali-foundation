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
package org.kuali.common.util.property.processor;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Copy a String property value into a bean
 */
public class CopyStringProcessor implements PropertyProcessor {

	Object bean;
	String beanProperty;
	String propertyKey;

	public CopyStringProcessor() {
		this(null, null, null);
	}

	public CopyStringProcessor(Object bean, String beanProperty, String propertyKey) {
		super();
		this.bean = bean;
		this.beanProperty = beanProperty;
		this.propertyKey = propertyKey;
	}

	@Override
	public void process(Properties properties) {
		copyProperty(bean, beanProperty, properties, propertyKey);
	}

	protected void copyProperty(Object bean, String beanProperty, Properties properties, String propertyKey) {
		try {
			String value = properties.getProperty(propertyKey);
			BeanUtils.copyProperty(bean, beanProperty, value);
		} catch (InvocationTargetException e) {
			throw new IllegalArgumentException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public String getBeanProperty() {
		return beanProperty;
	}

	public void setBeanProperty(String beanProperty) {
		this.beanProperty = beanProperty;
	}

	public String getPropertyKey() {
		return propertyKey;
	}

	public void setPropertyKey(String propertyKey) {
		this.propertyKey = propertyKey;
	}

}
