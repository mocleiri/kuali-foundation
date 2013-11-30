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
import org.kuali.common.util.enc.EncStrength;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.GlobalPropertiesMode;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.property.PropertiesContext;
import org.kuali.common.util.property.PropertyFormat;
import org.kuali.common.util.property.processor.AddPropertiesProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

import com.google.common.base.Optional;

/**
 * Simplify handling of <code>Properties</code> especially as it relates to storing and loading. <code>Properties</code> can be loaded from any url Spring resource loading can
 * understand. When storing and loading, locations ending in <code>.xml</code> are automatically handled using <code>storeToXML()</code> and <code>loadFromXML()</code>,
 * respectively. <code>Properties</code> are always stored in sorted order with the <code>encoding</code> indicated via a comment.
 */
public class PropertyUtils {

	private static final Logger logger = LoggerFactory.getLogger(PropertyUtils.class);

	public static final String ADDITIONAL_LOCATIONS = "properties.additional.locations";
	public static final String ADDITIONAL_LOCATIONS_ENCODING = ADDITIONAL_LOCATIONS + ".encoding";
	public static final Properties EMPTY = new ImmutableProperties(new Properties());

	private static final String XML_EXTENSION = ".xml";
	private static final PropertyPlaceholderHelper HELPER = new PropertyPlaceholderHelper("${", "}", ":", false);
	public static final String ENV_PREFIX = "env";
	private static final String DEFAULT_ENCODING = Charset.defaultCharset().name();
	private static final String DEFAULT_XML_ENCODING = Encodings.UTF8;

	/**
	 * If there is no value for <code>key</code> or the value is NULL or NONE, return Optional.absent(), otherwise return Optional.of(value)
	 */
	public static Optional<String> getOptionalString(Properties properties, String key) {
		if (properties.getProperty(key) == null) {
			return Optional.absent();
		} else {
			return Optional.of(properties.getProperty(key));
		}
	}

	public static Optional<String> getString(Properties properties, String key, Optional<String> provided) {
		Optional<String> value = getOptionalString(properties, key);
		if (value.isPresent()) {
			return value;
		} else {
			return provided;
		}
	}

	/**
	 * If the properties passed in are already immutable, just return them, otherwise, return a new ImmutableProperties object
	 */
	public static Properties toImmutable(Properties properties) {
		return (properties instanceof ImmutableProperties) ? properties : new ImmutableProperties(properties);
	}

	/**
	 * The list returned by this method is unmodifiable and contains only <code>ImmutableProperties</code>
	 */
	public static List<Properties> toImmutable(List<Properties> properties) {
		List<Properties> immutables = new ArrayList<Properties>();
		for (Properties p : properties) {
			immutables.add(toImmutable(p));
		}
		return Collections.unmodifiableList(immutables);
	}

	/**
	 * Return true if the value for <code>key</code> evaluates to the string <code>true</code> (ignoring case). The properties passed in along with the system and environment
	 * variables are all inspected with the value for system or environment variables "winning" over the value from the properties passed in.
	 */
	public static boolean getGlobalBoolean(String key, Properties properties) {
		String defaultValue = properties.getProperty(key);
		String value = getGlobalProperty(key, defaultValue);
		return Boolean.parseBoolean(value);
	}

	public static boolean getBoolean(String key, Properties properties, boolean defaultValue) {
		String value = properties.getProperty(key);
		if (value == null) {
			return defaultValue;
		} else {
			return Boolean.parseBoolean(value);
		}
	}

