/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.kuali.common.maven.spring;

import java.util.Properties;

import org.apache.maven.project.MavenProject;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.SetSourceDbSchemaNameExecutable;
import org.kuali.common.util.maven.MavenConstants;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SetSourceDbSchemaNameConfig {

	@Autowired
	Environment env;

	@Autowired
	@Qualifier(MavenConstants.PROJECT_BEAN_NAME)
	MavenProject mavenProject;

	@Bean(initMethod = "execute")
	public Executable setSourceDbSchemaNameExecutable() {
		boolean skip = SpringUtils.getBoolean(env, "jdbc.source.db.setSchemaName.skip", false);
		String baseSourceDbSchemaName = SpringUtils.getProperty(env, "jdbc.source.db.base");
		Properties mavenProperties = mavenProject.getProperties();
		String version = mavenProject.getVersion();

		SetSourceDbSchemaNameExecutable executable = new SetSourceDbSchemaNameExecutable();
		executable.setBaseSourceDbSchemaName(baseSourceDbSchemaName);
		executable.setMavenProperties(mavenProperties);
		executable.setSkip(skip);
		executable.setVersion(version);
		return executable;
	}

}
