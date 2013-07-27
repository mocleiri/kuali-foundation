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

import org.kuali.common.util.spring.PropertySourceUtils;
import org.springframework.core.env.PropertySource;

public class PropertySourceContext {

	public static final boolean DEFAULT_REMOVE_EXISTING_SOURCES = false;
	public static final boolean DEFAULT_LAST_ONE_IN_WINS = true;
	public static final PropertySourceAddPriority DEFAULT_PRIORITY = PropertySourceAddPriority.LAST;

	// If true, any existing property sources are removed and replaced by the list from this context
	boolean removeExistingSources = DEFAULT_REMOVE_EXISTING_SOURCES;

	// If true, the last PropertySource in the list has the highest priority
	// That is to say, Spring will search for property values starting at the bottom of the list and work its way upwards, stopping as soon as it has a match
	boolean lastOneInWins = DEFAULT_LAST_ONE_IN_WINS;

	// Can add property sources before or after existing property sources
	PropertySourceAddPriority priority = DEFAULT_PRIORITY;

	// The list of property source objects to add to the environment
	List<PropertySource<?>> sources;

	public PropertySourceContext() {
		this(null);
	}

	public PropertySourceContext(List<PropertySource<?>> sources) {
		this(sources, DEFAULT_REMOVE_EXISTING_SOURCES);
	}

	public PropertySourceContext(PropertySource<?> source, boolean removeExistingSources) {
		this(PropertySourceUtils.asList(source), removeExistingSources);
	}

	public PropertySourceContext(List<PropertySource<?>> sources, boolean removeExistingSources) {
		super();
		this.sources = sources;
		this.removeExistingSources = removeExistingSources;
	}

	public boolean isRemoveExistingSources() {
		return removeExistingSources;
	}

	public void setRemoveExistingSources(boolean removeExistingSources) {
		this.removeExistingSources = removeExistingSources;
	}

	public boolean isLastOneInWins() {
		return lastOneInWins;
	}

	public void setLastOneInWins(boolean lastOneInWins) {
		this.lastOneInWins = lastOneInWins;
	}

	public List<PropertySource<?>> getSources() {
		return sources;
	}

	public void setSources(List<PropertySource<?>> sources) {
		this.sources = sources;
	}

	public PropertySourceAddPriority getPriority() {
		return priority;
	}

	public void setPriority(PropertySourceAddPriority priority) {
		this.priority = priority;
	}

}
