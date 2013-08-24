package org.kuali.common.util.xml.spring;

import org.kuali.common.util.xml.JAXBXmlService;
import org.kuali.common.util.xml.XmlService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * The XML for log4j contains element and attribute names that have the colon character as part of the name.
 * 
 * <pre>
 * &lt;log4j:configuration  xmlns:log4j="">
 * &lt;/log4j:configuration>
 * </pre>
 * 
 * </p>
 * 
 * <p>
 * A colon is almost universally used to represent a namespace prefix, but in the XML for log4j, the colon is part of the node name.
 * </p>
 */
@Configuration
public class Log4JXmlServiceConfig {

	@Bean
	public XmlService xmlService() {
		return new JAXBXmlService(false);
	}
}
