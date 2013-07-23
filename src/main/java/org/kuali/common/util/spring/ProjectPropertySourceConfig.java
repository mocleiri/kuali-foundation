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

import org.kuali.common.util.maven.MavenConstants;
import org.kuali.common.util.maven.MavenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertiesPropertySource;

/**
 * Augment the default set of properties that ship with Maven and register them as a Spring <code>PropertySource</code>.
 * 
 * spring-maven-plugin auto-registers any beans that implement <code>PropertySource</code> as a top level property source
 */
@Configuration
public class ProjectPropertySourceConfig {

	/**
	 * spring-maven-plugin auto-wires Maven properties by default
	 */
	@Autowired
	@Qualifier(MavenConstants.PROPERTIES_BEAN_NAME)
	Properties mavenProperties;

	@Bean
	public PropertiesPropertySource projectPropertySource() {

		// organization, group, path and enhanced version properties
		MavenUtils.augmentProjectProperties(mavenProperties);

		// Return the augmented set of Maven properties as a Spring PropertySource
		String name = MavenConstants.PROPERTIES_BEAN_NAME;
		return new PropertiesPropertySource(name, mavenProperties);
	}
}
