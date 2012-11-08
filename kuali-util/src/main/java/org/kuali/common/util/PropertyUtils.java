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
import org.kuali.common.util.property.DefaultPropertyLoadingContext;
import org.kuali.common.util.property.DefaultPropertyStorageContext;
import org.kuali.common.util.property.PropertyHandlingContext;
import org.kuali.common.util.property.PropertyLoadingContext;
import org.kuali.common.util.property.PropertyStorageContext;
import org.kuali.common.util.property.PropertyStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 * Simplify handling of <code>Properties</code> especially as it relates to storing and loading. <code>Properties</code> can be loaded from
 * any url Spring resource loading can understand. When storing and loading, locations ending in <code>.xml</code> are automatically handled
 * using <code>storeToXML()</code> and <code>loadFromXML()</code>, respectively. <code>Properties</code> are stored in sorted order by
 * default.
 */
public class PropertyUtils {

	private static final Logger logger = LoggerFactory.getLogger(PropertyUtils.class);

	private static final String XML_EXTENSION = ".xml";
	private static final String ENV_PREFIX = "env";
	private static final String DEFAULT = "DEFAULT";

	public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";
	public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

	public static final Properties getResolvedProperties(Properties props) {
		return getResolvedProperties(props, DEFAULT_PLACEHOLDER_PREFIX, DEFAULT_PLACEHOLDER_SUFFIX);
	}

	public static final Properties getResolvedProperties(Properties props, String placeHolderPrefix, String placeHolderSuffix) {
		Properties global = getGlobalProperties(props);
		PropertyPlaceholderHelper pph = new PropertyPlaceholderHelper(placeHolderPrefix, placeHolderSuffix);
		List<String> keys = getSortedKeys(props);
		Properties newProps = new Properties();
		for (String key : keys) {
			String originalValue = props.getProperty(key);
			String resolvedValue = pph.replacePlaceholders(originalValue, global);
			if (!resolvedValue.equals(originalValue)) {
				logger.debug("Resolved property '" + key + "' [{}] -> [{}]", Str.flatten(originalValue), Str.flatten(resolvedValue));
			}
			newProps.setProperty(key, resolvedValue);
		}
		return newProps;
	}

	public static final void trim(Properties properties, String includes, String excludes) {
		List<String> includeList = CollectionUtils.getTrimmedListFromCSV(includes);
		List<String> excludeList = CollectionUtils.getTrimmedListFromCSV(excludes);
		trim(properties, includeList, excludeList);
	}

	public static final void trim(Properties properties, List<String> includes, List<String> excludes) {
		List<String> keys = getSortedKeys(properties);
		for (String key : keys) {
			boolean include = include(key, includes, excludes);
			if (!include) {
				logger.info("Removing property [{}]", key);
				properties.remove(key);
			}
		}
	}

	public static final boolean include(String value, List<String> includes, List<String> excludes) {
		if (!CollectionUtils.isEmpty(excludes) && excludes.contains(value)) {
			return false;
		} else {
			return CollectionUtils.isEmpty(includes) || includes.contains(value);
		}
	}

	public static final List<String> getSortedKeys(Properties properties) {
		List<String> keys = new ArrayList<String>(properties.stringPropertyNames());
		Collections.sort(keys);
		return keys;

	}

	public static final String getResolvedValue(String value, Properties properties) {
		return getResolvedValue(value, properties, DEFAULT_PLACEHOLDER_PREFIX, DEFAULT_PLACEHOLDER_SUFFIX);
	}

	public static final String getResolvedValue(String value, Properties properties, String placeHolderPrefix, String placeHolderSuffix) {
		PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(placeHolderPrefix, placeHolderSuffix);
		return helper.replacePlaceholders(value, properties);
	}

	public static final Properties getProperties(List<String> locations) {
		return getProperties(locations, null);
	}

	public static final Properties getProperties(List<String> locations, String encoding) {
		DefaultPropertyLoadingContext context = new DefaultPropertyLoadingContext();
		context.setLocations(locations);
		context.setEncoding(encoding);
		return getProperties(context);
	}

