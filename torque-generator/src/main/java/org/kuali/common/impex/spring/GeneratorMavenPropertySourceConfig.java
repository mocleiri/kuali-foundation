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
package org.kuali.common.impex.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.spring.MavenPropertySourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ GeneratorPropertiesConfig.class })
public class GeneratorMavenPropertySourceConfig extends MavenPropertySourceConfig {

	@Autowired
	protected GeneratorPropertiesConfig generatorProperties;

	@Override
	protected List<ProjectProperties> getProjectPropertiesList() {
		List<ProjectProperties> list = new ArrayList<ProjectProperties>();
		list.add(generatorProperties.generatorProjectProperties());
		return list;
	}

}
