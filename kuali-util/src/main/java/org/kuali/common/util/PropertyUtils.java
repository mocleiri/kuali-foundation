/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.GlobalPropertiesMode;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.property.PropertiesContext;
import org.kuali.common.util.property.processor.AddPropertiesProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 * Simplify handling of <code>Properties</code> especially as it relates to storing and loading. <code>Properties</code> can be loaded from any url Spring resource loading can
 * understand. When storing and loading, locations ending in <code>.xml</code> are automatically handled using <code>storeToXML()</code> and <code>loadFromXML()</code>,
 * respectively. <code>Properties</code> are always stored in sorted order with the <code>encoding</code> indicated via a comment.
 */
public class PropertyUtils {

	private static final Logger logger = LoggerFactory.getLogger(PropertyUtils.class);

	private static final String XML_EXTENSION = ".xml";
	private static final String ENV_PREFIX = "env";
	private static final String DEFAULT_ENCODING = Charset.defaultCharset().name();
	private static final String DEFAULT_XML_ENCODING = "UTF-8";

	public static Properties load(List<ProjectProperties> pps) {
		Properties properties = new Properties();
		for (ProjectProperties pp : pps) {
			PropertiesContext ctx = pp.getPropertiesContext();
			Properties combined = PropertyUtils.combine(properties, ctx.getProperties());
			ctx.setProperties(combined);
			Properties loaded = PropertyUtils.load(ctx);
			properties.putAll(loaded);
		}
		return properties;
	}

	public static Properties load(PropertiesContext context) {
		Assert.notNull(context.getHelper(), "helper is null");
		Assert.notNull(context.getLocations(), "locations are null");
		Assert.notNull(context.getEncoding(), "encoding is null");
		Assert.notNull(context.getMissingLocationsMode(), "missingLocationsMode is null");
		Properties global = PropertyUtils.getGlobalProperties();
		context.setProperties(PropertyUtils.toEmpty(context.getProperties()));
		Properties result = new Properties();
		for (String location : context.getLocations()) {
			Properties resolverProperties = PropertyUtils.combine(context.getProperties(), result, global);
			String resolvedLocation = context.getHelper().replacePlaceholders(location, resolverProperties);
			if (LocationUtils.exists(resolvedLocation)) {
				Properties properties = PropertyUtils.load(resolvedLocation, context.getEncoding());
				result.putAll(context.getProperties());
				result.putAll(properties);
			} else {
				ModeUtils.validate(context.getMissingLocationsMode(), "Skipping non-existent location [" + resolvedLocation + "]");
			}
		}
		return result;
	}

	/**
	 * Decrypt any encrypted property values. Encrypted values are surrounded by ENC(...), like:
	 * 
	 * <pre>
	 * my.value = ENC(DGA"$S24FaIO)
	 * </pre>
	 */
	public static void decrypt(Properties properties, TextEncryptor encryptor) {
		decrypt(properties, encryptor, null, null);
	}

	/**
	 * Return a new <code>Properties</code> object (never null) containing only those properties whose values are encrypted. Encrypted values are surrounded by ENC(...), like:
	 * 
	 * <pre>
	 * my.value = ENC(DGA"$S24FaIO)
	 * </pre>
	 */
	public static Properties getEncryptedProperties(Properties properties) {
		List<String> keys = getSortedKeys(properties);
		Properties encrypted = new Properties();
		for (String key : keys) {
			String value = properties.getProperty(key);
			if (isEncryptedPropertyValue(value)) {
				encrypted.setProperty(key, value);
			}
		}
		return encrypted;
	}

	/**
	 * Decrypt any encrypted property values matching the <code>includes</code>, <code>excludes</code> patterns. Encrypted values are surrounded by ENC(...).
	 * 
	 * <pre>
	 * my.value = ENC(DGA"$S24FaIO)
	 * </pre>
	 */
	public static void decrypt(Properties properties, TextEncryptor encryptor, List<String> includes, List<String> excludes) {
		List<String> keys = getSortedKeys(properties, includes, excludes);
		for (String key : keys) {
			String value = properties.getProperty(key);
			if (isEncryptedPropertyValue(value)) {
				String decryptedValue = decryptPropertyValue(encryptor, value);
				properties.setProperty(key, decryptedValue);
			}
		}
	}

