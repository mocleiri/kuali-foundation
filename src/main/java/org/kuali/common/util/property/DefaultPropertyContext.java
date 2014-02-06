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
package org.kuali.common.util.property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.obscure.DefaultObscurer;
import org.kuali.common.util.obscure.Obscurer;
import org.kuali.common.util.property.processor.AddPrefixProcessor;
import org.kuali.common.util.property.processor.GlobalOverrideProcessor;
import org.kuali.common.util.property.processor.OverrideProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.property.processor.ReformatKeysAsEnvVarsProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 * @deprecated
 */
@Deprecated
public class DefaultPropertyContext implements PropertyContext {

	private static final Logger logger = LoggerFactory.getLogger(DefaultPropertyContext.class);
	PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	String globalPropertiesMode = Constants.DEFAULT_GLOBAL_PROPERTIES_MODE.name();
	String resolvePlaceholders = Boolean.toString(Constants.DEFAULT_RESOLVE_PLACEHOLDERS);
	Obscurer obscurer = new DefaultObscurer();
	String style = PropertyStyle.NORMAL.name();
	String encryptionMode = org.kuali.common.util.EncryptionMode.NONE.name();
	String encryptionStrength = org.kuali.common.util.EncryptionStrength.BASIC.name();
	String encryptionPassword;
	String prefix;
	List<PropertyProcessor> processors;
	Properties properties;
	Properties buildProperties;
	List<String> buildPropertyIncludes;
	List<String> buildPropertyExcludes;

	protected List<PropertyProcessor> getDefaultProcessors() {
		List<PropertyProcessor> processors = new ArrayList<PropertyProcessor>();

		// If this context is being loaded as part of a build process, build properties win over properties from .properties files
		if (buildProperties != null) {
			OverrideProcessor overrideProcessor = new OverrideProcessor(Constants.DEFAULT_PROPERTY_OVERWRITE_MODE, buildProperties);
			if (buildPropertyIncludes != null) {
				overrideProcessor.setIncludes(buildPropertyIncludes);
			}
			if (buildPropertyExcludes != null) {
				overrideProcessor.setExcludes(buildPropertyExcludes);
			}
			processors.add(overrideProcessor);
		}

		// Decrypt/encrypt as appropriate
		if (encryptionMode != null) {
			org.kuali.common.util.EncryptionMode mode = org.kuali.common.util.EncryptionMode.valueOf(encryptionMode);
			org.kuali.common.util.EncryptionStrength strength = org.kuali.common.util.EncryptionStrength.valueOf(encryptionStrength);
			processors.add(getEncProcessor(mode, strength, encryptionPassword));
		}

		/**
		 * Remove the local reference to the encryption password now that the TextEncryptor has been created.<br>
		 * Note that the encryption password is VERY likely to be hanging around in memory even after being set to null locally.<br>
		 * Setting it to null here just makes it slightly tougher for someone to obtain the password.<br>
		 * Having a reference to this bean no longer does them any good, they'll have to search around in memory to find it.<br>
		 */
		this.encryptionPassword = null;

		GlobalPropertiesMode gpm = GlobalPropertiesMode.valueOf(globalPropertiesMode);

		// By default, system/environment properties override loaded properties
		processors.add(new GlobalOverrideProcessor(gpm));

		// By default, all placeholders in the properties are resolved
		if (Boolean.parseBoolean(resolvePlaceholders)) {
			processors.add(new org.kuali.common.util.property.processor.ResolvePlaceholdersProcessor(helper, gpm));
		}

		// Add a prefix to the property keys if appropriate
		if (!StringUtils.isBlank(prefix)) {
			processors.add(new AddPrefixProcessor(prefix));
		}

		// Reformat the keys in environment variable format if appropriate
		if (style != null) {
			processors.add(getStyleProcessor(style));
		}

		// Return the list of processors
		return processors;
	}

	protected PropertyProcessor getStyleProcessor(String style) {
		switch (PropertyStyle.valueOf(style)) {
		case NORMAL:
			return Constants.NO_OP_PROCESSOR;
		case ENVIRONMENT_VARIABLE:
			return new ReformatKeysAsEnvVarsProcessor();
		default:
			throw new IllegalArgumentException("Property style " + style + " is unknown");
		}
	}

	protected PropertyProcessor getEncProcessor(org.kuali.common.util.EncryptionMode mode, org.kuali.common.util.EncryptionStrength strength, String password) {
		switch (mode) {
		case NONE:
			return Constants.NO_OP_PROCESSOR;
		case ENCRYPT:
			TextEncryptor encryptor = org.kuali.common.util.EncUtils.getTextEncryptor(strength, password);
			return new org.kuali.common.util.property.processor.EndsWithEncryptProcessor(encryptor);
		case DECRYPT:
			TextEncryptor decryptor = org.kuali.common.util.EncUtils.getTextEncryptor(strength, password);
			return new org.kuali.common.util.property.processor.EndsWithDecryptProcessor(decryptor);
		default:
			throw new IllegalArgumentException("Encryption mode '" + mode + "' is unknown");
		}
	}

	protected void log() {
		if (!StringUtils.equals(org.kuali.common.util.EncryptionMode.NONE.name(), encryptionMode)) {
			logger.info("Encryption mode - " + StringUtils.trimToEmpty(encryptionMode));
			logger.info("Encryption strength - " + StringUtils.trimToEmpty(encryptionStrength));
			String displayPassword = org.kuali.common.util.LoggerUtils.getNullAsNone(encryptionPassword);
			if (encryptionPassword != null) {
				displayPassword = obscurer.obscure(encryptionPassword);
			}
			logger.info("Encryption password - " + displayPassword);
		}
		if (!StringUtils.equals(PropertyStyle.NORMAL.name(), style)) {
			logger.info("Property style - " + StringUtils.trimToEmpty(style));
		}
		if (!StringUtils.isEmpty(prefix)) {
			logger.info("Property prefix - " + StringUtils.trimToEmpty(prefix));
		}
		if (!StringUtils.equals(Boolean.toString(Constants.DEFAULT_RESOLVE_PLACEHOLDERS), resolvePlaceholders)) {
			logger.info("Resolve placeholders - " + resolvePlaceholders);
		}
	}

