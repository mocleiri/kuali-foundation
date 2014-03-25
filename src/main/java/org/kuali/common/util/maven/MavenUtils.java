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
package org.kuali.common.util.maven;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.property.PropertiesContext;
import org.kuali.common.util.property.processor.ProjectProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.property.processor.VersionProcessor;
import org.kuali.common.util.spring.PropertySourceUtils;
import org.kuali.common.util.spring.service.DefaultPropertySourceService;
import org.kuali.common.util.spring.service.DefaultSpringService;
import org.kuali.common.util.spring.service.PropertySourceService;
import org.kuali.common.util.spring.service.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.util.Assert;

/**
 * Maven utilities that don't depend on Maven libraries
 */
public class MavenUtils {

	private static final Logger logger = LoggerFactory.getLogger(MavenUtils.class);

	@Deprecated
	public static final String POM = MavenConstants.POM;

	@Deprecated
	public static final String PROJECT_VERSION_KEY = MavenConstants.VERSION_KEY;

	@Deprecated
	public static final String PROJECT_ENCODING_KEY = MavenConstants.ENCODING_KEY;

	@Deprecated
	public static SpringContext getMavenizedSpringContext(Class<?> propertySourceConfig) {
		return getMavenizedSpringContext(null, propertySourceConfig);
	}

	/**
	 * Return a SpringContext that resolves placeholders using the single <code>PropertySource</code> registered with <code>propertySourceConfig</code>
	 */
	@Deprecated
	public static SpringContext getMavenizedSpringContext(Properties mavenProperties, Class<?> propertySourceConfig) {
		PropertySourceService service = new DefaultPropertySourceService(new DefaultSpringService());
		return getMavenizedSpringContext(service, mavenProperties, propertySourceConfig);
	}

	@Deprecated
	public static SpringContext getMavenizedSpringContext(PropertySourceService service, Properties mavenProperties, Class<?> propertySourceConfig) {
		Map<String, Object> beans = CollectionUtils.toEmptyMap(MavenConstants.PROPERTIES_BEAN_NAME, (Object) mavenProperties);
		List<PropertySource<?>> sources = service.getPropertySources(beans, null, null, propertySourceConfig);
		Assert.isTrue(sources.size() == 1, "sources.size != 1");
		return PropertySourceUtils.getSinglePropertySourceContext(sources.get(0));
	}

	/**
	 * Add organization, group, and path properties and tokenize the version number adding properties for each token along with a boolean property indicating if this is a SNAPSHOT
	 * build
	 */
	@Deprecated
	public static void augmentProjectProperties(ProjectService service, Properties mavenProperties) {
		augmentProjectProperties(mavenProperties);
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
		processors.add(new VersionProcessor(Arrays.asList(MavenConstants.VERSION_KEY), true));

		// Process default Maven properties and add in our custom properties
		PropertyUtils.process(mavenProperties, processors);

		// Finish preparing the properties using the encoding from the project
		String encoding = PropertyUtils.getRequiredResolvedProperty(mavenProperties, MavenConstants.ENCODING_KEY);
		PropertyUtils.prepareContextProperties(mavenProperties, encoding);
	}

	@Deprecated
	public static org.kuali.common.util.property.ProjectProperties getMavenProjectProperties(Properties mavenProperties) {
		org.kuali.common.util.Project project = org.kuali.common.util.ProjectUtils.getProject(mavenProperties);

		PropertiesContext pc = new PropertiesContext();
		pc.setProperties(mavenProperties);

		org.kuali.common.util.property.ProjectProperties pp = new org.kuali.common.util.property.ProjectProperties();
		pp.setProject(project);
		pp.setPropertiesContext(pc);
		return pp;
	}

	@Deprecated
	protected static List<String> getList(Properties properties, String key) {
		String csv = properties.getProperty(key);
		List<String> list = new ArrayList<String>();
		list.addAll(CollectionUtils.getTrimmedListFromCSV(csv));
		return list;
	}

	@Deprecated
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
