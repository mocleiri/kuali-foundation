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

import java.util.List;

import org.apache.maven.project.MavenProject;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.MavenConstants;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.ShowPropertiesExecutable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ShowMavenPropertiesConfig {

	@Autowired
	Environment env;

	@Autowired
	@Qualifier(MavenConstants.MAVEN_PROJECT_BEAN_NAME)
	MavenProject mavenProject;

	@Bean(initMethod = "execute")
	public Executable showNativeMavenPropertiesExecutable() {
		List<String> includes = getList("properties.show.includes", "*");
		List<String> excludes = getList("properties.show.excludes", "");

		ShowPropertiesExecutable executable = new ShowPropertiesExecutable();
		executable.setProperties(mavenProject.getProperties());
		executable.setExcludes(excludes);
		executable.setIncludes(includes);
		return executable;
	}

	protected List<String> getList(String key, String defaultValue) {
		String csv = SpringUtils.getProperty(env, key, defaultValue);
		return CollectionUtils.getTrimmedListFromCSV(csv);
	}

}
