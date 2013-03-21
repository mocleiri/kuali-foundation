package org.kuali.common.util.spring;

import java.util.Properties;

import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.property.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.PropertyPlaceholderHelper;

@Configuration
public class GenerateProjectSourceFileConfig {

	@Autowired
	@Qualifier(Constants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME)
	Properties mavenProperties;

	@Bean
	public Object foo() {
		String template = ProjectUtils.getJavaSourceFileTemplate();
		PropertyPlaceholderHelper pph = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
		String source = pph.replacePlaceholders(template, mavenProperties);
		return null;
	}

}
