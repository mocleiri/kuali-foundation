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
package org.kuali.common.util.spring.service;

import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertySource;

/**
 * 
 */
public interface SpringService {

	ConfigurableApplicationContext getApplicationContext(SpringContext context);

	void load(Class<?> annotatedClass);

	void load(Class<?> annotatedClass, Map<String, Object> contextBeans);

	void load(Class<?> annotatedClass, Map<String, Object> contextBeans, PropertySource<?> propertySource);

	void load(String location);

	void load(String location, Map<String, Object> contextBeans, PropertySource<?> propertySource);

	void load(String location, Map<String, Object> contextBeans);

	void load(SpringContext context);

	@Deprecated
	void load(String location, String beanName, Object bean);

	@Deprecated
	void load(String location, String beanName, Object bean, PropertySource<?> propertySource);

	@Deprecated
	void load(Class<?> annotatedClass, String beanName, Object bean);

	@Deprecated
	void load(Class<?> annotatedClass, String beanName, Object bean, PropertySource<?> propertySource);

}
