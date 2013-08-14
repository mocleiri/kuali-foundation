/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.jdbc.service.spring;

import org.kuali.common.util.properties.PropertiesService;
import org.kuali.common.util.properties.spring.DefaultPropertiesServiceConfig;
import org.kuali.common.util.spring.PropertySourceUtils;
import org.kuali.common.util.spring.service.PropertySourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.PropertySource;

@Configuration
@Import({ DefaultPropertiesServiceConfig.class, JdbcPropertyLocationsConfig.class })
public class JdbcPropertySourceConfig implements PropertySourceConfig {

	@Autowired
	PropertiesService service;

	@Autowired
	JdbcPropertyLocationsConfig jdbc;

	@Override
	@Bean
	public PropertySource<?> propertySource() {
		return PropertySourceUtils.getPropertySource(service, jdbc.jdbcPropertyLocations());
	}

}
