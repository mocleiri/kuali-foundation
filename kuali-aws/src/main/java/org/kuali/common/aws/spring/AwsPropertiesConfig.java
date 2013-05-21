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
package org.kuali.common.aws.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.Project;
import org.kuali.common.util.Str;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.spring.ConfigUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AwsProjectConfig.class)
public class AwsPropertiesConfig {

	@Autowired
	AwsProjectConfig projectConfig;

	@Bean
	public ProjectProperties awsProjectProperties() {
		Project project = projectConfig.awsProject();
		List<String> locations = new ArrayList<String>();
		locations.add("classpath:" + Str.getPath(AwsProjectConfig.GROUP_ID) + "/" + AwsProjectConfig.ARTIFACT_ID + ".properties");
		return ConfigUtils.getProjectProperties(project, locations);
	}

}
