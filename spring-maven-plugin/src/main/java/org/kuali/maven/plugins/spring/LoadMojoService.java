/**
 * Copyright 2011-2013 The Kuali Foundation
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
package org.kuali.maven.plugins.spring;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.MavenUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.GlobalPropertiesMode;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.service.SpringService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Service;

@Service
public class LoadMojoService {

	public void execute(LoadMojo mojo) {
		// Might be skipping execution altogether
		if (MavenUtils.skip(mojo.isForceMojoExecution(), mojo.isSkip(), mojo.getProject().getPackaging())) {
			return;
		}

		// Combine mojo properties, project properties and internal maven properties into a Properties object
		Properties mavenProperties = getMavenProperties(mojo);

		// Aggregate objects into a SpringContext
		SpringContext context = getSpringContext(mojo, mavenProperties);

		// Instantiate the implementation of SpringService we will be using
		SpringService service = getSpringService(mojo.getServiceClassname());

		// Are we adding any custom property sources?
		if (mojo.isAddPropertySources()) {
			// If so, extract PropertySource objects from the PropertySources context
			List<PropertySource<?>> sources = getPropertySources(service, mojo.getPropertySourcesLocation(), mavenProperties);
			// Add them to the SpringContext
			context.setPropertySources(sources);
		}

		// Invoke the service to load the context and inject it with beans as appropriate
		service.load(context);
	}

	protected List<PropertySource<?>> getPropertySources(SpringService service, String location, Properties mavenProperties) {
		String[] locationsArray = { location };
		ConfigurableApplicationContext parent = service.getContextWithPreRegisteredBean("mavenProperties", mavenProperties);
		ConfigurableApplicationContext child = new ClassPathXmlApplicationContext(locationsArray, parent);
		return service.getPropertySources(child);
	}

	protected SpringContext getSpringContext(LoadMojo mojo, Properties mavenProperties) {
		// Combine the main context location with any optional locations
		List<String> contextLocations = CollectionUtils.combine(mojo.getLocation(), mojo.getLocations());

		logConfiguration(mojo, mavenProperties, contextLocations);

		// Assemble any beans we may be injecting
		List<Boolean> includes = Arrays.asList(mojo.isInjectMavenProperties(), mojo.isInjectMavenProject(), mojo.isInjectMavenMojo());
		List<String> beanNames = CollectionUtils.getList(includes, Arrays.asList(mojo.getMavenPropertiesBeanName(), mojo.getMavenProjectBeanName(), mojo.getMavenMojoBeanName()));
		List<Object> beans = CollectionUtils.getList(includes, Arrays.asList(mavenProperties, mojo.getProject(), mojo));

		SpringContext context = new SpringContext();
		context.setLocations(contextLocations);
		context.setBeanNames(beanNames);
		context.setBeans(beans);
		return context;
	}

	protected void logConfiguration(LoadMojo mojo, Properties props, List<String> contextLocations) {
		Log log = mojo.getLog();
		if (mojo.isInjectMavenProperties()) {
			log.info("Injecting " + props.size() + " Maven properties as a [" + props.getClass().getName() + "] bean under the id [" + mojo.getMavenPropertiesBeanName() + "]");
			// log.debug("Displaying " + props.size() + " properties\n\n" + PropertyUtils.toString(props));
		}
		if (mojo.isInjectMavenProject()) {
			log.info("Injecting the Maven project as a [" + mojo.getProject().getClass().getName() + "] bean under the id [" + mojo.getMavenProjectBeanName() + "]");
		}
		if (mojo.isInjectMavenMojo()) {
			log.info("Injecting this mojo as a [" + mojo.getClass().getName() + "] bean under the id [" + mojo.getMavenMojoBeanName() + "]");
		}
		if (contextLocations.size() > 1) {
			log.info("Loading " + contextLocations.size() + " Spring context files");
		}
	}

	public static SpringService getSpringService(String serviceClassname) {
		try {
			Class<?> serviceClass = Class.forName(serviceClassname);
			return (SpringService) serviceClass.newInstance();
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Unexpected error", e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException("Unexpected error", e);
		} catch (InstantiationException e) {
			throw new IllegalStateException("Unexpected error", e);
		}
	}

	protected Properties getMavenProperties(LoadMojo mojo) {
		MavenProject project = mojo.getProject();
		// Get internal Maven config as a properties object
		Properties internal = getInternalProperties(project);
		// The ordering here is significant.
		// Properties supplied directly to the mojo override properties from project.getProperties()
		// But, internal Maven properties need to always win.
		// ${project.artifactId} needs to always faithfully represent the correct artifactId
		Properties properties = PropertyUtils.combine(project.getProperties(), project.getProperties(), internal);
		// Explicitly override internal Maven props with system/env props (simulates the default maven behavior)
		PropertyUtils.overrideWithGlobalValues(properties, GlobalPropertiesMode.BOTH);
		// Return the overridden properties
		return properties;
	}

	protected Properties getInternalProperties(MavenProject project) {
		Properties properties = new Properties();
		properties.setProperty("project.id", project.getId());
		properties.setProperty("project.groupId", project.getGroupId());
		properties.setProperty("project.artifactId", project.getArtifactId());
		properties.setProperty("project.version", project.getVersion());
		properties.setProperty("project.packaging", project.getPackaging());
		properties.setProperty("project.name", project.getName());
		properties.setProperty("project.description", project.getDescription());
		properties.setProperty("project.inceptionYear", project.getInceptionYear());
		properties.setProperty("project.ciManagement.system", project.getCiManagement().getSystem());
		properties.setProperty("project.ciManagement.url", project.getCiManagement().getUrl());
		properties.setProperty("project.issueManagement.system", project.getIssueManagement().getSystem());
		properties.setProperty("project.issueManagement.url", project.getIssueManagement().getUrl());
		properties.setProperty("project.basedir", LocationUtils.getCanonicalPath(project.getBasedir()));
		properties.setProperty("project.build.directory", project.getBuild().getDirectory());
		properties.setProperty("project.build.outputDirectory", project.getBuild().getOutputDirectory());
		properties.setProperty("project.build.testOutputDirectory", project.getBuild().getTestOutputDirectory());
		properties.setProperty("project.build.sourceDirectory", project.getBuild().getSourceDirectory());
		properties.setProperty("project.build.scriptSourceDirectory", project.getBuild().getScriptSourceDirectory());
		properties.setProperty("project.build.testSourceDirectory", project.getBuild().getTestSourceDirectory());
		return properties;
	}
}
