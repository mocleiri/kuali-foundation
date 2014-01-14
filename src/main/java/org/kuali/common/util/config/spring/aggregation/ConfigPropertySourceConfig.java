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

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.spring.service.PropertySourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

/**
 * @deprecated
 */
@Deprecated
@Configuration
@Import({ org.kuali.common.util.config.spring.ConfigServiceConfig.class })
public class ConfigPropertySourceConfig implements PropertySourceConfig {

	private static final String PROPERTY_SOURCE_NAME = "configPropertySource";

	@Autowired
	org.kuali.common.util.config.spring.ConfigServiceConfig configServiceConfig;

	@Autowired
	ConfigOverridesConfig configOverridesConfig;

	@Autowired
	ConfigIdsConfig configIdsConfig;

	@Override
	@Bean
	public PropertySource<?> propertySource() {
		org.kuali.common.util.config.service.ConfigService service = configServiceConfig.configService();
		Properties overrides = configOverridesConfig.configOverrideProperties();
		List<String> configIds = configIdsConfig.configIds();
		Properties properties = service.getProperties(configIds, overrides);
		return new PropertiesPropertySource(PROPERTY_SOURCE_NAME, properties);
	}

}
