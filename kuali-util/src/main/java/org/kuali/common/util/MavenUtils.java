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
package org.kuali.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.property.GlobalPropertiesMode;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.property.PropertiesContext;
import org.kuali.common.util.property.processor.ProjectProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.property.processor.VersionProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

public class MavenUtils {

	private static final Logger logger = LoggerFactory.getLogger(MavenUtils.class);

	public static final String POM = "pom";
	public static final String INCLUDE = "properties.maven.include";
	public static final String EXCLUDE = "properties.maven.exclude";
	public static final String PROJECT_VERSION_KEY = "project.version";

	/**
	 * Add organization, group, and path properties and tokenize the version number adding properties for each token along with a boolean property indicating if this is a SNAPSHOT
	 * build
	 */
	public static void augmentProjectProperties(Properties mavenProperties) {
		// Setup some processors
		List<PropertyProcessor> processors = new ArrayList<PropertyProcessor>();

		// Add some organization, group, and path properties
		processors.add(new ProjectProcessor());

		// Tokenize the version number and add properties for each token (major/minor/incremental)
		// Also add a boolean property indicating if this is a SNAPSHOT build
		processors.add(new VersionProcessor(Arrays.asList(PROJECT_VERSION_KEY), true));

		// Process default Maven properties and add in our custom properties
		PropertyUtils.process(mavenProperties, processors);

		// Make sure system/environment properties still always win
		PropertyUtils.overrideWithGlobalValues(mavenProperties, GlobalPropertiesMode.BOTH);
	}

	public static void trim(Environment env, Properties mavenProperties) {
		List<String> excludes = getList(env, EXCLUDE);
		List<String> includes = getList(env, INCLUDE);
		PropertyUtils.trim(mavenProperties, includes, excludes);
	}

	public static ProjectProperties getMavenProjectProperties(Environment env, Properties mavenProperties) {
		Project project = ProjectUtils.getProject(mavenProperties);

		trim(env, mavenProperties);

		PropertiesContext pc = new PropertiesContext();
		pc.setProperties(mavenProperties);

		ProjectProperties pp = new ProjectProperties();
		pp.setProject(project);
		pp.setPropertiesContext(pc);
		return pp;
	}

	protected static List<String> getList(Environment env, String key) {
		String csv = env.getProperty(key);
		return CollectionUtils.getTrimmedListFromCSV(csv);
	}

	/**
	 * Always return false if <code>forceMojoExecution</code> is true, otherwise return true only if <code>skip</code> is true or <code>packaging</code> is pom.
	 */
	public static final boolean skip(boolean forceMojoExecution, boolean skip, String packaging) {
		if (forceMojoExecution) {
			logger.info("Forced mojo execution");
			return false;
		}
		if (skip) {
			logger.info("Skipping mojo execution");
			return true;
		}
		if (StringUtils.equalsIgnoreCase(packaging, POM)) {
			logger.info("Skipping mojo execution for project with packaging type '{}'", POM);
			return true;
		} else {
			return false;
		}
	}

}
