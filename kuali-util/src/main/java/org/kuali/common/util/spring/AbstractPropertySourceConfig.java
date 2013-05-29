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

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.property.ProjectProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertySource;

/**
 * 
 */
@Configuration
public abstract class AbstractPropertySourceConfig {

	public static final String SPRING_PROPERTY_SOURCE = "springPropertySource";

	/**
	 * This returns an empty list by default. Add in <code>ProjectProperties</code> as appropriate. Properties from this list use a "last one in wins" strategy.
	 */
	protected List<ProjectProperties> getProjectPropertiesList() {
		return new ArrayList<ProjectProperties>();
	}

	protected abstract ProjectProperties getProjectProperties();

	public PropertySource<?> getPropertySource() {

		// Obtain the properties for this project
		ProjectProperties pp = getProjectProperties();

		// Property loading uses a "last one in wins" strategy
		List<ProjectProperties> pps = new ArrayList<ProjectProperties>();

		// Add project properties first so they can be used to resolve locations
		pps.add(pp);

		// Load in project properties
		pps.addAll(getProjectPropertiesList());

		// Add project properties last so they override loaded properties
		pps.add(pp);

		// Get a PropertySource object backed by the properties loaded from the list as well as system/environment properties
		return SpringUtils.getGlobalPropertySource(SPRING_PROPERTY_SOURCE, pps);
	}

	@Bean
	public PropertySource<?> springPropertySource() {
		return getPropertySource();
	}

}
