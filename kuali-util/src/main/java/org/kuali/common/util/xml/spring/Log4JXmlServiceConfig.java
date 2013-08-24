package org.kuali.common.util.xml.spring;

import org.kuali.common.util.xml.JAXBXmlService;
import org.kuali.common.util.xml.XmlService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * The XML for log4j contains both elements and attributes that have the colon character as part of the name. A colon is almost universally used to represent a namespace prefix,
 * but in the XML for log4j, the colon is part of the node name. This config class must return an XML service that can correctly parse XML that uses the colon character in
 * attribute and element names.
 * 
 * <pre>
 * &lt;log4j:configuration  xmlns:log4j="http://jakarta.apache.org/log4j/">
 * &lt;/log4j:configuration>
 * </pre>
 * 
 * </p>
 * 
 * <p>
 * The default technique being used at Kuali is to leverage a SAX parser that is not namespace aware. This is described in more detail on Blaise Doughan's blog -
 * https://plus.google.com/110628352733714681176/posts
 * </p>
 */
@Configuration
public class Log4JXmlServiceConfig {

	@Bean
	public XmlService xmlService() {
		return new JAXBXmlService(false);
	}
}
