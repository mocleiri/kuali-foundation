package org.kuali.common.util.log4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.log4j.model.Log4JContext;
import org.kuali.common.util.xml.XmlService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class DefaultLog4JService implements Log4JService {

	protected static final String ENCODING = "UTF-8";
	protected static final String PROPERTIES_SUFFIX = ".properties";
	protected static final String XML_SUFFIX = ".xml";
	protected static final String UNSUPPORTED_LOCATION_TYPE = "Only " + PROPERTIES_SUFFIX + " and " + XML_SUFFIX + " locations are supported";

	XmlService xmlService;

	@Override
	public void reset() {
		LogManager.resetConfiguration();
	}

	@Override
	public void configure(String location) {

		// Make sure the location exists
		Assert.isTrue(LocationUtils.exists(location), "[" + location + "] does not exist");

		// Make sure it is either a .properties or .xml
		boolean properties = StringUtils.endsWithIgnoreCase(location, PROPERTIES_SUFFIX);
		boolean xml = StringUtils.endsWithIgnoreCase(location, XML_SUFFIX);
		Assert.isTrue(properties || xml, UNSUPPORTED_LOCATION_TYPE);

		if (properties) {
			configure(PropertyUtils.load(location, ENCODING));
		} else if (xml) {
			configureFromXmlLocation(location);
		} else {
			// Should never get here since the earlier assertions ensure it is either .xml or .properties
			throw new IllegalArgumentException(UNSUPPORTED_LOCATION_TYPE);
		}
	}

	protected void configureFromXmlLocation(String location) {
		InputStream in = null;
		try {
			in = LocationUtils.getInputStream(location);
			Document document = getDocument(in);
			DOMConfigurator.configure(document.getDocumentElement());
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	@Override
	public void configure(Properties properties) {
		PropertyConfigurator.configure(properties);
	}

	@Override
	public void configure(Log4JContext context) {
		String xml = getXml(context);
		Document document = getDocument(xml);
		DOMConfigurator.configure(document.getDocumentElement());
	}

	protected Document getDocument(InputStream in) throws IOException, SAXException, ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder parser = dbf.newDocumentBuilder();
		return parser.parse(in);
	}

	protected Document getDocument(String xml) {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(xml.getBytes(ENCODING));
			return getDocument(in);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public String getXml(Log4JContext context) {
		Log4JContext clone = new Log4JContext(context);
		new Log4JContextNullifier(clone).nullify();
		return xmlService.toString(clone, ENCODING);
	}

	@Override
	public void configure(Element element) {
		DOMConfigurator.configure(element);
	}

	public XmlService getXmlService() {
		return xmlService;
	}

	public void setXmlService(XmlService xmlService) {
		this.xmlService = xmlService;
	}

}
