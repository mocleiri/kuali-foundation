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

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.MavenUtils;
import org.kuali.common.util.property.ProjectProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.util.Assert;

@Configuration
public abstract class AbstractMavenPropertySourceConfig {

	@Autowired
	Environment env;

	@Autowired
	Properties mavenProperties;

	@Bean
	public ProjectProperties mavenProjectProperties() {
		Assert.notNull(mavenProperties, "mavenProperties are null");
		MavenUtils.augmentProjectProperties(mavenProperties);
		return MavenUtils.getMavenProjectProperties(env, mavenProperties);
	}

	protected abstract List<ProjectProperties> getProjectPropertiesList();

	@Bean
	public PropertySource<?> springPropertySource() {
		// Combine project properties into a list where the "last one in wins"
		List<ProjectProperties> pps = getProjectPropertiesList();

		// Add the current project's maven properties last45
		pps.add(mavenProjectProperties());

		// Get a PropertySource object backed by the properties loaded from the list
		return SpringUtils.getPropertySource("springPropertySource", pps);
	}

}
