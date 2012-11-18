package org.kuali.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
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
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.property.PropertyOverwriteMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simplify handling of <code>Properties</code> especially as it relates to storing and loading. <code>Properties</code> can be loaded from
 * any url Spring resource loading can understand. When storing and loading, locations ending in <code>.xml</code> are automatically handled
 * using <code>storeToXML()</code> and <code>loadFromXML()</code>, respectively. <code>Properties</code> are always stored in sorted order.
 */
public class PropertyUtils {

	private static final Logger logger = LoggerFactory.getLogger(PropertyUtils.class);

	private static final String XML_EXTENSION = ".xml";
	private static final String ENV_PREFIX = "env";
	private static final String PLATFORM_DEFAULT = Charset.defaultCharset().toString();

	/**
	 * Return any keys from the <code>properties</code> passed in that end with <code>suffix</code>.
	 */
	public static final List<String> getEndsWithKeys(Properties properties, String suffix) {
		List<String> matches = new ArrayList<String>();
		List<String> keys = getSortedKeys(properties);
		for (String key : keys) {
			if (StringUtils.endsWith(key, suffix)) {
				matches.add(key);
			}
		}
		return matches;
	}

	/**
	 * Alter the <code>properties</code> passed in so it only contains the desired property values. <code>includes</code> and
	 * <code>excludes</code> are comma separated lists.
	 */
	public static final void trim(Properties properties, String includesCSV, String excludesCSV) {
		List<String> includes = CollectionUtils.getTrimmedListFromCSV(includesCSV);
		List<String> excludes = CollectionUtils.getTrimmedListFromCSV(excludesCSV);
		trim(properties, includes, excludes);
	}

	/**
	 * Alter the <code>properties</code> passed in so it only contains the desired property values.
	 */
	public static final void trim(Properties properties, List<String> includes, List<String> excludes) {
		List<String> keys = getSortedKeys(properties);
		for (String key : keys) {
			boolean include = include(key, includes, excludes);
			if (!include) {
				logger.debug("Removing property [{}]", key);
				properties.remove(key);
			}
		}
	}

	/**
	 * Return true if <code>value</code> should be included, false otherwise.<br>
	 * If <code>excludes</codes> is not empty and contains <code>value</code> return false.<br>
	 * If <code>value</code> has not been explicitly excluded, proceed with checking the <code>includes</code> list.<br>
	 * If <code>includes</code> is empty return true.<br>
	 * If <code>includes</code> is not empty, return true only if <code>value</code> appears in the list.
	 */
	public static final boolean include(String value, List<String> includes, List<String> excludes) {
		if (!CollectionUtils.isEmpty(excludes) && excludes.contains(value)) {
			return false;
		} else {
			return CollectionUtils.isEmpty(includes) || includes.contains(value);
		}
	}

	/**
	 * Return property keys that should be included as a sorted list.
	 */
	public static final List<String> getSortedKeys(Properties properties, List<String> includes, List<String> excludes) {
		List<String> keys = getSortedKeys(properties);
		List<String> includedKeys = new ArrayList<String>();
		for (String key : keys) {
			if (include(key, includes, excludes)) {
				includedKeys.add(key);
			}
		}
		return includedKeys;
	}

	/**
	 * Return the property keys as a sorted list.
	 */
	public static final List<String> getSortedKeys(Properties properties) {
		List<String> keys = new ArrayList<String>(properties.stringPropertyNames());
		Collections.sort(keys);
		return keys;
	}

	/**
	 * Store the properties to the indicated file.
	 */
	public static final void store(Properties properties, File file) {
		store(properties, file, null);
	}

	/**
	 * Store the properties to the indicated file using the indicated encoding.
	 */
	public static final void store(Properties properties, File file, String encoding) {
		store(properties, file, encoding, null);
	}