	public static final void store(Properties properties, File file) {
		store(properties, file, null);
	}

	public static final void store(Properties properties, File file, String encoding) {
		store(properties, file, encoding, null, PropertyStyle.NORMAL, true, null);
	}

	public static final void store(Properties properties, File file, String encoding, PropertyStyle style) {
		store(properties, file, encoding, null, style, true, null);
	}

	public static final void store(Properties properties, File file, String encoding, String prefix, PropertyStyle style, boolean sort, String comment) {
		DefaultPropertyStorageContext context = new DefaultPropertyStorageContext();
		context.setFile(file);
		context.setEncoding(encoding);
		context.setPrefix(prefix);
		context.setStyle(style);
		context.setSort(sort);
		context.setComment(comment);
		store(context, properties);
	}

	public static final void store(PropertyStorageContext context, Properties properties) {
		Properties finalProperties = getProperties(context, properties);
		Properties sortedProperties = context.isSort() ? getSortedProperties(finalProperties) : finalProperties;
		storeProperties(context, sortedProperties);
	}

	public static final void storeProperties(PropertyStorageContext context, Properties properties) {
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
	 * Return a properties object containing the properties passed in, plus any properties returned by <code>getEnvAsProperties()</code> and
	 * <code>System.getProperties()</code>. Properties from <code>getEnvAsProperties()</code> override properties from <code>original</code>
	 * and properties from <code>System.getProperties()</code> override everything.
	 */
	public static final Properties getGlobalProperties(Properties original) {
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

	public static final Properties load(PropertyLoadingContext context) {
		String prefix = context.getPlaceHolderPrefix();
		String suffix = context.getPlaceHolderSuffix();
		Properties props = new Properties();
		for (String location : context.getLocations()) {
			Properties global = getGlobalProperties(props);
			String resolvedLocation = getResolvedValue(location, global, prefix, suffix);
			if (!location.equals(resolvedLocation)) {
				logger.info("Resolved location [{}] -> [{}]", location, resolvedLocation);
			}
			boolean missing = !ResourceUtils.exists(resolvedLocation);
			if (missing && context.isIgnoreMissingLocations()) {
				logger.info("Skipping non-existent location - [{}]", resolvedLocation);
				continue;
			} else {
				props.putAll(load(resolvedLocation, context.getEncoding()));
			}
		}
		return props;
	}

	public static final Properties getProperties(PropertyLoadingContext context) {
		// Load properties in from the specified locations
		Properties props = load(context);
		// Process the properties according to the options provided in the context
		return getProperties(context, props);
	}

	public static final Properties getProperties(PropertyHandlingContext context, Properties props) {

		// Add in environment variables?
		if (context.isIncludeEnvironmentVariables()) {
			props.putAll(PropertyUtils.getEnvAsProperties());
		}

		// Add in system properties?
		if (context.isIncludeSystemProperties()) {
			props.putAll(System.getProperties());
		}

		// Resolve placeholders?
		if (context.isResolvePlaceholders()) {
			props = getResolvedProperties(props, context.getPlaceHolderPrefix(), context.getPlaceHolderSuffix());
		}

		// Trim out unwanted properties
		trim(props, context.getInclude(), context.getExclude());

		// Add in a prefix if asked to do so
		Properties prefixed = getPrefixedProperties(props, context.getPrefix());

		// Format the property keys according to the style they've asked for and return
		return getFormattedProperties(prefixed, context.getStyle());
	}

	public static final Properties getProperties(String location) {
		return getProperties(location, null);
	}

	public static final Properties getProperties(String location, String encoding) {
		return getProperties(Collections.singletonList(location), encoding);
	}

	public static final boolean isXml(String location) {
		return location.endsWith(XML_EXTENSION);
	}

	public static final Properties load(String location, String encoding) {
		Properties properties = new Properties();
		load(properties, location, encoding);
		return properties;
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
	 * This is private because <code>SortedProperties</code> does not fully honor the contract for <code>Properties</code>
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

	public static final Properties getFormattedProperties(Properties properties, PropertyStyle style) {
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
