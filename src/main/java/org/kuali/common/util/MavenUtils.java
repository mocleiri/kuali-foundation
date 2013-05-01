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
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.GlobalPropertiesMode;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.property.PropertiesContext;
import org.kuali.common.util.property.processor.ProjectProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.property.processor.VersionProcessor;
import org.kuali.common.util.service.PropertySourceContext;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.service.SpringService;
import org.kuali.common.util.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

public class MavenUtils {

	private static final Logger logger = LoggerFactory.getLogger(MavenUtils.class);

	public static final String POM = "pom";
	public static final String INCLUDE = "properties.maven.include";
	public static final String EXCLUDE = "properties.maven.exclude";
	public static final String PROJECT_VERSION_KEY = "project.version";

	public static SpringContext getMavenizedSpringContext(SpringService service, Properties mavenProperties, Class<?> propertySourceConfig) {
		// This PropertySource object is backed by a set of properties that has been
		// 1 - fully resolved
		// 2 - contains all properties needed by Spring
		// 3 - contains system/environment properties where system/env properties override loaded properties
		PropertySource<?> source = getPropertySource(service, mavenProperties, propertySourceConfig);

		// Setup a property source context such that our single property source is the only one registered with Spring
		// This will make it so our PropertySource is the ONLY thing to resolve placeholders
		PropertySourceContext psc = new PropertySourceContext(source, true);

		// Setup a Spring context
		SpringContext context = new SpringContext();

		// Supply Spring with our PropertySource
		context.setPropertySourceContext(psc);

		// Return a Spring context configured with a single property source
		return context;
	}

	protected static PropertySource<?> getPropertySource(SpringService service, Properties mavenProperties, Class<?> annotatedClass) {
		return getPropertySource(service, annotatedClass, Constants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME, mavenProperties);
	}

	protected static PropertySource<?> getPropertySource(SpringService service, Class<?> annotatedClass, String mavenPropertiesBeanName, Properties mavenProperties) {
		List<PropertySource<?>> sources = SpringUtils.getPropertySources(service, annotatedClass, mavenPropertiesBeanName, mavenProperties);
		if (sources.size() > 1) {
			throw new IllegalStateException("More than one PropertySource was registered in the context");
		} else {
			return sources.get(0);
		}
	}

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

		SpringUtils.decrypt(mavenProperties);
		trim(mavenProperties);
		SpringUtils.resolve(mavenProperties);
	}

	public static void trim(Properties mavenProperties) {
		List<String> excludes = getList(mavenProperties, EXCLUDE);
		List<String> includes = getList(mavenProperties, INCLUDE);
		PropertyUtils.trim(mavenProperties, includes, excludes);
	}

	public static void trim(Environment env, Properties mavenProperties) {
		List<String> excludes = getList(env, mavenProperties, EXCLUDE);
		List<String> includes = getList(env, mavenProperties, INCLUDE);
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

	protected static List<String> getList(Properties properties, String key) {
		String csv = properties.getProperty(key);
		List<String> list = new ArrayList<String>();
		list.addAll(CollectionUtils.getTrimmedListFromCSV(csv));
		return list;
	}

	protected static List<String> getList(Environment env, Properties properties, String key) {
		String csv = env.getProperty(key);
		List<String> list = new ArrayList<String>();
		list.addAll(CollectionUtils.getTrimmedListFromCSV(csv));
		list.addAll(getList(properties, key));
		return list;
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
