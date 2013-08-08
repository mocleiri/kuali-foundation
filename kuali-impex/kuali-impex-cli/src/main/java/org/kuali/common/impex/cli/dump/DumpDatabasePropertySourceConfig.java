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

package org.kuali.common.impex.cli.dump;

import org.kuali.common.impex.cli.project.ImpexCLIProjectIdConfig;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.properties.spring.PropertiesLocationServiceConfig;
import org.kuali.common.util.spring.main.MainContext;
import org.kuali.common.util.spring.service.PropertySourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.PropertySource;
import org.springframework.util.Assert;

@Configuration
@Import({ ImpexCLIProjectIdConfig.class, PropertiesLocationServiceConfig.class })
public class DumpDatabasePropertySourceConfig implements PropertySourceConfig {

	@Autowired
	MainContext context;

	@Autowired
	Project project;

	@Override
	@Bean
	public PropertySource<?> propertySource() {
		String[] args = context.getArgs();
		Assert.notNull(args, "args is null");
		Assert.isTrue(args.length > 0, "args.length <= 0");

		String location = args[0];

		return null;
	}

}