	@Override
	public void initialize(Properties properties) {
		GlobalPropertiesMode gpm = GlobalPropertiesMode.valueOf(globalPropertiesMode);
		Properties global = PropertyUtils.getProperties(properties, gpm);
		this.encryptionMode = resolve(encryptionMode, global);
		this.encryptionPassword = resolveAndRemove(encryptionPassword, global, properties);
		this.encryptionStrength = resolve(encryptionStrength, global);
		this.style = resolve(style, global);
		this.prefix = resolve(prefix, global);
		this.resolvePlaceholders = resolve(resolvePlaceholders, global);
		log();
		validate();
		addProcessors();
		logger.info("Proceeding with " + processors.size() + " processors.");
	}

	protected void addProcessors() {
		List<PropertyProcessor> defaultProcessors = getDefaultProcessors();
		if (processors == null) {
			processors = defaultProcessors;
		} else {
			processors.addAll(0, defaultProcessors);
		}
	}

	protected void validate() {
		org.kuali.common.util.EncryptionMode.valueOf(encryptionMode);
		org.kuali.common.util.EncryptionStrength.valueOf(encryptionStrength);
		PropertyStyle.valueOf(style);
		Boolean.parseBoolean(resolvePlaceholders);
	}

	protected String getPlaceholderKey(String string) {
		String prefix = Constants.DEFAULT_PLACEHOLDER_PREFIX;
		String suffix = Constants.DEFAULT_PLACEHOLDER_SUFFIX;
		String separator = Constants.DEFAULT_VALUE_SEPARATOR;
		String key = StringUtils.substringBetween(string, prefix, separator);
		if (key == null) {
			return StringUtils.substringBetween(string, prefix, suffix);
		} else {
			return key;
		}
	}

	protected void remove(String string, String resolvedString, Properties properties) {
		boolean placeholder = PropertyUtils.isSingleUnresolvedPlaceholder(string);
		boolean resolved = !StringUtils.equals(string, resolvedString);
		boolean irrelevant = Str.contains(Arrays.asList(Constants.NONE, Constants.NULL), resolvedString, false);
		boolean remove = placeholder && resolved && !irrelevant;
		if (remove) {
			String key = getPlaceholderKey(string);
			Assert.notNull(key, "key is null");
			if (properties.getProperty(key) != null) {
				logger.info("Removing property '" + key + "'");
				properties.remove(key);
			}
		}
	}

	protected String resolveAndRemove(String string, Properties global, Properties properties) {
		String resolvedString = resolve(string, global);
		remove(string, resolvedString, properties);
		return resolvedString;
	}

	protected String resolve(String string, Properties properties) {
		if (string == null) {
			return null;
		} else {
			String resolvedValue = helper.replacePlaceholders(string, properties);
			if (!StringUtils.equals(string, resolvedValue)) {
				logger.debug("Resolved {} -> {}", string, resolvedValue);
			}
			return resolvedValue;
		}
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public PropertyPlaceholderHelper getHelper() {
		return helper;
	}

	public void setHelper(PropertyPlaceholderHelper helper) {
		this.helper = helper;
	}

	public String getEncryptionMode() {
		return encryptionMode;
	}

	public void setEncryptionMode(String encryptionMode) {
		this.encryptionMode = encryptionMode;
	}

	public String getEncryptionStrength() {
		return encryptionStrength;
	}

	public void setEncryptionStrength(String encryptionStrength) {
		this.encryptionStrength = encryptionStrength;
	}

	public String getEncryptionPassword() {
		return encryptionPassword;
	}

	public void setEncryptionPassword(String encryptionPassword) {
		this.encryptionPassword = encryptionPassword;
	}

	@Override
	public List<PropertyProcessor> getProcessors() {
		return processors;
	}

	public void setProcessors(List<PropertyProcessor> processors) {
		this.processors = processors;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getGlobalPropertiesMode() {
		return globalPropertiesMode;
	}

	public void setGlobalPropertiesMode(String globalPropertiesMode) {
		this.globalPropertiesMode = globalPropertiesMode;
	}

	public String getResolvePlaceholders() {
		return resolvePlaceholders;
	}

	public void setResolvePlaceholders(String resolvePlaceholders) {
		this.resolvePlaceholders = resolvePlaceholders;
	}

	public Obscurer getObscurer() {
		return obscurer;
	}

	public void setObscurer(Obscurer obscurer) {
		this.obscurer = obscurer;
	}

	public Properties getBuildProperties() {
		return buildProperties;
	}

	public void setBuildProperties(Properties buildProperties) {
		this.buildProperties = buildProperties;
	}

	public List<String> getBuildPropertyIncludes() {
		return buildPropertyIncludes;
	}

	public void setBuildPropertyIncludes(List<String> buildPropertyIncludes) {
		this.buildPropertyIncludes = buildPropertyIncludes;
	}

	public List<String> getBuildPropertyExcludes() {
		return buildPropertyExcludes;
	}

	public void setBuildPropertyExcludes(List<String> buildPropertyExcludes) {
		this.buildPropertyExcludes = buildPropertyExcludes;
	}
}