	/**
	 * Store the properties to the indicated file using the indicated encoding with the indicated comment appearing at the top of the file.
	 */
	public static final void store(Properties properties, File file, String encoding, String comment) {
		OutputStream out = null;
		Writer writer = null;
		try {
			out = FileUtils.openOutputStream(file);
			String path = file.getCanonicalPath();
			boolean xml = isXml(path);
			Properties sorted = getSortedProperties(properties);
			comment = getComment(encoding, comment);
			if (xml) {
				logger.info("Storing XML properties - [{}] encoding={}", path, StringUtils.defaultIfBlank(encoding, PLATFORM_DEFAULT));
				if (StringUtils.isBlank(encoding)) {
					sorted.storeToXML(out, comment);
				} else {
					sorted.storeToXML(out, comment, encoding);
				}
			} else {
				writer = ResourceUtils.getWriter(out, encoding);
				logger.info("Storing properties - [{}] encoding={}", path, StringUtils.defaultIfBlank(encoding, PLATFORM_DEFAULT));
				sorted.store(writer, comment);
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(writer);
			IOUtils.closeQuietly(out);
		}
	}

	/**
	 * Return a new properties object containing the properties from <code>getEnvAsProperties()</code> and
	 * <code>System.getProperties()</code> . Properties from <code>System.getProperties()</code> override properties from
	 * <code>getEnvAsProperties</code> if there are duplicates.
	 */
	public static final Properties getGlobalProperties() {
		return getGlobalProperties(new Properties());
	}

	/**
	 * Return a new properties object containing the properties passed in, plus any properties returned by <code>getEnvAsProperties()</code>
	 * and <code>System.getProperties()</code>. Properties from <code>getEnvAsProperties()</code> override properties from
	 * <code>original</code> and properties from <code>System.getProperties()</code> override everything.
	 */
	public static final Properties getGlobalProperties(Properties properties) {
		Properties newProperties = duplicate(properties);
		newProperties.putAll(getEnvAsProperties());
		newProperties.putAll(System.getProperties());
		return newProperties;
	}

	/**
	 * Convert the <code>Map</code> to a <code>Properties</code> object.
	 */
	public static final Properties convert(Map<String, String> map) {
		Properties props = new Properties();
		for (String key : map.keySet()) {
			String value = map.get(key);
			props.setProperty(key, value);
		}
		return props;
	}

	/**
	 * Return a new properties object that duplicates the properties passed in.
	 */
	public static final Properties duplicate(Properties properties) {
		Properties newProperties = new Properties();
		newProperties.putAll(properties);
		return newProperties;
	}

	/**
	 * Return a new properties object containing environment variables as properties prefixed with <code>env</code>
	 */
	public static Properties getEnvAsProperties() {
		return getEnvAsProperties(ENV_PREFIX);
	}

	/**
	 * Return a new properties object containing environment variables as properties prefixed with <code>prefix</code>
	 */
	public static Properties getEnvAsProperties(String prefix) {
		Properties properties = convert(System.getenv());
		return getPrefixedProperties(properties, prefix);
	}

	/**
	 * Return true if location ends with <code>.xml</code> (case insensitive).
	 */
	public static final boolean isXml(String location) {
		return StringUtils.endsWithIgnoreCase(location, XML_EXTENSION);
	}

	/**
	 * Return a new <code>Properties</code> object loaded from <code>location</code>.
	 */
	public static final Properties load(String location) {
		return load(location, null);
	}

	/**
	 * Return a new <code>Properties</code> object loaded from <code>location</code> using <code>encoding</code>.
	 */
	public static final Properties load(String location, String encoding) {
		InputStream in = null;
		Reader reader = null;
		try {
			Properties properties = new Properties();
			boolean xml = isXml(location);
			if (xml) {
				in = ResourceUtils.getInputStream(location);
				logger.info("Loading XML properties - [{}]", location);
				properties.loadFromXML(in);
			} else {
				logger.info("Loading properties - [{}] encoding={}", location, StringUtils.defaultIfBlank(encoding, PLATFORM_DEFAULT));
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

	/**
	 * Return a new properties object containing properties prefixed using the indicated prefix. If prefix is blank, the new properties
	 * object will duplicate the properties passed in.
	 */
	public static final Properties getPrefixedProperties(Properties properties, String prefix) {
		if (StringUtils.isBlank(prefix)) {
			return duplicate(properties);
		}
		Properties newProperties = new Properties();
		for (String key : properties.stringPropertyNames()) {
			String value = properties.getProperty(key);
			String newKey = prefix + "." + key;
			newProperties.setProperty(newKey, value);
		}
		return newProperties;
	}

	/**
	 * Return a new properties object where the keys have been converted to upper case and periods have been replaced with an underscore.
	 */
	public static final Properties getPropertiesAsEnvironmentVariables(Properties properties) {
		Properties newProperties = new Properties();
		for (String key : properties.stringPropertyNames()) {
			String value = properties.getProperty(key);
			String newKey = key.toUpperCase().replace(".", "_");
			newProperties.setProperty(newKey, value);
		}
		return newProperties;
	}

	/**
	 * Check <code>properties</code> to see if it has a value for <code>key</code>. If there is no existing value or <code>mode</code> is
	 * <code>IGNORE</code>, silently return. If there is a value and <code>mode</code> is <code>ERROR</code> throw
	 * <code>IllegalStateException</code>, otherwise log a <code>DEBUG</code>, <code>INFO</code>, or <code>WARN</code> message.
	 */
	public static final void checkExistingProperty(Properties properties, String key, PropertyOverwriteMode mode) {
		if (!properties.contains(key)) {
			return;
		}
		switch (mode) {
		case IGNORE:
			return;
		case DEBUG:
			logger.debug("Overwriting existing property [{}]", key);
			return;
		case INFORM:
			logger.info("Overwriting existing property [{}]", key);
			return;
		case WARN:
			logger.warn("Overwriting existing property [{}]", key);
			return;
		case ERROR:
			throw new IllegalStateException("Overwrite of existing property [" + key + "] is not allowed.");
		default:
			throw new IllegalArgumentException(mode + " is unknown");
		}
	}

	private static final String getDefaultComment(String encoding) {
		StringBuilder sb = new StringBuilder();
		sb.append("Encoding=");
		if (StringUtils.isBlank(encoding)) {
			sb.append(PLATFORM_DEFAULT);
		} else {
			sb.append(encoding);
		}
		return sb.toString();
	}

	private static final String getComment(String encoding, String comment) {
		if (StringUtils.isBlank(comment)) {
			return getDefaultComment(encoding);
		} else {
			return comment + "\n#" + getDefaultComment(encoding);
		}
	}

	/**
	 * This is private because <code>SortedProperties</code> does not fully honor the contract for <code>Properties</code>
	 */
	private static final SortedProperties getSortedProperties(Properties properties) {
		SortedProperties sp = new PropertyUtils().new SortedProperties();
		sp.putAll(properties);
		return sp;
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
