package org.kuali.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.common.util.property.PropertyStorageContext;
import org.kuali.common.util.property.PropertyStorageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 * Simplify handling of <code>Properties</code> especially as it relates to storing and loading. <code>Properties</code> can be loaded from
 * any url Spring resource loading can understand. By default, <code>Properties</code> are stored in sorted order. For both storing and
 * loading, locations ending in <code>.xml</code> are automatically handled using <code>storeToXML()</code> and <code>loadFromXML()</code>.
 */
public class PropertyUtils {

	private static final Logger logger = LoggerFactory.getLogger(PropertyUtils.class);

	private static final String XML_EXTENSION = ".xml";
	private static final String ENV_PREFIX = "env";
	private static final String DEFAULT = "DEFAULT";

	private static final String DEFAULT_PREFIX = "${";
	private static final String DEFAULT_SUFFIX = "}";

	public static final void trim(Properties properties, String includes, String excludes) {
		List<String> includeList = CollectionUtils.getListFromCSV(includes);
		List<String> excludeList = CollectionUtils.getListFromCSV(excludes);
		List<String> keys = getSortedKeys(properties);
		for (String key : keys) {
			boolean include = include(key, includeList, excludeList);
			if (!include) {
				logger.info("Removing [{}]", key);
				properties.remove(key);
			}
		}
	}

	public static final boolean include(String value, List<String> includes, List<String> excludes) {
		if (excludes.contains(value)) {
			return false;
		} else {
			return includes.size() == 0 || includes.contains(value);
		}
	}

	public static final List<String> getSortedKeys(Properties properties) {
		List<String> keys = new ArrayList<String>(properties.stringPropertyNames());
		Collections.sort(keys);
		return keys;

	}

	public static final String getResolvedValue(String value, Properties properties) {
		return getResolvedValue(value, properties, DEFAULT_PREFIX, DEFAULT_SUFFIX);
	}

	public static final String getResolvedValue(String value, Properties properties, String prefix, String suffix) {
		PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(prefix, suffix);
		return helper.replacePlaceholders(value, properties);
	}

	public static final Properties getProperties(List<String> locations) {
		return getProperties(locations, null);
	}

	public static final Properties getProperties(List<String> locations, String encoding) {
		Properties props = new Properties();
		for (String location : locations) {
			Properties overriddenProperties = getOverriddenProperties(props);
			String resolvedLocation = getResolvedValue(location, overriddenProperties);
			if (!location.equals(resolvedLocation)) {
				logger.info("Resolved location [{}] -> [{}]", location, resolvedLocation);
			}
			props.putAll(getProperties(resolvedLocation, encoding));
		}
		return props;
	}

	public static final void store(Properties properties, File file) {
		store(properties, file, null);
	}

	public static final void store(Properties properties, File file, String encoding) {
		store(properties, file, encoding, null, PropertyStorageStyle.NORMAL, true, null);
	}

	public static final void store(Properties properties, File file, String encoding, PropertyStorageStyle style) {
		store(properties, file, encoding, null, style, true, null);
	}

	public static final void store(Properties properties, File file, String encoding, String prefix, PropertyStorageStyle style, boolean sort, String comment) {
		PropertyStorageContext context = new PropertyStorageContext();
		context.setFile(file);
		context.setEncoding(encoding);
		context.setPrefix(prefix);
		context.setStyle(style);
		context.setSort(sort);
		context.setComment(comment);
		store(context, properties);
	}

	public static final void store(PropertyStorageContext context, Properties properties) {
		Properties prefixed = getPrefixedProperties(properties, context.getPrefix());
		Properties formatted = getFormattedProperties(prefixed, context.getStyle());
		Properties finalProperties = (context.isSort()) ? getSortedProperties(formatted) : formatted;
		storeProperties(context, finalProperties);
	}

	protected static final void storeProperties(PropertyStorageContext context, Properties properties) {
		OutputStream out = null;
		Writer writer = null;
		try {
			out = FileUtils.openOutputStream(context.getFile());
			String path = context.getFile().getCanonicalPath();
			boolean xml = isXml(path);
			if (xml) {
				logger.info("Storing XML properties - [{}] encoding={}", path, Str.toDefault(context.getEncoding(), DEFAULT));
				if (StringUtils.isBlank(context.getEncoding())) {
					properties.storeToXML(out, context.getComment());
				} else {
					properties.storeToXML(out, context.getComment(), context.getEncoding());
				}
			} else {
				writer = ResourceUtils.getWriter(out, context.getEncoding());
				logger.info("Storing properties - [{}] encoding={}", path, Str.toDefault(context.getEncoding(), DEFAULT));
				properties.store(writer, context.getComment());
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(writer);
			IOUtils.closeQuietly(out);
		}
	}

	/**
	 * Return a properties object containing the original properties plus the properties returned by <code>getEnvAsProperties()</code> and
	 * <code>System.getProperties()</code>. Properties from <code>getEnvAsProperties()</code> override properties from <code>original</code>
	 * and properties from <code>System.getProperties()</code> override everything.
	 */
	public static final Properties getOverriddenProperties(Properties original) {
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
		load(properties, location, encoding);
		return properties;
	}

	public static final boolean isXml(String location) {
		return location.endsWith(XML_EXTENSION);
	}

	public static final void load(Properties properties, String location, String encoding) {
		InputStream in = null;
		Reader reader = null;
		try {
			boolean xml = isXml(location);
			if (xml) {
				in = ResourceUtils.getInputStream(location);
				logger.info("Loading XML properties - [{}]", location);
				properties.loadFromXML(in);
			} else {
				logger.info("Loading properties - [{}] encoding={}", location, Str.toDefault(encoding, DEFAULT));
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

	/**
	 * Keep this private since <code>SortedProperties</code> does not fully honor the contract for <code>Properties</code>
	 */
	private static final SortedProperties getSortedProperties(Properties properties) {
		SortedProperties sp = new PropertyUtils().new SortedProperties();
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

	/**
	 * This is private since it does not honor the full contract for <code>Properties</code>. <code>PropertyUtils</code> uses it internally
	 * to store properties in sorted order.
	 */
	private class SortedProperties extends Properties {

		private static final long serialVersionUID = 1330825236411537386L;

		/**
		 * <code>Properties.storeToXML()</code> uses <code>keySet()</code>
		 */
		@Override
		public Set<Object> keySet() {
			return Collections.unmodifiableSet(new TreeSet<Object>(super.keySet()));
		}

		/**
		 * <code>Properties.store()</code> uses <code>keys()</code>
		 */
		@Override
		public synchronized Enumeration<Object> keys() {
			return Collections.enumeration(new TreeSet<Object>(super.keySet()));
		}

	}

}
