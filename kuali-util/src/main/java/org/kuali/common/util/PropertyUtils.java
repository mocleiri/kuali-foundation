package org.kuali.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyUtils {
	private static final Logger logger = LoggerFactory.getLogger(PropertyUtils.class);

	private static final String XML_EXTENSION = ".xml";
	private static final String ENV_PREFIX = "env";

	/**
	 * <code>System.getenv()</code> overrides properties from <code>original</code> and <code>System.getProperties()</code> overrides
	 * everything
	 */
	public Properties getOverriddenProperties(Properties original) {
		Properties properties = new Properties();
		properties.putAll(original);
		properties.putAll(getEnvAsProperties());
		properties.putAll(System.getProperties());
		return properties;
	}

	/**
	 * Return environment variables as properties prefixed with <code>env</code>
	 */
	public static Properties getEnvAsProperties() {
		Map<String, String> map = System.getenv();
		Properties props = new Properties();
		for (String key : map.keySet()) {
			String newKey = ENV_PREFIX + "." + key;
			String value = map.get(key);
			props.setProperty(newKey, value);
		}
		return props;
	}

	public static final Properties getProperties(String location) {
		return getProperties(location, null);
	}

	public static final Properties getProperties(String location, String encoding) {
		Properties properties = new Properties();
		boolean isXml = location.endsWith(XML_EXTENSION);
		load(properties, isXml, location, encoding);
		return properties;
	}

	public static final void load(Properties properties, boolean isXml, String location, String encoding) {
		InputStream in = null;
		Reader reader = null;
		try {
			if (isXml) {
				in = ResourceUtils.getInputStream(location);
				logger.info("Loading XML properties - [{}]", location);
				properties.loadFromXML(in);
			} else {
				logger.info("Loading properties - [{}] encoding={}", location, encoding);
				reader = ResourceUtils.getBufferedReader(location, encoding);
				properties.load(reader);
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(reader);
		}
	}
}
