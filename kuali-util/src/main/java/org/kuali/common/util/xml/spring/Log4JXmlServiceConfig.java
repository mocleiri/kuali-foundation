package org.kuali.common.util.xml.spring;

import org.kuali.common.util.xml.Log4JXmlService;
import org.kuali.common.util.xml.XmlService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Log4JXmlServiceConfig {

	@Bean
	public XmlService xmlService() {
		return new Log4JXmlService();
	}
}
