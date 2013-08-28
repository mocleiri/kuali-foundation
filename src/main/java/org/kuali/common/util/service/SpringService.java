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
package org.kuali.common.util.service;

import org.springframework.core.env.PropertySource;

/**
 * @deprecated
 */
@Deprecated
public interface SpringService {

	void load(Class<?> annotatedClass);

	void load(Class<?> annotatedClass, String beanName, Object bean);

	void load(Class<?> annotatedClass, String beanName, Object bean, PropertySource<?> propertySource);

	void load(String location);

	void load(String location, String beanName, Object bean);

	void load(String location, String beanName, Object bean, PropertySource<?> propertySource);

	void load(SpringContext context);

}
