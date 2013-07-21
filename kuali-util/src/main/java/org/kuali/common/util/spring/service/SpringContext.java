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

import java.util.List;

import org.kuali.common.util.CollectionUtils;

public class SpringContext {

	public SpringContext() {
		this((Class<?>) null);
	}

	public SpringContext(Class<?> annotatedClass) {
		this(CollectionUtils.asList(annotatedClass));
	}

	public SpringContext(List<Class<?>> annotatedClasses) {
		super();
		this.annotatedClasses = annotatedClasses;
	}

	String id;
	String displayName;
	List<String> locations;
	List<Class<?>> annotatedClasses;
	List<String> beanNames;
	List<Object> beans;
	PropertySourceContext propertySourceContext;
	List<String> activeProfiles;

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public List<Class<?>> getAnnotatedClasses() {
		return annotatedClasses;
	}

	public void setAnnotatedClasses(List<Class<?>> annotatedClasses) {
		this.annotatedClasses = annotatedClasses;
	}

	public List<String> getBeanNames() {
		return beanNames;
	}

	public void setBeanNames(List<String> beanNames) {
		this.beanNames = beanNames;
	}

	public List<Object> getBeans() {
		return beans;
	}

	public void setBeans(List<Object> beans) {
		this.beans = beans;
	}

	public PropertySourceContext getPropertySourceContext() {
		return propertySourceContext;
	}

	public void setPropertySourceContext(PropertySourceContext propertySourceContext) {
		this.propertySourceContext = propertySourceContext;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<String> getActiveProfiles() {
		return activeProfiles;
	}

	public void setActiveProfiles(List<String> activeProfiles) {
		this.activeProfiles = activeProfiles;
	}

}
