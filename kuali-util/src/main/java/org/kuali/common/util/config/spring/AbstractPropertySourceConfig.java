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

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.config.ProjectConfigService;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.PropertySource;

/**
 * 
 */
@Configuration
@Import({ ProjectConfigSpringConfig.class })
public abstract class AbstractPropertySourceConfig {

	@Autowired
	ProjectConfigSpringConfig projectConfigSpringConfig;

	/**
	 * <p>
	 * Returns <code>Collections.emptyList()</code> by default. Override this method to pull in properties from other sources.
	 * </p>
	 * 
	 * Example configIds:
	 * 
	 * <pre>
	 *   org.kuali.common:kuali-sql
	 *   org.kuali.common:kuali-util:metainf:mpx
	 *   org.kuali.common:kuali-util:metainf:sql
	 * </pre>
	 */
	protected List<String> getConfigIds() {
		return Collections.emptyList();
	}

	/**
	 * Return properties for the current project.
	 */
	protected abstract Properties getProjectProperties();

	/**
	 * Combine loaded properties, project properties, and system/environment properties into a <code>PropertySource<?></code>
	 */
	protected PropertySource<?> getPropertySource() {
		ProjectConfigService service = projectConfigSpringConfig.utilProjectConfigService();
		Properties projectProperties = getProjectProperties();
		List<String> configIds = getConfigIds();
		Properties properties = service.getProperties(projectProperties, configIds);
		return SpringUtils.getGlobalPropertySource(properties);
	}

	@Bean
	public PropertySource<?> springPropertySource() {
		return getPropertySource();
	}

}