	/**
	 * Return true if the value starts with <code>ENC(</code> and ends with <code>)</code>, false otherwise.
	 */
	public static boolean isEncryptedPropertyValue(String value) {
		return StringUtils.startsWith(value, Constants.ENCRYPTION_PREFIX) && StringUtils.endsWith(value, Constants.ENCRYPTION_SUFFIX);
	}

	/**
	 * Encrypt all of the property values. Encrypted values are surrounded by ENC(...).
	 * 
	 * <pre>
	 * my.value = ENC(DGA"$S24FaIO)
	 * </pre>
	 */
	public static void encrypt(Properties properties, TextEncryptor encryptor) {
		encrypt(properties, encryptor, null, null);
	}

	/**
	 * Encrypt properties as dictated by <code>includes</code> and <code>excludes</code>. Encrypted values are surrounded by ENC(...).
	 * 
	 * <pre>
	 * my.value = ENC(DGA"$S24FaIO)
	 * </pre>
	 */
	public static void encrypt(Properties properties, TextEncryptor encryptor, List<String> includes, List<String> excludes) {
		List<String> keys = getSortedKeys(properties, includes, excludes);
		for (String key : keys) {
			String originalValue = properties.getProperty(key);
			String encryptedValue = encryptPropertyValue(encryptor, originalValue);
			properties.setProperty(key, encryptedValue);
		}
	}

	/**
	 * Return the decrypted version of the property value. Encrypted values are surrounded by ENC(...).
	 * 
	 * <pre>
	 * my.value = ENC(DGA"$S24FaIO)
	 * </pre>
	 */
	public static String decryptPropertyValue(TextEncryptor encryptor, String value) {
		// Ensure this property value really is encrypted
		Assert.isTrue(StringUtils.startsWith(value, Constants.ENCRYPTION_PREFIX), "value does not start with " + Constants.ENCRYPTION_PREFIX);
		Assert.isTrue(StringUtils.endsWith(value, Constants.ENCRYPTION_SUFFIX), "value does not end with " + Constants.ENCRYPTION_SUFFIX);

		// Extract the value inside the ENC(...) wrapping
		int start = Constants.ENCRYPTION_PREFIX.length();
		int end = StringUtils.length(value) - Constants.ENCRYPTION_SUFFIX.length();
		String unwrapped = StringUtils.substring(value, start, end);

		// Return the decrypted value
		return encryptor.decrypt(unwrapped);
	}

	/**
	 * Return the encrypted version of the property value. A value is considered "encrypted" when it appears surrounded by ENC(...).
	 * 
	 * <pre>
	 * my.value = ENC(DGA"$S24FaIO)
	 * </pre>
	 */
	public static String encryptPropertyValue(TextEncryptor encryptor, String value) {
		String encryptedValue = encryptor.encrypt(value);
		StringBuilder sb = new StringBuilder();
		sb.append(Constants.ENCRYPTION_PREFIX);
		sb.append(encryptedValue);
		sb.append(Constants.ENCRYPTION_SUFFIX);
		return sb.toString();
	}

	public static void overrideWithGlobalValues(Properties properties, GlobalPropertiesMode mode) {
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		Properties global = PropertyUtils.getProperties(mode);
		for (String key : keys) {
			String globalValue = global.getProperty(key);
			if (!StringUtils.isBlank(globalValue)) {
				properties.setProperty(key, globalValue);
			}
		}
	}

	public static final Properties combine(List<Properties> properties) {
		Properties combined = new Properties();
		for (Properties p : properties) {
			combined.putAll(PropertyUtils.toEmpty(p));
		}
		return combined;
	}

	public static final Properties combine(Properties... properties) {
		return combine(Arrays.asList(properties));
	}

	public static final void process(Properties properties, PropertyProcessor processor) {
		process(properties, Collections.singletonList(processor));
	}

	public static final void process(Properties properties, List<PropertyProcessor> processors) {
		for (PropertyProcessor processor : CollectionUtils.toEmptyList(processors)) {
			processor.process(properties);
		}
	}

	public static final Properties toEmpty(Properties properties) {
		return properties == null ? new Properties() : properties;
	}

	public static final boolean isSingleUnresolvedPlaceholder(String string) {
		return isSingleUnresolvedPlaceholder(string, Constants.DEFAULT_PLACEHOLDER_PREFIX, Constants.DEFAULT_PLACEHOLDER_SUFFIX);
	}

