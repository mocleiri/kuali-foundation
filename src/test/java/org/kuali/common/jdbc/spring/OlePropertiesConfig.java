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

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.MavenUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.spring.ConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JdbcPropertiesConfig.class)
public class OlePropertiesConfig {

	// Simulate the properties supplied by Maven for OLE
	public static final Properties OLE_MAVEN_PROPS = getOleMavenProperties();

	@Bean
	public ProjectProperties oleProjectProperties() {
		Project project = ProjectUtils.getProject(OLE_MAVEN_PROPS);
		List<String> locations = Arrays.asList("classpath:ole-fs.properties");
		return ConfigUtils.getProjectProperties(project, locations);
	}

	private static Properties getOleMavenProperties() {
		Properties p = new Properties();
		p.setProperty("project.groupId", "org.kuali.ole");
		p.setProperty("project.artifactId", "ole-fs");
		p.setProperty("project.version", "0.8.1-s-r11074");
		p.setProperty("project.encoding", "UTF-8");
		p.setProperty("project.orgId", "org.kuali");
		p.setProperty("project.orgId.code", "kuali");
		p.setProperty("project.orgId.path", "org/kuali");

		MavenUtils.augmentProjectProperties(p);

		return p;
	}

}
