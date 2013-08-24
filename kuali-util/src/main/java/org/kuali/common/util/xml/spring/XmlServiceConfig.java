package org.kuali.common.util.xml.spring;

import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.kuali.common.util.xml.JAXBXmlService;
import org.kuali.common.util.xml.XmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class XmlServiceConfig {

	private static final String FORMAT_OUTPUT_KEY = "jaxb.formatOutput";
	private static final String USE_NAMESPACE_AWARE_PARSER_KEY = "jaxb.useNamespaceAwareParser";

	@Autowired
	EnvironmentService env;

	@Bean
	public XmlService xmlService() {
		boolean formatOutput = env.getBoolean(FORMAT_OUTPUT_KEY, JAXBXmlService.DEFAULT_FORMAT_OUTPUT);
		boolean useNamespaceAwareParser = env.getBoolean(USE_NAMESPACE_AWARE_PARSER_KEY, JAXBXmlService.DEFAULT_USE_NAMESPACE_AWARE_PARSER);
		return new JAXBXmlService(formatOutput, useNamespaceAwareParser);
	}
}
