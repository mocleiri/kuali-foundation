/**
 * Copyright 2010-2012 The Kuali Foundation
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
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.EncUtils;
import org.kuali.common.util.EncryptionStrength;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.processor.AddPrefixProcessor;
import org.kuali.common.util.property.processor.EndsWithDecryptProcessor;
import org.kuali.common.util.property.processor.EndsWithEncryptProcessor;
import org.kuali.common.util.property.processor.GlobalOverrideProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.property.processor.ReformatKeysAsEnvVarsProcessor;
import org.kuali.common.util.property.processor.ResolvePlaceholdersProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultPropertyContext implements PropertyContext {

	private static final Logger logger = LoggerFactory.getLogger(DefaultPropertyContext.class);

	PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	String globalPropertiesMode = Constants.DEFAULT_GLOBAL_PROPERTIES_MODE.name();
	String style = PropertyStyle.NORMAL.name();
	String encryptionMode = PropertyEncryptionMode.NONE.name();
	String encryptionStrength = EncryptionStrength.BASIC.name();
	String encryptionPassword;
	boolean resolvePlaceholders;
	String prefix;
	List<PropertyProcessor> processors;
	Properties properties;

	protected List<PropertyProcessor> getDefaultProcessors() {
		List<PropertyProcessor> processors = new ArrayList<PropertyProcessor>();

		// Decrypt/encrypt as appropriate
		if (encryptionMode != null) {
			processors.add(getEncProcessor(encryptionMode, encryptionStrength, encryptionPassword));
		}

		// Make sure system/environment properties override everything else
		GlobalPropertiesMode gpm = GlobalPropertiesMode.valueOf(globalPropertiesMode);
		processors.add(new GlobalOverrideProcessor(gpm));
		if (resolvePlaceholders) {
			processors.add(new ResolvePlaceholdersProcessor(helper, gpm));
		}

		// Add a prefix to all of the existing properties if appropriate
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

	protected PropertyProcessor getEncProcessor(String mode, String strength, String password) {
		switch (PropertyEncryptionMode.valueOf(mode)) {
		case NONE:
			return Constants.NO_OP_PROCESSOR;
		case ENCRYPT:
			TextEncryptor encryptor = EncUtils.getTextEncryptor(EncryptionStrength.valueOf(strength), password);
			return new EndsWithEncryptProcessor(encryptor);
		case DECRYPT:
			TextEncryptor decryptor = EncUtils.getTextEncryptor(EncryptionStrength.valueOf(strength), password);
			return new EndsWithDecryptProcessor(decryptor);
		default:
			throw new IllegalArgumentException("Encryption mode '" + mode + "' is unknown");
		}
	}

	@Override
	public void initialize(Properties properties) {
		GlobalPropertiesMode gpm = GlobalPropertiesMode.valueOf(globalPropertiesMode);
		Properties global = PropertyUtils.getProperties(properties, gpm);
		this.encryptionMode = resolve(encryptionMode, global);
		this.encryptionPassword = resolve(encryptionPassword, global);
		this.encryptionStrength = resolve(encryptionStrength, global);
		this.style = resolve(style, global);
		this.prefix = resolve(prefix, global);
		logger.info("Encryption mode - " + encryptionMode);
		logger.info("Encryption strength - " + encryptionStrength);
		logger.info("Encryption password - " + encryptionPassword);
		logger.info("Property style - " + style);
		logger.info("Property prefix - " + prefix);
		List<PropertyProcessor> defaultProcessors = getDefaultProcessors();
		if (processors == null) {
			processors = defaultProcessors;
		} else {
			processors.addAll(0, defaultProcessors);
		}
		logger.info("Initialized " + defaultProcessors.size() + " processors.");
	}

	protected String resolve(String string, Properties properties) {
		if (string == null) {
			return null;
		} else {
			return helper.replacePlaceholders(string, properties);
		}
	}

	public boolean isResolvePlaceholders() {
		return resolvePlaceholders;
	}

	public void setResolvePlaceholders(boolean resolvePlaceholders) {
		this.resolvePlaceholders = resolvePlaceholders;
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
}
