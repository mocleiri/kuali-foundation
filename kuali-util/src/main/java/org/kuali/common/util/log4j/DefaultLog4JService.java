package org.kuali.common.util.log4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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

	public static final String ENCODING = "UTF-8";

	XmlService xmlService;

	@Override
	public void reset() {
		LogManager.resetConfiguration();
	}

	@Override
	public void configure(String location) {
		Assert.isTrue(LocationUtils.exists(location), "[" + location + "] does not exist");
		if (StringUtils.endsWithIgnoreCase(location, ".properties")) {
			configure(PropertyUtils.load(location, ENCODING));
		} else if (StringUtils.endsWithIgnoreCase(location, ".xml")) {
			DOMConfigurator.configure(location);
		} else {
			throw new IllegalArgumentException("Only .properties and .xml files are supported");
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

	protected Document getDocument(String xml) {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(xml.getBytes(ENCODING));
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder parser = dbf.newDocumentBuilder();
			return parser.parse(in);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} catch (SAXException e) {
			throw new IllegalStateException("Unexpected SAX error", e);
		} catch (ParserConfigurationException e) {
			throw new IllegalStateException("Unexpected parser config error", e);
		}
	}

	protected String getXml(Log4JContext context) {
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
