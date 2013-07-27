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
package org.kuali.common.util.config.spring.aggregation;

import java.util.Properties;

import org.kuali.common.util.project.Project;
import org.kuali.common.util.project.spring.config.MavenProjectConfig;
import org.kuali.common.util.project.spring.config.ProjectConfig;
import org.kuali.common.util.project.spring.config.RuntimeProjectConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MavenProjectConfig.class, RuntimeProjectConfig.class })
public class ProjectConfigOverridesConfig implements ConfigOverridesConfig {

	@Autowired
	ProjectConfig projectConfig;

	@Override
	public Properties configOverrideProperties() {
		Project project = projectConfig.project();
		Properties overrides = project.getProperties();
		return overrides;
	}

}
