package org.kuali.common.util.xml.spring;

import org.kuali.common.util.xml.DefaultXmlService;
import org.kuali.common.util.xml.XmlService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XmlServiceConfig {

	@Bean
	public XmlService xmlService() {
		return new DefaultXmlService();
	}
}