	/**
	 * Return true if both contain an identical set of string keys and values, or both are <code>null</code>, false otherwise.
	 */
	public static boolean equals(Properties one, Properties two) {

		// Return true if they are the same object
		if (one == two) {
			return true;
		}

		// Return true if they are both null
		if (one == null && two == null) {
			return true;
		}

		// If we get here, both are not null (but one or the other might be)

		// Return false if one is null but not the other
		if (one == null || two == null) {
			return false;
		}

		// If we get here, neither one is null

		// Extract the string property keys
		List<String> keys1 = getSortedKeys(one);
		List<String> keys2 = getSortedKeys(two);

		// If the sizes are different, return false
		if (keys1.size() != keys2.size()) {
			return false;
		}

		// If we get here, they have the same number of string property keys

		// The sizes are the same, just pick one
		int size = keys1.size();

		// Iterate through the keys comparing both the keys and values for equality
		for (int i = 0; i < size; i++) {

			// Extract the keys
			String key1 = keys1.get(i);
			String key2 = keys2.get(i);

			// Compare the keys for equality (this works because the keys are in sorted order)
			if (!StringUtils.equals(key1, key2)) {
				return false;
			}

			// Extract the values
			String val1 = one.getProperty(key1);
			String val2 = two.getProperty(key2);

			// Compare the values for equality
			if (!StringUtils.equals(val1, val2)) {
				return false;
			}
		}

		// If we get here we know 3 things:

		// 1 - Both have the exact same number of string based keys/values
		// 2 - Both have an identical set of string based keys
		// 3 - Both have the exact same string value for each string key

		// This means they are equal, return true
		return true;

	}

