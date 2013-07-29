package org.kuali.common.util.log4j;

import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.kuali.common.util.log4j.model.Log4JContext;
import org.w3c.dom.Element;

public class DefaultLog4JService implements Log4JService {

	@Override
	public void reset() {
		LogManager.resetConfiguration();
	}

	@Override
	public void configure(Properties properties) {
		PropertyConfigurator.configure(properties);
	}

	@Override
	public void configure(Log4JContext context) {
		// Do something smart
	}

	@Override
	public void configure(Element element) {
		DOMConfigurator.configure(element);
	}

	protected Properties getProperties(Log4JContext context) {
		Properties properties = new Properties();
		return properties;
	}
}
