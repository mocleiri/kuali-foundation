package org.kuali.common.util.xml.spring;

import org.kuali.common.util.xml.JAXBXmlService;
import org.kuali.common.util.xml.XmlService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * The XML for log4j contains both an element and an attribute that have the colon character in their name. A colon is almost universally used to represent a namespace prefix, but
 * in the XML for log4j, the colon is part of the node name. This config class must return an XML service that can correctly parse XML that uses the colon character in attribute
 * and element names.
 * 
 * <pre>
 * &lt;log4j:configuration  xmlns:log4j="http://jakarta.apache.org/log4j/">
 * &lt;/log4j:configuration>
 * </pre>
 * 
 * </p>
 * 
 * <p>
 * The technique being used for this at Kuali is to leverage a SAX parser that is not namespace aware (and thus treats the colon character just like any other character). This is
 * described in more detail on Blaise Doughan's blog - http://blog.bdoughan.com/2011/05/jaxb-and-dtd.html
 * </p>
 */
@Configuration
public class Log4JXmlServiceConfig {

	@Bean
	public XmlService xmlService() {
		return new JAXBXmlService.Builder().useNamespaceAwareParser(false).build();
	}
}
