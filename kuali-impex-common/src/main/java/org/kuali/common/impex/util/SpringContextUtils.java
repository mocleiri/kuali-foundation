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

package org.kuali.common.impex.util;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.MavenUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectContext;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.service.SpringService;

public class SpringContextUtils {

	public static Properties getMavenProjectProperties(String location) throws IOException {

		// Setup the project context for kuali-impex-common
		ProjectContext context = new ImpexCommonProjectContext();

		// Load the project.properties automatically embedded into the jar file by Maven
		Project project = ProjectUtils.loadProject(context);

		// Load the properties provided to this method
		Properties loaded = PropertyUtils.load(location);

		// Combine them, where project properties always win
		return PropertyUtils.combine(loaded, project.getProperties());
	}

	public static void loadSpringService(String propertiesLocation, Class<?> propertySourceConfigClass, List<Class<?>> annotatedClasses) throws IOException {

		// Default Spring service will do what we need
		SpringService ss = new DefaultSpringService();

		// Get Maven properties
		Properties mavenProperties = getMavenProjectProperties(propertiesLocation);

		// Setup a Spring context that uses maven properties for placeholder resolution
		SpringContext context = MavenUtils.getMavenizedSpringContext(mavenProperties, propertySourceConfigClass);

		// Complete the setting up of the context
		context.setAnnotatedClasses(annotatedClasses);

		// Execute Spring
		ss.load(context);
	}
}
