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

package org.kuali.common.impex.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.impex.schema.ProjectSchemaExportExecutable;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * This class supports exporting a schema with multiple output modules
 */
@Configuration
public class ProjectSchemaExportConfig {

	public static final String PROJECTS_KEY = "impex.export.schema.projects";

	@Autowired
	Environment env;

	@Bean
	public List<ProjectSchemaExportExecutable> projectSchemaExportExecutables() {

		List<String> projects = CollectionUtils.getTrimmedListFromCSV(SpringUtils.getProperty(env, PROJECTS_KEY));

		List<ProjectSchemaExportExecutable> executables = new ArrayList<ProjectSchemaExportExecutable>();

		for (String gav : projects) {
			Project project = ProjectUtils.loadProject(gav);
			ProjectSchemaExportExecutable psee = new ProjectSchemaExportExecutable();
			psee.setProject(project);
			executables.add(psee);
		}
		return executables;
	}

}
