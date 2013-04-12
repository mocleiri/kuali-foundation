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
package org.kuali.common.deploy;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.common.deploy.spring.DeployConfig;
import org.kuali.common.deploy.spring.DeployPropertiesConfig;
import org.kuali.common.impex.spring.GeneratorPropertiesConfig;
import org.kuali.common.impex.spring.MpxSupplierConfig;
import org.kuali.common.jdbc.spring.JdbcPropertiesConfig;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.property.PropertiesContext;
import org.kuali.common.util.property.processor.ProjectProcessor;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

@Configuration
@Import({ JdbcPropertiesConfig.class, GeneratorPropertiesConfig.class, MpxSupplierConfig.class, DeployPropertiesConfig.class })
public class KSDeployConfig {

	@Autowired
	protected Environment env;

	@Autowired
	protected JdbcPropertiesConfig jdbcProperties;

	@Autowired
	protected GeneratorPropertiesConfig generatorProperties;

	@Autowired
	protected DeployPropertiesConfig deployProperties;

	public List<ProjectProperties> getProjectPropertiesList() {
		List<ProjectProperties> pps = new ArrayList<ProjectProperties>();
		pps.add(mavenProperties());
		pps.add(jdbcProperties.jdbcProjectProperties());
		pps.add(generatorProperties.generatorProjectProperties());
		pps.add(deployProperties.deployProjectProperties());
		return pps;
	}

	@Bean
	public ProjectProperties mavenProperties() {
		Properties p = new Properties();
		p.setProperty("project.orgId", "org.kuali");
		p.setProperty("project.orgId.code", "kuali");
		p.setProperty("project.orgId.path", "org/kuali");
		p.setProperty("project.groupId", "org.kuali.student.web");
		p.setProperty("project.artifactId", "ks-with-rice-bundled");
		p.setProperty("project.version", "2.0.0-SNAPSHOT");

		ProjectProcessor processor = new ProjectProcessor();
		processor.process(p);

		Project project = new Project();
		project.setGroupId(p.getProperty("project.groupId"));
		project.setArtifactId(p.getProperty("project.artifactId"));
		project.setVersion(p.getProperty("project.version"));
		project.setEncoding("UTF-8");

		PropertiesContext pc = new PropertiesContext();
		pc.setEncoding(project.getEncoding());
		pc.setProperties(p);

		ProjectProperties pp = new ProjectProperties();
		pp.setProject(project);
		pp.setPropertiesContext(pc);
		return pp;
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
		// Setup a flag for skipping the deploy completely
		boolean skip = SpringUtils.getBoolean(env, "kdo.deploy.skip", false);
		// Get an executable, backed by the correct set of properties, loading the correct config
		return SpringUtils.getSpringExecutable(env, skip, springPropertySource(), CollectionUtils.asList(DeployConfig.class));
	}

}
