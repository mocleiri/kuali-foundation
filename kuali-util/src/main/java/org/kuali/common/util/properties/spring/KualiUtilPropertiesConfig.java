package org.kuali.common.util.properties.spring;

import java.util.Arrays;

import org.kuali.common.util.Str;
import org.kuali.common.util.project.KualiUtilProjectConstants;
import org.kuali.common.util.project.ProjectIdentifier;
import org.kuali.common.util.properties.PropertiesContext;
import org.kuali.common.util.properties.PropertiesLocation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KualiUtilPropertiesConfig {

	ProjectIdentifier identifier = KualiUtilProjectConstants.PROJECT_IDENTIFIER;
	String projectId = identifier.getGroupId() + ":" + identifier.getArtifactId();
	String classpathPrefix = "classpath:" + Str.getPath(identifier.getGroupId()) + "/" + identifier.getArtifactId();

	@Bean
	public PropertiesContext propertiesContext() {
		PropertiesContext context = new PropertiesContext();
		return context;
	}

	protected PropertiesContext getScm() {
		PropertiesContext context = new PropertiesContext();
		context.setId(projectId + ":scm");
		context.setLocations(Arrays.asList(new PropertiesLocation(classpathPrefix + "/scm.properties")));
		return context;
	}

}
