package org.kuali.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.common.util.property.PropertyStorageStyle;
import org.kuali.common.util.property.SortedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyUtils {
	private static final Logger logger = LoggerFactory.getLogger(PropertyUtils.class);

	private static final String XML_EXTENSION = ".xml";
	private static final String ENV_PREFIX = "env";

	public static final Properties getFormattedProperties(Properties properties, PropertyStorageStyle style) {
		switch (style) {
		case NORMAL:
			return properties;
		case ENVIRONMENT_VARIABLE:
			return getPropertiesAsEnvironmentVariables(properties);
		default:
			throw new IllegalArgumentException(style + " is unknown");
		}
	}

	public static final void store(Properties properties, File file) {
		store(properties, file, null);
	}

	public static final void store(Properties properties, File file, String encoding) {
		store(properties, file, encoding, null, PropertyStorageStyle.NORMAL, true, null);
	}

	public static final void store(Properties properties, File file, String encoding, String prefix, PropertyStorageStyle style, boolean sort, String comment) {
		boolean xml = isXml(file.getAbsolutePath());
		Properties prefixed = getPrefixedProperties(properties, prefix);
		Properties formatted = getFormattedProperties(prefixed, style);
		Properties finalProperties = (sort) ? getSortedProperties(formatted) : formatted;
		OutputStream out = null;
		Writer writer = null;
		try {
			out = FileUtils.openOutputStream(file);
			if (xml) {
				finalProperties.storeToXML(out, comment, encoding);
			} else {
				writer = ResourceUtils.getWriter(out, encoding);
				finalProperties.store(writer, comment);
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(writer);
		}
	}

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
		boolean xml = isXml(location);
		load(properties, xml, location, encoding);
		return properties;
	}

	public static final boolean isXml(String location) {
		return location.endsWith(XML_EXTENSION);
	}

	public static final void load(Properties properties, boolean xml, String location, String encoding) {
		InputStream in = null;
		Reader reader = null;
		try {
			if (xml) {
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

	public static final SortedProperties getSortedProperties(Properties properties) {
		SortedProperties sp = new SortedProperties();
		sp.putAll(properties);
		return sp;
	}

	public static final Properties getPrefixedProperties(Properties properties, String prefix) {
		if (StringUtils.isBlank(prefix)) {
			return properties;
		}
		Properties newProperties = new Properties();
		for (String key : properties.stringPropertyNames()) {
			String value = properties.getProperty(key);
			String newKey = prefix + "." + key;
			newProperties.setProperty(newKey, value);
		}
		return newProperties;
	}

	public static final Properties getPropertiesAsEnvironmentVariables(Properties properties) {
		Properties newProperties = new Properties();
		for (String key : properties.stringPropertyNames()) {
			String value = properties.getProperty(key);
			String newKey = key.toUpperCase().replace(".", "_");
			newProperties.setProperty(newKey, value);
		}
		return newProperties;
	}

}
