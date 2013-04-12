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
package org.kuali.common.deploy.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.common.jdbc.spring.JdbcPropertiesConfig;
import org.kuali.common.util.MavenUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

@Configuration
@Import({ JdbcPropertiesConfig.class, DeployPropertiesConfig.class })
public class MavenDeployConfig {

	@Autowired
	protected Environment env;

	@Autowired
	protected JdbcPropertiesConfig jdbcProperties;

	@Autowired
	protected DeployPropertiesConfig deployProperties;

	@Autowired
	@Qualifier(Constants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME)
	protected Properties mavenProperties;

	@Bean
	public ProjectProperties mavenProjectProperties() {
		return MavenUtils.getMavenProjectProperties(env, mavenProperties);
	}

	public List<ProjectProperties> getProjectPropertiesList() {
		List<ProjectProperties> pps = new ArrayList<ProjectProperties>();
		pps.add(jdbcProperties.jdbcProjectProperties());
		pps.add(deployProperties.deployProjectProperties());
		pps.add(mavenProjectProperties());
		return pps;
	}

	@Bean
	public PropertySource<?> springPropertySource() {
		// Combine project properties into a list where the "last one in wins"
		List<ProjectProperties> pps = getProjectPropertiesList();

		// Get a PropertySource object backed by the properties loaded from the list
		return SpringUtils.getPropertySource("springPropertySource", pps);
	}

	@Bean(initMethod = "execute")
	public Executable springExecutable() {
		PropertySource<?> ps = springPropertySource();
		Properties properties = (Properties) ps.getSource();
		PropertyUtils.info(properties);
		return null;
		// Setup a flag for skipping execution completely
		// boolean skip = SpringUtils.getBoolean(env, "db.reset.skip", false);
		// Get an executable, backed by the correct set of properties, loading the correct config
		// return SpringUtils.getSpringExecutable(env, skip, springPropertySource(), CollectionUtils.asList(DeployConfig.class, ResetConfig.class, ResetController.class));
	}

}
