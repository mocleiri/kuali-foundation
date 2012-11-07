package org.kuali.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyUtils {
	private static final Logger logger = LoggerFactory.getLogger(PropertyUtils.class);

	private static final String XML_EXTENSION = ".xml";

	public static final Properties getProperties(String location) {
		return getProperties(location, null);
	}

	public static final Properties getProperties(String location, String encoding) {
		InputStream in = null;
		Reader reader = null;
		try {
			Properties properties = new Properties();
			boolean xml = location.toLowerCase().endsWith(XML_EXTENSION);
			if (xml) {
				in = ResourceUtils.getInputStream(location);
				logger.info("Loading XML properties - [{}]", location);
				properties.loadFromXML(in);
			} else {
				logger.info("Loading properties, enc:'{}' - [{}]", encoding, location);
				reader = ResourceUtils.getBufferedReader(location, encoding);
				properties.load(reader);
			}
			return properties;
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(reader);
		}

	}
}
