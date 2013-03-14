package org.kuali.maven.plugins.spring;

import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;
import org.kuali.maven.plugins.spring.config.PropertySourcesConfig;

public final class MavenConstants {

	public static final String LOAD_MOJO = "load";
	public static final String LOAD_XML_MOJO = "loadxml";
	public static final String DEFAULT_MAVEN_PROPERTIES_BEAN_NAME = "mavenProperties";
	public static final String DEFAULT_MAVEN_PROJECT_BEAN_NAME = "mavenProject";
	public static final String DEFAULT_MAVEN_MOJO_BEAN_NAME = "mavenMojo";
	public static final String DEFAULT_ADD_PROPERTY_SOURCES = "true";
	public static final String DEFAULT_INJECT_MAVEN_PROPERTIES = "true";
	public static final String DEFAULT_INJECT_MAVEN_PROJECT = "false";
	public static final String DEFAULT_INJECT_MAVEN_MOJO = "false";
	public static final String DEFAULT_FORCE_MOJO_EXECUTION = "false";
	public static final String DEFAULT_SKIP = "false";
	public static final Class<? extends SpringService> DEFAULT_SERVICE_CLASS = DefaultSpringService.class;
	public static final Class<?> DEFAULT_PROPERTY_SOURCES_CONFIG = PropertySourcesConfig.class;
	public static final String DEFAULT_PROPERTY_SOURCES_LOCATION = "classpath:org/kuali/maven/plugins/spring/property-sources.xml";

}
