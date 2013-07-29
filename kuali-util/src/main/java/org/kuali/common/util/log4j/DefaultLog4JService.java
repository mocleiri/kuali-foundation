package org.kuali.common.util.log4j;

import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.kuali.common.util.log4j.model.Log4JContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultDocument;

public class DefaultLog4JService implements Log4JService {

	public static final String RESET_KEY = "log4.reset";
	public static final String THRESHOLD_KEY = "log4.threshold";

	@Override
	public void reset() {
		LogManager.resetConfiguration();
	}

	@Override
	public void configure(String location) {
		// Do something here
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

	protected Element getElement(Log4JContext context) {
		Document document = new DefaultDocument();
		return document.getDocumentElement();
	}

}