	public static final boolean isSingleUnresolvedPlaceholder(String string, String prefix, String suffix) {
		int prefixMatches = StringUtils.countMatches(string, prefix);
		int suffixMatches = StringUtils.countMatches(string, suffix);
		boolean startsWith = StringUtils.startsWith(string, prefix);
		boolean endsWith = StringUtils.endsWith(string, suffix);
		return prefixMatches == 1 && suffixMatches == 1 && startsWith && endsWith;
	}

	public static final boolean containsUnresolvedPlaceholder(String string) {
		return containsUnresolvedPlaceholder(string, Constants.DEFAULT_PLACEHOLDER_PREFIX, Constants.DEFAULT_PLACEHOLDER_SUFFIX);
	}

	public static final boolean containsUnresolvedPlaceholder(String string, String prefix, String suffix) {
		int beginIndex = StringUtils.indexOf(string, prefix);
		if (beginIndex == -1) {
			return false;
		}
		return StringUtils.indexOf(string, suffix) != -1;
	}

	/**
	 * Return a new <code>Properties</code> object containing only those properties where the resolved value is different from the original value. Using global properties to
	 * perform property resolution as indicated by <code>Constants.DEFAULT_GLOBAL_PROPERTIES_MODE</code>
	 */
	public static final Properties getResolvedProperties(Properties properties) {
		return getResolvedProperties(properties, Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER, Constants.DEFAULT_GLOBAL_PROPERTIES_MODE);
	}

	/**
	 * Return a new <code>Properties</code> object containing only those properties where the resolved value is different from the original value. Using global properties to
	 * perform property resolution as indicated by <code>globalPropertiesMode</code>
	 */
	public static final Properties getResolvedProperties(Properties properties, GlobalPropertiesMode globalPropertiesMode) {
		return getResolvedProperties(properties, Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER, globalPropertiesMode);
	}

	/**
	 * Return a new <code>Properties</code> object containing only those properties where the resolved value is different from the original value. Using global properties to
	 * perform property resolution as indicated by <code>Constants.DEFAULT_GLOBAL_PROPERTIES_MODE</code>
	 */
	public static final Properties getResolvedProperties(Properties properties, PropertyPlaceholderHelper helper) {
		return getResolvedProperties(properties, helper, Constants.DEFAULT_GLOBAL_PROPERTIES_MODE);
	}

	/**
	 * Return a new <code>Properties</code> object containing only those properties where the resolved value is different from the original value. Using global properties to
	 * perform property resolution as indicated by <code>globalPropertiesMode</code>
	 */
	public static final Properties getResolvedProperties(Properties properties, PropertyPlaceholderHelper helper, GlobalPropertiesMode globalPropertiesMode) {
		Properties global = PropertyUtils.getProperties(properties, globalPropertiesMode);
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		Properties newProperties = new Properties();
		for (String key : keys) {
			String originalValue = properties.getProperty(key);
			String resolvedValue = helper.replacePlaceholders(originalValue, global);
			if (!resolvedValue.equals(originalValue)) {
				logger.debug("Resolved property '" + key + "' [{}] -> [{}]", Str.flatten(originalValue), Str.flatten(resolvedValue));
				newProperties.setProperty(key, resolvedValue);
			}
		}
		return newProperties;
	}

	/**
	 * Return the property values from <code>keys</code>
	 */
	public static final List<String> getValues(Properties properties, List<String> keys) {
		List<String> values = new ArrayList<String>();
		for (String key : keys) {
			values.add(properties.getProperty(key));
		}
		return values;
	}

	/**
	 * Return a sorted <code>List</code> of keys from <code>properties</code> that end with <code>suffix</code>.
	 */
	public static final List<String> getEndsWithKeys(Properties properties, String suffix) {
		List<String> keys = getSortedKeys(properties);
		List<String> matches = new ArrayList<String>();
		for (String key : keys) {
			if (StringUtils.endsWith(key, suffix)) {
				matches.add(key);
			}
		}
		return matches;
	}

	/**
	 * Alter the <code>properties</code> passed in to contain only the desired property values. <code>includes</code> and <code>excludes</code> are comma separated values.
	 */
	public static final void trim(Properties properties, String includesCSV, String excludesCSV) {
		List<String> includes = CollectionUtils.getTrimmedListFromCSV(includesCSV);
		List<String> excludes = CollectionUtils.getTrimmedListFromCSV(excludesCSV);
		trim(properties, includes, excludes);
	}

