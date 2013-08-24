package org.kuali.common.util.xml.spring;

import org.kuali.common.util.xml.JAXBXmlService;
import org.kuali.common.util.xml.XmlService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The XML log4j knows how to parse has element and attribute names that contain the colon character. A colon is almost universally used to represent a namespace prefix, but in the
 * XML for log4j, the colon is part of the node name.
 */
@Configuration
public class Log4JXmlServiceConfig {

	@Bean
	public XmlService xmlService() {
		return new JAXBXmlService(false);
	}
}
