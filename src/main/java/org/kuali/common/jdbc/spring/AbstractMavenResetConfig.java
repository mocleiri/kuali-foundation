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
package org.kuali.common.jdbc.spring;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.SpringExecutable;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.property.PropertiesContext;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.PropertySourceContext;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

@Configuration
@Import({ JdbcPropertiesConfig.class })
public abstract class AbstractMavenResetConfig {

	@Autowired
	protected Environment env;

	@Autowired
	protected JdbcPropertiesConfig jdbcProperties;

	@Autowired
	protected Properties mavenProperties;

	@Bean
	public ProjectProperties mavenProjectProperties() {
		Project project = ProjectUtils.getProject(mavenProperties);

		List<String> excludes = getList(env, "properties.maven.exclude");
		PropertyUtils.trim(mavenProperties, null, excludes);

		PropertiesContext pc = new PropertiesContext();
		pc.setProperties(mavenProperties);

		ProjectProperties pp = new ProjectProperties();
		pp.setProject(project);
		pp.setPropertiesContext(pc);
		return pp;
	}

	protected List<String> getList(Environment env, String key) {
		String csv = env.getProperty(key);
		return CollectionUtils.getTrimmedListFromCSV(csv);
	}

	protected abstract List<ProjectProperties> getProjectPropertiesList();

	protected abstract List<Class<?>> getAnnotatedClasses();

	@Bean
	public PropertySource<?> springPropertySource() {
		// Combine project properties into a list where the "last one in wins"
		List<ProjectProperties> pps = getProjectPropertiesList();

		// Get a PropertySource object backed by the properties loaded from the list
		return SpringUtils.getPropertySource("springPropertySource", pps);
	}

	@Bean(initMethod = "execute")
	public Executable springExecutable() {
		// Simple flag for skipping execution altogether
		boolean skip = SpringUtils.getBoolean(env, "db.reset.skip", false);

		/**
		 * This line creates a property source containing 100% of the properties needed by Spring to resolve any/all placeholders. It will be the only property source available to
		 * Spring so it needs to include system properties and environment variables
		 */
		PropertySourceContext psc = new PropertySourceContext(springPropertySource(), true);

		// Setup the list containing annotated configuration classes
		List<Class<?>> annotatedClasses = getAnnotatedClasses();

		// Setup the Spring context
		SpringContext context = new SpringContext();
		context.setAnnotatedClasses(annotatedClasses);
		context.setPropertySourceContext(psc);

		// Load the context
		SpringExecutable se = new SpringExecutable();
		se.setService(new DefaultSpringService());
		se.setContext(context);
		se.setSkip(skip);
		return se;
	}

}