	public static String getProperty(Properties properties, String key, String defaultValue) {
		String value = properties.getProperty(key);
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		} else {
			return value;
		}
	}

	public static boolean isEmpty(Properties properties) {
		return properties == null || properties.size() == 0;
	}

	public static String getRiceXML(Properties properties) {
		StringBuilder sb = new StringBuilder();
		sb.append("<config>\n");
		List<String> keys = getSortedKeys(properties);
		for (String key : keys) {
			String value = properties.getProperty(key);
			// Convert to CDATA if the value contains characters that would blow up an XML parser
			if (StringUtils.contains(value, "<") || StringUtils.contains(value, "&")) {
				value = Str.cdata(value);
			}
			sb.append("  <param name=" + Str.quote(key) + ">");
			sb.append(value);
			sb.append("</param>\n");
		}
		sb.append("</config>\n");
		return sb.toString();
	}

	public static String getRequiredResolvedProperty(Properties properties, String key) {
		return getRequiredResolvedProperty(properties, key, null);
	}

	public static String getRequiredResolvedProperty(Properties properties, String key, String defaultValue) {
		String value = properties.getProperty(key);
		value = StringUtils.isBlank(value) ? defaultValue : value;
		if (StringUtils.isBlank(value)) {
			throw new IllegalArgumentException("[" + key + "] is not set");
		} else {
			return HELPER.replacePlaceholders(value, properties);
		}
	}

	/**
	 * Process the properties passed in so they are ready for use by a Spring context.<br>
	 * 
	 * 1 - Override with system/environment properties<br>
	 * 2 - Decrypt any ENC(...) values<br>
	 * 3 - Resolve all property values throwing an exception if any are unresolvable.<br>
	 */
	public static void prepareContextProperties(Properties properties, String encoding) {

		// Override with additional properties (if any)
		properties.putAll(getAdditionalProperties(properties, encoding));

		// Override with system/environment properties
		properties.putAll(getGlobalProperties());

		// Are we decrypting property values?
		decrypt(properties);

		// Are we resolving placeholders
		resolve(properties);
	}

	/**
	 * Process the properties passed in so they are ready for use by a Spring context.<br>
	 * 
	 * 1 - Override with system/environment properties<br>
	 * 2 - Decrypt any ENC(...) values<br>
	 * 3 - Resolve all property values throwing an exception if any are unresolvable.<br>
	 */
	public static void prepareContextProperties(Properties properties) {
		prepareContextProperties(properties, null);
	}

	public static void resolve(Properties properties, PropertyPlaceholderHelper helper) {
		List<String> keys = getSortedKeys(properties);
		for (String key : keys) {
			String original = properties.getProperty(key);
			String resolved = helper.replacePlaceholders(original, properties);
			if (!StringUtils.equals(original, resolved)) {
				logger.debug("Resolved [{}]", key);
				properties.setProperty(key, resolved);
			}
		}
	}

	public static void removeSystemProperty(String key) {
		if (System.getProperty(key) != null) {
			logger.info("Removing system property [{}]", key);
			System.getProperties().remove(key);
		}
	}

	public static void removeSystemProperties(List<String> keys) {
		for (String key : keys) {
			removeSystemProperty(key);
		}
	}

	@Deprecated
	public static void resolve(Properties properties) {
		// Are we resolving placeholders?
		boolean resolve = new Boolean(getRequiredResolvedProperty(properties, "properties.resolve", "true"));
		if (resolve) {
			org.kuali.common.util.property.processor.ResolvePlaceholdersProcessor rpp = new org.kuali.common.util.property.processor.ResolvePlaceholdersProcessor();
			rpp.setHelper(HELPER);
			rpp.process(properties);
		}
	}

	@Deprecated
	public static void decrypt(Properties properties) {
		// Are we decrypting property values?
		boolean decrypt = Boolean.parseBoolean(getRequiredResolvedProperty(properties, "properties.decrypt", "false"));
		if (decrypt) {
			// If they asked to decrypt, a password is required
			String password = getRequiredResolvedProperty(properties, "properties.enc.password");

			// Strength is optional (defaults to BASIC)
			String defaultStrength = EncStrength.BASIC.name();
			String strength = getRequiredResolvedProperty(properties, "properties.enc.strength", defaultStrength);
			EncStrength es = EncStrength.valueOf(strength);
			TextEncryptor decryptor = EncUtils.getTextEncryptor(password, es);
			decrypt(properties, decryptor);
		}
	}

	public static Properties getAdditionalProperties(Properties properties) {
		return getAdditionalProperties(properties, null);
	}

	public static Properties getAdditionalProperties(Properties properties, String encoding) {
		String csv = properties.getProperty(ADDITIONAL_LOCATIONS);
		if (StringUtils.isBlank(csv)) {
			return new Properties();
		}
		if (StringUtils.isBlank(encoding)) {
			encoding = properties.getProperty(ADDITIONAL_LOCATIONS_ENCODING, DEFAULT_XML_ENCODING);
		}
		List<String> locations = CollectionUtils.getTrimmedListFromCSV(csv);
		PropertiesContext context = new PropertiesContext(locations, encoding);
		return load(context);
	}

	public static void appendToOrSetProperty(Properties properties, String key, String value) {
		Assert.hasText(value);
		String existingValue = properties.getProperty(key);
		if (existingValue == null) {
			existingValue = "";
		}
		String newValue = existingValue + value;
		properties.setProperty(key, newValue);
	}

	@Deprecated
	public static Properties load(List<org.kuali.common.util.property.ProjectProperties> pps) {

		// Create some storage for the Properties object we will be returning
		Properties properties = new Properties();

		// Cycle through the list of project properties, loading them as we go
		for (org.kuali.common.util.property.ProjectProperties pp : pps) {

			logger.debug("oracle.dba.url.1={}", properties.getProperty("oracle.dba.url"));

			// Extract the properties context object
			PropertiesContext ctx = pp.getPropertiesContext();

			// Retain the original properties object from the context
			Properties original = PropertyUtils.duplicate(PropertyUtils.toEmpty(ctx.getProperties()));

			// Override any existing property values with properties stored directly on the context
			Properties combined = PropertyUtils.combine(properties, ctx.getProperties());

			// Store the combined properties on the context itself
			ctx.setProperties(combined);

			// Load properties as dictated by the context
			Properties loaded = load(ctx);

			logger.debug("oracle.dba.url.2={}", loaded.getProperty("oracle.dba.url"));

			// Override any existing property values with those we just loaded
			properties.putAll(loaded);

			// Override any existing property values with the properties that were stored directly on the context
			properties.putAll(original);

		}

		// Return the property values we now have
		return properties;
	}

	public static Properties load(PropertiesContext context) {
		// If there are no locations specified, add the properties supplied directly on the context (if there are any)
		if (CollectionUtils.isEmpty(context.getLocations())) {
			return PropertyUtils.toEmpty(context.getProperties());
		}

		// Make sure we are configured correctly
		Assert.notNull(context.getHelper(), "helper is null");
		Assert.notNull(context.getLocations(), "locations are null");
		Assert.notNull(context.getEncoding(), "encoding is null");
		Assert.notNull(context.getMissingLocationsMode(), "missingLocationsMode is null");

		// Get system/environment properties
		Properties global = PropertyUtils.getGlobalProperties();

		// Convert null to an empty properties object (if necessary)
		context.setProperties(PropertyUtils.toEmpty(context.getProperties()));

		// Create new storage for the properties we are loading
		Properties result = new Properties();

		// Add in any properties stored directly on the context itself (these get overridden by properties loaded elsewhere)
		result.putAll(context.getProperties());

		// Cycle through the locations, loading and storing properties as we go
		for (String location : context.getLocations()) {

			// Get a combined Properties object capable of resolving any placeholders that exist in the property location strings
			Properties resolverProperties = PropertyUtils.combine(context.getProperties(), result, global);

			// Make sure we have a fully resolved location to load Properties from
			String resolvedLocation = context.getHelper().replacePlaceholders(location, resolverProperties);

			// If the location exists, load properties from it
			if (LocationUtils.exists(resolvedLocation)) {

				// Load this set of Properties
				Properties properties = PropertyUtils.load(resolvedLocation, context.getEncoding());

				// Add these properties to the result. This follows the traditional "last one in wins" strategy
				result.putAll(properties);
			} else {

				// Handle missing locations (might be fine, may need to emit a logging statement, may need to error out)
				ModeUtils.validate(context.getMissingLocationsMode(), "Non-existent location [" + resolvedLocation + "]");
			}
		}

		// Return the properties we loaded
		return result;
	}

	/**
	 * Decrypt any encrypted property values. Encrypted values are surrounded by ENC(...), like:
	 * 
	 * <pre>
	 * my.value = ENC(DGA$S24FaIO)
	 * </pre>
	 */
	public static void decrypt(Properties properties, TextEncryptor encryptor) {
		decrypt(properties, encryptor, null, null);
	}

	/**
	 * Return a new <code>Properties</code> object (never null) containing only those properties whose values are encrypted. Encrypted values are surrounded by ENC(...), like:
	 * 
	 * <pre>
	 * my.value = ENC(DGA$S24FaIO)
	 * </pre>
	 */
	public static Properties getEncryptedProperties(Properties properties) {
		List<String> keys = getEncryptedKeys(properties);
		Properties encrypted = new Properties();
		for (String key : keys) {
			String value = properties.getProperty(key);
			encrypted.setProperty(key, value);
		}
		return encrypted;
	}

	/**
	 * Return a list containing only those keys whose values are encrypted. Encrypted values are surrounded by ENC(...), like:
	 * 
	 * <pre>
	 * my.value = ENC(DGA$S24FaIO)
	 * </pre>
	 */
	public static List<String> getEncryptedKeys(Properties properties) {
		List<String> all = getSortedKeys(properties);
		List<String> encrypted = new ArrayList<String>();
		for (String key : all) {
			String value = properties.getProperty(key);
			if (EncUtils.isEncrypted(value)) {
				encrypted.add(key);
			}
		}
		return encrypted;
	}

	/**
	 * Decrypt any encrypted property values matching the <code>includes</code>, <code>excludes</code> patterns. Encrypted values are surrounded by ENC(...).
	 * 
	 * <pre>
	 * my.value = ENC(DGA$S24FaIO)
	 * </pre>
	 */
	public static void decrypt(Properties properties, TextEncryptor encryptor, List<String> includes, List<String> excludes) {
		List<String> keys = getSortedKeys(properties, includes, excludes);
		for (String key : keys) {
			String value = properties.getProperty(key);
			if (EncUtils.isEncrypted(value)) {
				String decryptedValue = decryptPropertyValue(encryptor, value);
				properties.setProperty(key, decryptedValue);
			}
		}
	}

	/**
	 * Return true if the value starts with <code>ENC(</code> and ends with <code>)</code>, false otherwise.
	 * 
	 * @deprecated Use EncUtils.isEncrypted(value) instead
	 */
	@Deprecated
	public static boolean isEncryptedPropertyValue(String value) {
		return EncUtils.isEncrypted(value);
	}

	/**
	 * <p>
	 * A trivial way to conceal property values. Can be reversed using <code>reveal()</code>. Do <b>NOT</b> use this method in an attempt to obscure sensitive data. The algorithm
	 * is completely trivial and exceedingly simple to reverse engineer. Not to mention, the <code>reveal()</code> method can reproduce the original string without requiring any
	 * secret knowledge.
	 * </p>
	 * 
	 * <p>
	 * The use case here is to help prevent someone with otherwise mostly good intentions from altering a piece of information in a way they should not. This is <b>NOT</b> intended
	 * to defeat any serious attempt at discovering the original text.
	 * </p>
	 * 
	 * <p>
	 * Think a hungry sales or marketing rep who stumbles across a config file with the entry <code>vending.machine.refill.day=wed</code> in it and tries to change that to
	 * <code>mon</code> in order to beat a case of the munchies. :)
	 * </p>
	 * 
	 * <p>
	 * If the entry says <code>vending.machine.refill.day=cnc--jrq</code> instead of <code>vending.machine.refill.day=wed</code> they are far more likely to ask around before they
	 * change it <b>OR</b> just give up and head out to lunch instead.
	 * </p>
	 * 
	 * @see reveal
	 */
	public static void conceal(Properties properties) {
		List<String> keys = getSortedKeys(properties);
		for (String key : keys) {
			String value = properties.getProperty(key);
			String concealed = Str.conceal(value);
			properties.setProperty(key, concealed);
		}
	}

	/**
	 * Reveal property values that were concealed by the <code>conceal</code> method
	 * 
	 * <pre>
	 * foo=cnc--one.onm -> foo=bar.baz
	 * </pre>
	 */
	public static void reveal(Properties properties) {
		List<String> keys = getSortedKeys(properties);
		for (String key : keys) {
			String value = properties.getProperty(key);
			String revealed = Str.reveal(value);
			properties.setProperty(key, revealed);
		}
	}

	/**
	 * Encrypt all of the property values. Encrypted values are surrounded by ENC(...).
	 * 
	 * <pre>
	 * my.value = ENC(DGA$S24FaIO)
	 * </pre>
	 */
	public static void encrypt(Properties properties, TextEncryptor encryptor) {
		encrypt(properties, encryptor, null, null);
	}

	/**
	 * Encrypt properties as dictated by <code>includes</code> and <code>excludes</code>. Encrypted values are surrounded by ENC(...).
	 * 
	 * <pre>
	 * my.value = ENC(DGA$S24FaIO)
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
	 * my.value = ENC(DGA$S24FaIO)
	 * </pre>
	 */
	public static String decryptPropertyValue(TextEncryptor encryptor, String value) {
		String unwrapped = unwrapEncryptedValue(value);

		// Return the decrypted value
		return encryptor.decrypt(unwrapped);
	}

	/**
	 * Remove the leading <code>ENC(</code> prefix and the trailing <code>)</code> from the encrypted value passed in.
	 * 
	 * <pre>
	 * ENC(DGA$S24FaIO) -> DGA$S24FaIO
	 * </pre>
	 * 
	 * @deprecated Use EncUtils.unwrap(value) instead
	 */
	@Deprecated
	public static String unwrapEncryptedValue(String encryptedValue) {
		return org.kuali.common.util.enc.EncUtils.unwrap(encryptedValue);
	}

	/**
	 * Return the encrypted version of the property value. A value is considered "encrypted" when it appears surrounded by ENC(...).
	 * 
	 * <pre>
	 * my.value = ENC(DGA$S24FaIO)
	 * </pre>
	 */
	public static String encryptPropertyValue(TextEncryptor encryptor, String value) {
		String encryptedValue = encryptor.encrypt(value);
		return wrapEncryptedPropertyValue(encryptedValue);
	}

	/**
	 * Return the value enclosed with ENC()
	 * 
	 * <pre>
	 * DGA$S24FaIO -> ENC(DGA$S24FaIO)
	 * </pre>
	 * 
	 * @deprecated Use EncUtils.wrap(value) instead
	 */
	@Deprecated
	public static String wrapEncryptedPropertyValue(String encryptedValue) {
		return org.kuali.common.util.enc.EncUtils.wrap(encryptedValue);
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

	public static final Properties[] toArray(List<Properties> properties) {
		return properties.toArray(new Properties[properties.size()]);
	}

	public static final Properties combine(Properties properties, List<Properties> list) {
		List<Properties> newList = new ArrayList<Properties>(CollectionUtils.toEmptyList(list));
		newList.add(0, toEmpty(properties));
		return combine(newList);
	}

	public static final Properties combine(List<Properties> properties) {
		Properties combined = new Properties();
		for (Properties p : properties) {
			combined.putAll(toEmpty(p));
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
		Properties global = getProperties(properties, globalPropertiesMode);
		List<String> keys = getSortedKeys(properties);
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
		} else if (value == null || pattern == null) {
			// One is null, but not the other
			return false;
		} else if (pattern.equals(Constants.WILDCARD)) {
			// Neither one is null and pattern is the unqualified wildcard.
			// Value is irrelevant, always return true
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
	 * Store the properties to the indicated file using the platform default encoding.
	 */
	public static final void storeSilently(Properties properties, File file) {
		store(properties, file, null, null, true);
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
		store(properties, file, encoding, comment, false);
	}

	/**
	 * Store the properties to the indicated file using the indicated encoding with the indicated comment appearing at the top of the file.
	 */
	public static final void store(Properties properties, File file, String encoding, String comment, boolean silent) {
		OutputStream out = null;
		Writer writer = null;
		try {
			out = FileUtils.openOutputStream(file);
			String path = file.getCanonicalPath();
			boolean xml = isXml(path);
			Properties sorted = getSortedProperties(properties);
			comment = getComment(encoding, comment, xml);
			if (xml) {
				if (!silent) {
					logger.info("Storing XML properties - [{}] encoding={}", path, StringUtils.defaultIfBlank(encoding, DEFAULT_ENCODING));
				}
				if (encoding == null) {
					sorted.storeToXML(out, comment);
				} else {
					sorted.storeToXML(out, comment, encoding);
				}
			} else {
				writer = LocationUtils.getWriter(out, encoding);
				if (!silent) {
					logger.info("Storing properties - [{}] encoding={}", path, StringUtils.defaultIfBlank(encoding, DEFAULT_ENCODING));
				}
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
	 * Examine both system properties and environment variables to get a value for <code>key</code>. Return <code>null</code> if nothing is found.
	 * 
	 * <pre>
	 *   foo.bar -> System property check for "foo.bar"
	 *   foo.bar -> Environment check for "FOO_BAR"
	 * </pre>
	 */
	public static final String getGlobalProperty(String key) {
		return getGlobalProperty(key, null);
	}

	/**
	 * Examine both system properties and environment variables to get a value for <code>key</code>. Return <code>defaultValue</code> if nothing is found
	 * 
	 * <pre>
	 *   foo.bar -> System property check for "foo.bar"
	 *   foo.bar -> Environment check for "FOO_BAR"
	 * </pre>
	 */
	public static final String getGlobalProperty(String key, String defaultValue) {
		Assert.noNullsWithMsg("key is required", key);

		// Check to see if there is a system property for this key
		String systemValue = System.getProperty(key);

		// If so, we are done
		if (systemValue != null) {
			return systemValue;
		}

		// Reformat the key as an environment variable key
		String environmentVariable = convertToEnvironmentVariable(key);

		// Check to see if we have a match for an environment variable
		String environmentValue = System.getenv(environmentVariable);

		if (environmentValue != null) {
			// If so, return the value of the environment variable
			return environmentValue;
		} else {
			// If not, return the default value
			return defaultValue;
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
	 * Return true if, and only if, location ends with <code>rice-properties.xml</code> (case insensitive).
	 */
	public static final boolean isRiceProperties(String location) {
		return StringUtils.endsWithIgnoreCase(location, Constants.RICE_PROPERTIES_SUFFIX);
	}

	/**
	 * Return a new <code>Properties</code> object loaded from <code>file</code> where the properties are stored in Rice XML style syntax
	 */
	public static final Properties loadRiceProperties(File file) {
		return loadRiceProperties(LocationUtils.getCanonicalPath(file));
	}

	/**
	 * Return a new <code>Properties</code> object loaded from <code>location</code> where the properties are stored in Rice XML style syntax
	 */
	public static final Properties loadRiceProperties(String location) {
		logger.info("Loading Rice properties [{}] encoding={}", location, DEFAULT_XML_ENCODING);
		String contents = LocationUtils.toString(location, DEFAULT_XML_ENCODING);
		String config = StringUtils.substringBetween(contents, "<config>", "</config>");
		String[] tokens = StringUtils.substringsBetween(config, "<param", "</param>");

		Properties properties = new Properties();
		for (String token : tokens) {
			String key = StringUtils.substringBetween(token, "name=\"", "\">");
			validateRiceProperties(token, key);
			String value = StringUtils.substringBetween(token + "</param>", "\">", "</param>");
			properties.setProperty(key, value);
		}
		return properties;
	}

	/**
	 * Make sure they are just loading simple properties and are not using any of the unsupported "features". Can't have a key named config.location, and can't use the system,
	 * override, or random attributes.
	 */
	protected static final void validateRiceProperties(String token, String key) {
		if (StringUtils.equalsIgnoreCase("config.location", key)) {
			throw new IllegalArgumentException("config.location is not supported");
		}
		if (StringUtils.contains(token, "override=\"")) {
			throw new IllegalArgumentException("override attribute is not supported");
		}
		if (StringUtils.contains(token, "system=\"")) {
			throw new IllegalArgumentException("system attribute is not supported");
		}
		if (StringUtils.contains(token, "random=\"")) {
			throw new IllegalArgumentException("random attribute is not supported");
		}
	}

	/**
	 * Return a new <code>Properties</code> object loaded from <code>file</code>.
	 */
	public static final Properties load(File file) {
		return load(file, null);
	}

	/**
	 * Return a new <code>Properties</code> object loaded from <code>file</code>.
	 */
	public static final Properties loadSilently(File file) {
		return loadSilently(LocationUtils.getCanonicalPath(file));
	}

	/**
	 * Return a new <code>Properties</code> object loaded from <code>file</code>.
	 */
	public static final Properties loadSilently(String location) {
		return load(location, null, PropertyFormat.NORMAL, true);
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
	 * If location exists, return a new <code>Properties</code> object loaded from <code>location</code>, otherwise return a new <code>Properties</code> object
	 */
	public static final Properties loadOrCreateSilently(String location) {
		if (LocationUtils.exists(location)) {
			return load(location, null, PropertyFormat.NORMAL, true);
		} else {
			return new Properties();
		}
	}

	/**
	 * Return a new <code>Properties</code> object loaded from <code>locations</code> using <code>encoding</code>.
	 */
	public static final Properties load(List<String> locations, String encoding) {
		Properties properties = new Properties();
		for (String location : locations) {
			properties.putAll(load(location, encoding));
		}
		return properties;
	}

	/**
	 * Return a new <code>Properties</code> object loaded from <code>location</code> using <code>encoding</code>.
	 */
	public static final Properties load(String location, String encoding) {
		return load(location, encoding, PropertyFormat.NORMAL);
	}

	/**
	 * Return a new <code>Properties</code> object loaded from <code>location</code> using <code>encoding</code>.
	 */
	public static final Properties load(String location, String encoding, PropertyFormat format) {
		return load(location, encoding, format, false);
	}

	/**
	 * Return a new <code>Properties</code> object loaded from <code>location</code> using <code>encoding</code>.
	 */
	public static final Properties load(String location, String encoding, PropertyFormat format, boolean silent) {
		InputStream in = null;
		Reader reader = null;
		try {
			Properties properties = new Properties();
			boolean xml = isXml(location);
			boolean riceProperties = isRiceProperties(location);
			location = getCanonicalLocation(location);
			if (PropertyFormat.RICE.equals(format) || riceProperties) {
				properties = loadRiceProperties(location);
			} else if (xml) {
				in = LocationUtils.getInputStream(location);
				if (!silent) {
					logger.info("Loading XML properties - [{}]", location);
				}
				properties.loadFromXML(in);
			} else {
				if (!silent) {
					logger.info("Loading properties - [{}] encoding={}", location, StringUtils.defaultIfBlank(encoding, DEFAULT_ENCODING));
				}
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
	 * Replace periods with an underscore and convert to uppercase
	 * 
	 * <pre>
	 *   foo.bar -> FOO_BAR
	 * </pre>
	 */
	public static final String convertToEnvironmentVariable(String key) {
		return StringUtils.upperCase(StringUtils.replace(key, ".", "_"));
	}

	/**
	 * Replace periods with an underscore, convert to uppercase, and prefix with <code>env</code>
	 * 
	 * <pre>
	 *   foo.bar -> env.FOO_BAR
	 * </pre>
	 */
	public static final String getEnvironmentVariableKey(String key) {
		return ENV_PREFIX + "." + convertToEnvironmentVariable(key);
	}

	/**
	 * Return a new properties object where the keys have been converted to upper case and periods have been replaced with an underscore.
	 */
	public static final Properties reformatKeysAsEnvVars(Properties properties) {
		Properties newProperties = new Properties();
		for (String key : properties.stringPropertyNames()) {
			String value = properties.getProperty(key);
			String newKey = convertToEnvironmentVariable(key);
			newProperties.setProperty(newKey, value);
		}
		return newProperties;
	}

	/**
	 * Before setting the newValue, check to see if there is a conflict with an existing value. If there is no existing value, add the property. If there is a conflict, check
	 * <code>propertyOverwriteMode</code> to make sure we have permission to override the value.
	 */
	public static final void addOrOverrideProperty(Properties properties, String key, String newValue, Mode propertyOverwriteMode) {
		addOrOverrideProperty(properties, key, newValue, propertyOverwriteMode, 0);
	}

	public static final void addOrOverrideProperty(Properties properties, String key, String newValue, Mode overrideMode, int indent) {
		String oldValue = properties.getProperty(key);
		if (StringUtils.equals(newValue, oldValue)) {
			// Nothing to do! New value is the same as old value.
			return;
		}
		boolean overwrite = !StringUtils.isBlank(oldValue);

		String logNewValue = newValue;
		String logOldValue = oldValue;

		// TODO Yuck! Do something smarter here
		if (obscure(key)) {
			logNewValue = "*********";
			logOldValue = "*********";
		}

		if (overwrite) {
			// This property already has a value, and it is different from the new value
			// Check to make sure we are allowed to override the old value before doing so
			Object[] args = new Object[] { StringUtils.repeat(" ", indent), key, Str.flatten(logNewValue), Str.flatten(logOldValue) };
			ModeUtils.validate(overrideMode, "{}override [{}] -> [{}]", args, "Override of existing property [" + key + "] is not allowed.");
		} else {
			// There is no existing value for this key
			logger.debug("Adding [{}={}]", key, Str.flatten(logNewValue));
		}
		properties.setProperty(key, newValue);
	}

	protected static boolean obscure(String key) {
		if (StringUtils.containsIgnoreCase(key, "password")) {
			return true;
		}
		if (StringUtils.containsIgnoreCase(key, "secret")) {
			return true;
		}
		if (StringUtils.containsIgnoreCase(key, "private")) {
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
