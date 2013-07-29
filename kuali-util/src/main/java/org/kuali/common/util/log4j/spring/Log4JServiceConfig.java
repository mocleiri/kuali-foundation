package org.kuali.common.util.log4j.spring;

import org.kuali.common.util.log4j.DefaultLog4JService;
import org.kuali.common.util.log4j.Log4JService;
import org.kuali.common.util.xml.spring.XmlServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ XmlServiceConfig.class })
public class Log4JServiceConfig {

	@Autowired
	XmlServiceConfig xmlServiceConfig;

	@Bean
	public Log4JService log4jService() {
		DefaultLog4JService service = new DefaultLog4JService();
		service.setXmlService(xmlServiceConfig.xmlService());
		return service;
	}

}
