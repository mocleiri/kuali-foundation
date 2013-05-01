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
package org.kuali.common.maven.spring;

import org.apache.maven.project.MavenProject;
import org.kuali.common.util.MavenConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 
 */
@Configuration
public class AlterMavenPropertiesConfig {

	@Autowired
	Environment env;

	@Autowired
	@Qualifier(MavenConstants.MAVEN_PROJECT_BEAN_NAME)
	MavenProject mavenProject;

	@Bean
	public Object whatup() {
		int size = mavenProject.getProperties().size();
		System.out.println("size=" + size);
		return null;
	}

}
