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
package org.kuali.common.util.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectContext;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.property.PropertiesContext;

@Deprecated
public class ConfigUtils {

	public static ProjectProperties getProjectProperties(ProjectContext project, Properties properties) {
		ProjectProperties projectProperties = ProjectUtils.getProjectProperties(project);
		combine(projectProperties, properties);
		return projectProperties;
	}

	public static void combine(ProjectProperties projectProperties, Properties properties) {
		Properties existing = projectProperties.getPropertiesContext().getProperties();
		Properties combined = PropertyUtils.combine(existing, properties);
		projectProperties.getPropertiesContext().setProperties(combined);
	}

	public static List<ProjectProperties> getProjectProperties(List<ProjectContext> contexts) {
		List<ProjectProperties> list = new ArrayList<ProjectProperties>();
		for (ProjectContext context : contexts) {
			Project project = ProjectUtils.loadProject(context);
			ProjectProperties pp = getProjectProperties(project, context.getPropertyLocations());
			list.add(pp);
		}
		return list;
	}

	public static List<ProjectProperties> getProjectProperties(ProjectContext... contexts) {
		return getProjectProperties(Arrays.asList(contexts));
	}

	public static ProjectProperties getProjectProperties(ProjectContext context) {
		Project project = ProjectUtils.loadProject(context);
		return getProjectProperties(project, context.getPropertyLocations());
	}

	public static ProjectProperties getProjectProperties(Project project, List<String> locations) {
		PropertiesContext pc = new PropertiesContext();
		pc.setEncoding(project.getEncoding());
		pc.setLocations(locations);
		return new ProjectProperties(project, pc);
	}
}
