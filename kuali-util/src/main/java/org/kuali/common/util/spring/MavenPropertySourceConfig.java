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
import java.util.Properties;

import org.kuali.common.util.MavenUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.ProjectProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.util.Assert;

/**
 * Add properties from the currently executing Maven project to the list of explicitly configured <code>ProjectProperties</code> and then combine all of them into a property source
 * object.
 */
@Configuration
public class MavenPropertySourceConfig {

	public static final String SPRING_PROPERTY_SOURCE = "springPropertySource";

	@Autowired
	Environment env;

	@Autowired
	@Qualifier(Constants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME)
	Properties mavenProperties;

	@Bean
	public ProjectProperties mavenProjectProperties() {
		// Make sure the maven properties got wired in correctly
		Assert.notNull(mavenProperties, "mavenProperties are null");

		// Add in org, group, home, and enhanced version properties
		MavenUtils.augmentProjectProperties(mavenProperties);

		// Create a ProjectProperties pojo from the properties
		return MavenUtils.getMavenProjectProperties(env, mavenProperties);
	}

	/**
	 * This returns an empty list by default. Add in <code>ProjectProperties</code> as appropriate. Properties from this list use a "last one in wins" strategy.
	 */
	protected List<ProjectProperties> getProjectPropertiesList() {
		return new ArrayList<ProjectProperties>();
	}

	@Bean
	public PropertySource<?> springPropertySource() {
		// Get the list of project properties we will be loading where properties use a "last one in wins" strategy
		List<ProjectProperties> pps = getProjectPropertiesList();

		// Add the current project's Maven properties last
		pps.add(mavenProjectProperties());

		// Get a PropertySource object backed by the properties loaded from the list as well as system/environment properties
		return SpringUtils.getGlobalPropertySource(SPRING_PROPERTY_SOURCE, pps);
	}

}
