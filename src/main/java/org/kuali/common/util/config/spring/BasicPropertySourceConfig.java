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

import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.PropertySource;

/**
 * @deprecated
 */
@Deprecated
@Configuration
@Import({ ConfigServiceConfig.class })
public class BasicPropertySourceConfig {

	@Autowired
	ConfigServiceConfig utilConfigServiceConfig;

	/**
	 * <p>
	 * Returns <code>Collections.emptyList()</code> by default. Override this method to pull in properties from other locations
	 * </p>
	 * 
	 * Example configIds:
	 * 
	 * <pre>
	 *   org.kuali.common:kuali-sql
	 *   org.kuali.common:kuali-util:scm
	 *   org.kuali.common:kuali-util:metainf:mpx
	 *   org.kuali.common:kuali-util:metainf:sql
	 * </pre>
	 */
	protected List<String> getConfigIds() {
		return Collections.emptyList();
	}

	/**
	 * <p>
	 * Returns an empty properties object by default. Override this method to inject properties that will override anything loaded from other locations
	 * </p>
	 */
	protected Properties getOverrides() {
		return new Properties();
	}

	/**
	 * Combine loaded properties, project properties, and system/environment properties into a <code>PropertySource<?></code>
	 */
	protected PropertySource<?> getPropertySource() {
		org.kuali.common.util.config.service.ConfigService service = utilConfigServiceConfig.configService();
		List<String> configIds = getConfigIds();
		Properties properties = service.getProperties(configIds, getOverrides());
		return SpringUtils.getGlobalPropertySource(properties);
	}

	@Bean
	public PropertySource<?> springPropertySource() {
		return getPropertySource();
	}

}
