package org.kuali.common.util.log.log4j;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.kuali.common.util.Assert;
import org.kuali.common.util.Encodings;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.log.log4j.model.Log4JConfiguration;
import org.kuali.common.util.xml.service.XmlService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public final class DefaultLog4JService implements Log4JService {

	// ASCII should actually be good enough, log4j config files shouldn't contain special characters
	private static final String ENCODING = Encodings.UTF8;
	private static final String PROPERTIES_SUFFIX = ".properties";
	private static final String XML_SUFFIX = ".xml";
	private static final String UNSUPPORTED_LOCATION_TYPE = "Only " + PROPERTIES_SUFFIX + " and " + XML_SUFFIX + " locations are supported";

	private final XmlService service;

	public DefaultLog4JService(XmlService service) {
		Assert.noNulls(service);
		this.service = service;
	}

	@Override
	public void configure(Log4JConfiguration config) {
		String xml = toXml(config);
		Document document = getDocument(xml);
		configure(document);
	}

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
			// Should never get here since the earlier assertions guarantee it is either .xml or .properties
			throw new IllegalArgumentException(UNSUPPORTED_LOCATION_TYPE);
		}
	}

	@Override
	public String toXml(Log4JConfiguration config) {
		return service.toXml(config, ENCODING);
	}

	@Override
	public void configure(Element element) {
		DOMConfigurator.configure(element);
	}

	@Override
	public void configure(Properties properties) {
		PropertyConfigurator.configure(properties);
	}

	@Override
	public void write(File file, Log4JConfiguration config) {
		OutputStream out = null;
		try {
			String xml = toXml(config);
			out = FileUtils.openOutputStream(file);
			IOUtils.write(xml, out, ENCODING);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	protected void configure(Document document) {
		DOMConfigurator.configure(document.getDocumentElement());
	}

	protected void configureFromXmlLocation(String location) {
		InputStream in = null;
		try {
			in = LocationUtils.getInputStream(location);
			Document document = getDocument(in);
			configure(document);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
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

	public XmlService getXmlService() {
		return service;
	}

}