	/**
	 * Alter the <code>properties</code> passed in to contain only the desired property values.
	 */
	public static final void trim(Properties properties, List<String> includes, List<String> excludes) {
		List<String> keys = getSortedKeys(properties);
		for (String key : keys) {
			if (!include(key, includes, excludes)) {
				logger.debug("Removing [{}]", key);
				properties.remove(key);
			}
		}
	}

	/**
	 * Return true if <code>value</code> should be included, false otherwise.<br>
	 * If <code>excludes</code> is not empty and matches <code>value</code> return false.<br>
	 * If <code>value</code> has not been explicitly excluded, check the <code>includes</code> list.<br>
	 * If <code>includes</code> is empty return true.<br>
	 * If <code>includes</code> is not empty, return true if, and only if, <code>value</code> matches a pattern from the <code>includes</code> list.<br>
	 * A single wildcard <code>*</code> is supported for <code>includes</code> and <code>excludes</code>.<br>
	 */
	public static final boolean include(String value, List<String> includes, List<String> excludes) {
		if (isSingleWildcardMatch(value, excludes)) {
			// No point incurring the overhead of matching an include pattern
			return false;
		} else {
			// If includes is empty always return true
			return CollectionUtils.isEmpty(includes) || isSingleWildcardMatch(value, includes);
		}
	}

