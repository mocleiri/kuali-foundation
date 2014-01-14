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
import java.util.Map;

import org.kuali.common.util.CollectionUtils;

public class SpringContext {

	public SpringContext(Map<String, Object> beans, Class<?> annotatedClass) {
		this(beans, CollectionUtils.asList(annotatedClass), (String) null);
	}

	public SpringContext(Map<String, Object> beans, Class<?> annotatedClass, String activeProfile) {
		this(beans, CollectionUtils.asList(annotatedClass), null, CollectionUtils.toEmptyList(activeProfile));
	}

	public SpringContext(Map<String, Object> beans, List<Class<?>> annotatedClasses, String activeProfile) {
		this(beans, annotatedClasses, null, CollectionUtils.toEmptyList(activeProfile));
	}

	public SpringContext(PropertySourceContext propertySourceContext) {
		this((Class<?>) null, propertySourceContext);
	}

	public SpringContext() {
		this((Class<?>) null);
	}

	public SpringContext(Class<?> annotatedClass) {
		this(CollectionUtils.asList(annotatedClass));
	}

	public SpringContext(Class<?> annotatedClass, PropertySourceContext propertySourceContext) {
		this(CollectionUtils.asList(annotatedClass), propertySourceContext);
	}

	public SpringContext(List<Class<?>> annotatedClasses) {
		this(annotatedClasses, null);
	}

	public SpringContext(List<Class<?>> annotatedClasses, PropertySourceContext propertySourceContext) {
		this(null, annotatedClasses, propertySourceContext);
	}

	public SpringContext(Map<String, Object> contextBeans, List<Class<?>> annotatedClasses, PropertySourceContext propertySourceContext) {
		this(contextBeans, annotatedClasses, propertySourceContext, null);
	}

	public SpringContext(Map<String, Object> contextBeans, List<Class<?>> annotatedClasses, PropertySourceContext propertySourceContext, List<String> activeProfiles) {
		this.contextBeans = contextBeans;
		this.annotatedClasses = annotatedClasses;
		this.propertySourceContext = propertySourceContext;
		this.activeProfiles = activeProfiles;
	}

	String id;
	String displayName;
	List<String> locations;
	List<Class<?>> annotatedClasses;
	Map<String, Object> contextBeans;
	PropertySourceContext propertySourceContext;
	List<String> activeProfiles;
	List<String> defaultProfiles;

	@Deprecated
	List<String> beanNames;

	@Deprecated
	List<Object> beans;

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

	@Deprecated
	public List<String> getBeanNames() {
		return beanNames;
	}

	@Deprecated
	public void setBeanNames(List<String> beanNames) {
		this.beanNames = beanNames;
	}

	@Deprecated
	public List<Object> getBeans() {
		return beans;
	}

	@Deprecated
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

	public List<String> getDefaultProfiles() {
		return defaultProfiles;
	}

	public void setDefaultProfiles(List<String> defaultProfiles) {
		this.defaultProfiles = defaultProfiles;
	}

	public Map<String, Object> getContextBeans() {
		return contextBeans;
	}

	public void setContextBeans(Map<String, Object> contextBeans) {
		this.contextBeans = contextBeans;
	}

}
