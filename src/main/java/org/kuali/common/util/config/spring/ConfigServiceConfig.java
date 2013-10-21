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
package org.kuali.common.util.config.spring;

import org.kuali.common.util.config.service.ConfigService;
import org.kuali.common.util.config.service.DefaultConfigService;
import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.project.spring.ProjectServiceConfig;
import org.kuali.common.util.xml.service.XmlService;
import org.kuali.common.util.xml.spring.XmlServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @deprecated
 */
@Deprecated
@Configuration
@Import({ ProjectServiceConfig.class, XmlServiceConfig.class })
public class ConfigServiceConfig {

	@Autowired
	ProjectService projectService;

	@Autowired
	XmlService xmlService;

	@Bean
	public ConfigService configService() {
		return new DefaultConfigService(projectService, xmlService);
	}

}
