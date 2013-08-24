package org.kuali.common.util.xml.spring;

import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.kuali.common.util.xml.jaxb.JAXBXmlService;
import org.kuali.common.util.xml.service.XmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class XmlServiceConfig {

	private static final String FORMAT_OUTPUT_KEY = "jaxb.formatOutput";
	private static final String USE_NAMESPACE_AWARE_PARSER_KEY = "jaxb.useNamespaceAwareParser";
	private static final String USE_ECLIPSE_LINK_MOXY_PROVIDER_KEY = "jaxb.useEclipseLinkMoxyProvider";

	@Autowired
	EnvironmentService env;

	@Bean
	public XmlService xmlService() {
		boolean formatOutput = env.getBoolean(FORMAT_OUTPUT_KEY, JAXBXmlService.Builder.FORMAT_OUTPUT);
		boolean nap = env.getBoolean(USE_NAMESPACE_AWARE_PARSER_KEY, JAXBXmlService.Builder.USE_NAMESPACE_AWARE_PARSER);
		boolean elmp = env.getBoolean(USE_ECLIPSE_LINK_MOXY_PROVIDER_KEY, JAXBXmlService.Builder.USE_ECLIPSE_LINK_MOXY_PROVIDER);
		return new JAXBXmlService.Builder().formatOutput(formatOutput).useNamespaceAwareParser(nap).useEclipseLinkMoxyProvider(elmp).build();
	}
}