	public static final boolean isSingleWildcardMatch(String s, List<String> patterns) {
		for (String pattern : CollectionUtils.toEmptyList(patterns)) {
			if (isSingleWildcardMatch(s, pattern)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Match {@code value} against {@code pattern} where {@code pattern} can optionally contain a single wildcard {@code *}. If both are {@code null} return {@code true}. If one of
	 * {@code value} or {@code pattern} is {@code null} but the other isn't, return {@code false}. Any {@code pattern} containing more than a single wildcard throws
	 * {@code IllegalArgumentException}.
	 * 
	 * <pre>
	 * PropertyUtils.isSingleWildcardMatch(null, null)          = true
	 * PropertyUtils.isSingleWildcardMatch(null, *)             = false
	 * PropertyUtils.isSingleWildcardMatch(*, null)             = false
	 * PropertyUtils.isSingleWildcardMatch(*, "*")              = true
	 * PropertyUtils.isSingleWildcardMatch("abcdef", "bcd")     = false
	 * PropertyUtils.isSingleWildcardMatch("abcdef", "*def")    = true
	 * PropertyUtils.isSingleWildcardMatch("abcdef", "abc*")    = true
	 * PropertyUtils.isSingleWildcardMatch("abcdef", "ab*ef")   = true
	 * PropertyUtils.isSingleWildcardMatch("abcdef", "abc*def") = true
	 * PropertyUtils.isSingleWildcardMatch(*, "**")             = IllegalArgumentException
	 * </pre>
	 */
	public static final boolean isSingleWildcardMatch(String value, String pattern) {
		if (value == null && pattern == null) {
			// both are null
			return true;
		} else if (value != null && pattern == null || value == null && pattern != null) {
			// One is null, but not the other
			return false;
		} else if (pattern.equals(Constants.WILDCARD)) {
			// neither one is null and pattern is the wildcard. Value is irrelevant
			return true;
		} else if (StringUtils.countMatches(pattern, Constants.WILDCARD) > 1) {
			// More than one wildcard in the pattern is not supported
			throw new IllegalArgumentException("Pattern [" + pattern + "] is not supported.  Only one wildcard is allowed in the pattern");
		} else if (!StringUtils.contains(pattern, Constants.WILDCARD)) {
			// Neither one is null and there is no wildcard in the pattern. They must match exactly
			return StringUtils.equals(value, pattern);
		} else {
			// The pattern contains 1 (and only 1) wildcard
			// Make sure value starts with the characters to the left of the wildcard
			// and ends with the characters to the right of the wildcard
			int pos = StringUtils.indexOf(pattern, Constants.WILDCARD);
			int suffixPos = pos + Constants.WILDCARD.length();
			boolean nullPrefix = pos == 0;
			boolean nullSuffix = suffixPos >= pattern.length();
			String prefix = nullPrefix ? null : StringUtils.substring(pattern, 0, pos);
			String suffix = nullSuffix ? null : StringUtils.substring(pattern, suffixPos);
			boolean prefixMatch = nullPrefix || StringUtils.startsWith(value, prefix);
			boolean suffixMatch = nullSuffix || StringUtils.endsWith(value, suffix);
			return prefixMatch && suffixMatch;
		}
	}

	/**
	 * Return property keys that should be included as a sorted list.
	 */
	public static final Properties getProperties(Properties properties, String include, String exclude) {
		List<String> keys = getSortedKeys(properties, include, exclude);
		Properties newProperties = new Properties();
		for (String key : keys) {
			String value = properties.getProperty(key);
			newProperties.setProperty(key, value);
		}
		return newProperties;
	}

	/**
	 * Return property keys that should be included as a sorted list.
	 */
	public static final List<String> getSortedKeys(Properties properties, String include, String exclude) {
		return getSortedKeys(properties, CollectionUtils.toEmptyList(include), CollectionUtils.toEmptyList(exclude));
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
	 * Return a sorted <code>List</code> of keys from <code>properties</code> that start with <code>prefix</code>
	 */
	public static final List<String> getStartsWithKeys(Properties properties, String prefix) {
		List<String> keys = getSortedKeys(properties);
		List<String> matches = new ArrayList<String>();
		for (String key : keys) {
			if (StringUtils.startsWith(key, prefix)) {
				matches.add(key);
			}
		}
		return matches;
	}

	/**
	 * Return the property keys as a sorted list.
	 */
	public static final List<String> getSortedKeys(Properties properties) {
		List<String> keys = new ArrayList<String>(properties.stringPropertyNames());
		Collections.sort(keys);
		return keys;
	}

	public static final String toString(Properties properties) {
		List<String> keys = getSortedKeys(properties);
		StringBuilder sb = new StringBuilder();
		for (String key : keys) {
			String value = Str.flatten(properties.getProperty(key));
			sb.append(key + "=" + value + "\n");
		}
		return sb.toString();
	}

	public static final void info(Properties properties) {
		properties = toEmpty(properties);
		logger.info("--- Displaying {} properties ---\n\n{}", properties.size(), toString(properties));
	}

	public static final void debug(Properties properties) {
		properties = toEmpty(properties);
		logger.debug("--- Displaying {} properties ---\n\n{}", properties.size(), toString(properties));
	}

	/**
	 * Store the properties to the indicated file using the platform default encoding.
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
			comment = getComment(encoding, comment, xml);
			if (xml) {
				logger.info("Storing XML properties - [{}] encoding={}", path, StringUtils.defaultIfBlank(encoding, DEFAULT_ENCODING));
				if (encoding == null) {
					sorted.storeToXML(out, comment);
				} else {
					sorted.storeToXML(out, comment, encoding);
				}
			} else {
				writer = LocationUtils.getWriter(out, encoding);
				logger.info("Storing properties - [{}] encoding={}", path, StringUtils.defaultIfBlank(encoding, DEFAULT_ENCODING));
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
	 * Return a new properties object containing the properties from <code>getEnvAsProperties()</code> and <code>System.getProperties()</code>. Properties from
	 * <code>System.getProperties()</code> override properties from <code>getEnvAsProperties</code> if there are duplicates.
	 */
	public static final Properties getGlobalProperties() {
		return getProperties(Constants.DEFAULT_GLOBAL_PROPERTIES_MODE);
	}

	/**
	 * Return a new properties object containing the properties passed in, plus any properties returned by <code>getEnvAsProperties()</code> and <code>System.getProperties()</code>
	 * . Properties from <code>getEnvAsProperties()</code> override <code>properties</code> and properties from <code>System.getProperties()</code> override everything.
	 */
	public static final Properties getGlobalProperties(Properties properties) {
		return getProperties(properties, Constants.DEFAULT_GLOBAL_PROPERTIES_MODE);
	}

	/**
	 * Return a new properties object containing the properties passed in, plus any global properties as requested. If <code>mode</code> is <code>NONE</code> the new properties are
	 * a duplicate of the properties passed in. If <code>mode</code> is <code>ENVIRONMENT</code> the new properties contain the original properties plus any properties returned by
	 * <code>getEnvProperties()</code>. If <code>mode</code> is <code>SYSTEM</code> the new properties contain the original properties plus <code>System.getProperties()</code>. If
	 * <code>mode</code> is <code>BOTH</code> the new properties contain the original properties plus <code>getEnvProperties()</code> and <code>System.getProperties()</code>.
	 */
	public static final Properties getProperties(Properties properties, GlobalPropertiesMode mode) {
		Properties newProperties = duplicate(properties);
		List<PropertyProcessor> modifiers = getPropertyProcessors(mode);
		for (PropertyProcessor modifier : modifiers) {
			modifier.process(newProperties);
		}
		return newProperties;
	}

	/**
	 * Return a new properties object containing global properties as requested. If <code>mode</code> is <code>NONE</code> the new properties are empty. If <code>mode</code> is
	 * <code>ENVIRONMENT</code> the new properties contain the properties returned by <code>getEnvProperties()</code>. If <code>mode</code> is <code>SYSTEM</code> the new
	 * properties contain <code>System.getProperties()</code>. If <code>mode</code> is <code>BOTH</code> the new properties contain <code>getEnvProperties</code> plus
	 * <code>System.getProperties()</code> with system properties overriding environment variables if the same case sensitive property key is supplied in both places.
	 */
	public static final Properties getProperties(GlobalPropertiesMode mode) {
		return getProperties(new Properties(), mode);
	}

	/**
	 * Search global properties to find a value for <code>key</code> according to the mode passed in.
	 */
	public static final String getProperty(String key, GlobalPropertiesMode mode) {
		return getProperty(key, new Properties(), mode);
	}

	/**
	 * Search <code>properties</code> plus global properties to find a value for <code>key</code> according to the mode passed in. If the property is present in both, the value
	 * from the global properties is returned.
	 */
	public static final String getProperty(String key, Properties properties, GlobalPropertiesMode mode) {
		return getProperties(properties, mode).getProperty(key);
	}

	/**
	 * Return modifiers that add environment variables, system properties, or both, according to the mode passed in.
	 */
	public static final List<PropertyProcessor> getPropertyProcessors(GlobalPropertiesMode mode) {
		List<PropertyProcessor> processors = new ArrayList<PropertyProcessor>();
		switch (mode) {
		case NONE:
			return processors;
		case ENVIRONMENT:
			processors.add(new AddPropertiesProcessor(getEnvAsProperties()));
			return processors;
		case SYSTEM:
			processors.add(new AddPropertiesProcessor(System.getProperties()));
			return processors;
		case BOTH:
			processors.add(new AddPropertiesProcessor(getEnvAsProperties()));
			processors.add(new AddPropertiesProcessor(System.getProperties()));
			return processors;
		default:
			throw new IllegalStateException(mode + " is unknown");
		}
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
	 * Return true if, and only if, location ends with <code>.xml</code> (case insensitive).
	 */
	public static final boolean isXml(String location) {
		return StringUtils.endsWithIgnoreCase(location, XML_EXTENSION);
	}

	/**
	 * Return a new <code>Properties</code> object loaded from <code>file</code>.
	 */
	public static final Properties load(File file) {
		return load(file, null);
	}

	/**
	 * Return a new <code>Properties</code> object loaded from <code>file</code> using the given encoding.
	 */
	public static final Properties load(File file, String encoding) {
		String location = LocationUtils.getCanonicalPath(file);
		return load(location, encoding);
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
			location = getCanonicalLocation(location);
			if (xml) {
				in = LocationUtils.getInputStream(location);
				logger.info("Loading XML properties - [{}]", location);
				properties.loadFromXML(in);
			} else {
				logger.info("Loading properties - [{}] encoding={}", location, StringUtils.defaultIfBlank(encoding, DEFAULT_ENCODING));
				reader = LocationUtils.getBufferedReader(location, encoding);
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

	protected static String getCanonicalLocation(String location) {
		if (LocationUtils.isExistingFile(location)) {
			return LocationUtils.getCanonicalPath(new File(location));
		} else {
			return location;
		}
	}

	/**
	 * Return a new <code>Properties</code> object containing properties prefixed with <code>prefix</code>. If <code>prefix</code> is blank, the new properties object duplicates
	 * the properties passed in.
	 */
	public static final Properties getPrefixedProperties(Properties properties, String prefix) {
		if (StringUtils.isBlank(prefix)) {
			return duplicate(properties);
		}
		Properties newProperties = new Properties();
		for (String key : properties.stringPropertyNames()) {
			String value = properties.getProperty(key);
			String newKey = StringUtils.startsWith(key, prefix + ".") ? key : prefix + "." + key;
			newProperties.setProperty(newKey, value);
		}
		return newProperties;
	}

	/**
	 * Return a new properties object where the keys have been converted to upper case and periods have been replaced with an underscore.
	 */
	public static final Properties reformatKeysAsEnvVars(Properties properties) {
		Properties newProperties = new Properties();
		for (String key : properties.stringPropertyNames()) {
			String value = properties.getProperty(key);
			String newKey = StringUtils.upperCase(StringUtils.replace(key, ".", "-"));
			newProperties.setProperty(newKey, value);
		}
		return newProperties;
	}

	/**
	 * Before setting the newValue, check to see if there is a conflict with an existing value. If there is no existing value, add the property. If there is a conflict, check
	 * <code>propertyOverwriteMode</code> to make sure we have permission to override the value.
	 */
	public static final void addOrOverrideProperty(Properties properties, String key, String newValue, Mode propertyOverwriteMode) {
		String oldValue = properties.getProperty(key);
		if (StringUtils.equals(newValue, oldValue)) {
			// Nothing to do! New value is the same as old value.
			return;
		}
		boolean overwrite = !StringUtils.isBlank(oldValue);

		// TODO Yuck! Do something smarter here
		String logNewValue = newValue;
		String logOldValue = oldValue;
		if (obscure(key)) {
			logNewValue = "PROTECTED";
			logOldValue = "PROTECTED";
		}

		if (overwrite) {
			// This property already has a value, and it is different from the new value
			// Check to make sure we are allowed to override the old value before doing so
			Object[] args = new Object[] { key, Str.flatten(logNewValue), Str.flatten(logOldValue) };
			ModeUtils.validate(propertyOverwriteMode, "Overriding [{}={}] was [{}]", args, "Override of existing property [" + key + "] is not allowed.");
		} else {
			// There is no existing value for this key
			logger.info("Adding [{}={}]", key, Str.flatten(logNewValue));
		}
		properties.setProperty(key, newValue);
	}

	protected static boolean obscure(String key) {
		if (StringUtils.containsIgnoreCase(key, ".password")) {
			return true;
		}
		if (StringUtils.containsIgnoreCase(key, ".secret")) {
			return true;
		}
		if (StringUtils.containsIgnoreCase(key, ".private")) {
			return true;
		}
		return false;
	}

	private static final String getDefaultComment(String encoding, boolean xml) {
		if (encoding == null) {
			if (xml) {
				// Java defaults XML properties files to UTF-8 if no encoding is provided
				return "encoding.default=" + DEFAULT_XML_ENCODING;
			} else {
				// For normal properties files the platform default encoding is used
				return "encoding.default=" + DEFAULT_ENCODING;
			}
		} else {
			return "encoding.specified=" + encoding;
		}
	}

	private static final String getComment(String encoding, String comment, boolean xml) {
		if (StringUtils.isBlank(comment)) {
			return getDefaultComment(encoding, xml);
		} else {
			return comment + "\n#" + getDefaultComment(encoding, xml);
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
	 * This is private since it does not honor the full contract for <code>Properties</code>. <code>PropertyUtils</code> uses it internally to store properties in sorted order.
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

	/**
	 * Set properties in the given Properties to CSV versions of the lists in the ComparisonResults
	 * 
	 * @param properties
	 *            the Properties to populate
	 * @param listComparison
	 *            the ComparisonResults to use for data
	 * @param propertyNames
	 *            the list of property keys to set. Exactly 3 names are required, and the assumed order is: index 0: key for the ADDED list index 1: key for the SAME list index 2:
	 *            key for the DELETED list
	 */
	public static final void addListComparisonProperties(Properties properties, ComparisonResults listComparison, List<String> propertyNames) {
		// make sure that there are three names in the list of property names
		Assert.isTrue(propertyNames.size() == 3);

		properties.setProperty(propertyNames.get(0), CollectionUtils.getCSV(listComparison.getAdded()));
		properties.setProperty(propertyNames.get(1), CollectionUtils.getCSV(listComparison.getSame()));
		properties.setProperty(propertyNames.get(2), CollectionUtils.getCSV(listComparison.getDeleted()));
	}

}
