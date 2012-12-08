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
import org.kuali.common.util.property.processor.AddPropertiesProcessor;
import org.kuali.common.util.property.processor.EndsWithDecryptProcessor;
import org.kuali.common.util.property.processor.EndsWithEncryptProcessor;
import org.kuali.common.util.property.processor.GlobalOverrideProcessor;
import org.kuali.common.util.property.processor.PathProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.property.processor.ReformatKeysAsEnvVarsProcessor;
import org.kuali.common.util.property.processor.ResolvePlaceholdersProcessor;
import org.kuali.common.util.property.processor.TrimProcessor;
import org.kuali.common.util.property.processor.VersionProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultPropertyContext implements PropertyContext {

	private static final Logger logger = LoggerFactory.getLogger(DefaultPropertyContext.class);

	String encoding;
	List<String> includes;
	List<String> excludes;
	boolean addEnvironmentVariables;
	boolean addSystemProperties;
	boolean resolvePlaceholders;
	String prefix;
	PropertyStyle style = PropertyStyle.NORMAL;
	PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	String versionProperty = Constants.DEFAULT_VERSION_PROPERTY;
	String pathProperty = Constants.DEFAULT_PATH_PROPERTY;
	PropertyEncryptionMode encryptionMode = PropertyEncryptionMode.NONE;
	EncryptionStrength encryptionStrength = EncryptionStrength.BASIC;
	String encryptionPassword;
	List<PropertyProcessor> processors;
	Properties properties;
	GlobalPropertiesMode globalPropertiesOverrideMode = GlobalPropertiesMode.BOTH;

	protected List<PropertyProcessor> getDefaultProcessors() {
		List<PropertyProcessor> defaultProcessors = new ArrayList<PropertyProcessor>();

		if (properties != null) {
			defaultProcessors.add(new AddPropertiesProcessor(properties));
		}

		if (addEnvironmentVariables) {
			defaultProcessors.add(new AddPropertiesProcessor(PropertyUtils.getEnvAsProperties()));
		}

		if (addSystemProperties) {
			defaultProcessors.add(new AddPropertiesProcessor(System.getProperties()));
		}

		if (pathProperty != null) {
			defaultProcessors.add(new PathProcessor(pathProperty));
		}

		if (versionProperty != null) {
			defaultProcessors.add(new VersionProcessor(versionProperty));
		}

		addEncModifier(defaultProcessors);

		// Make sure system/environment properties override everything else
		if (globalPropertiesOverrideMode != null) {
			defaultProcessors.add(new GlobalOverrideProcessor(globalPropertiesOverrideMode));
		}

		// At this point no further properties will be added so we are safe to resolve place holders
		if (resolvePlaceholders) {
			Assert.notNull(helper, "helper is null");
			defaultProcessors.add(new ResolvePlaceholdersProcessor(helper));
		}

		if (!StringUtils.isBlank(prefix)) {
			defaultProcessors.add(new AddPrefixProcessor(prefix));
		}

		addStyleModifier(defaultProcessors);

		boolean trim = !CollectionUtils.isEmpty(includes) || !CollectionUtils.isEmpty(excludes);
		if (trim) {
			defaultProcessors.add(new TrimProcessor(includes, excludes));
		}

		return defaultProcessors;
	}

	protected void addStyleModifier(List<PropertyProcessor> defaultProcessors) {
		if (style == null) {
			return;
		}
		switch (style) {
		case NORMAL:
			return;
		case ENVIRONMENT_VARIABLE:
			defaultProcessors.add(new ReformatKeysAsEnvVarsProcessor());
			return;
		default:
			throw new IllegalArgumentException(style + " is unknown");
		}
	}

	protected void addEncModifier(List<PropertyProcessor> defaultProcessors) {
		if (encryptionMode == null) {
			return;
		}
		switch (encryptionMode) {
		case NONE:
			return;
		case ENCRYPT:
			TextEncryptor encryptor = EncUtils.getTextEncryptor(encryptionStrength, encryptionPassword);
			defaultProcessors.add(new EndsWithEncryptProcessor(encryptor));
			return;
		case DECRYPT:
			TextEncryptor decryptor = EncUtils.getTextEncryptor(encryptionStrength, encryptionPassword);
			defaultProcessors.add(new EndsWithDecryptProcessor(decryptor));
			return;
		default:
			throw new IllegalArgumentException("Encryption mode '" + encryptionMode + "' is unknown");
		}
	}

	@Override
	public void initialize(Properties properties) {
		Properties global = PropertyUtils.getProperties(properties, globalPropertiesOverrideMode);
		resolveInternalStrings(global);
		List<PropertyProcessor> defaultProcessors = getDefaultProcessors();
		if (processors == null) {
			processors = defaultProcessors;
		} else {
			processors.addAll(0, defaultProcessors);
		}
	}

	protected void resolveInternalStrings(Properties properties) {
		if (helper == null) {
			return;
		}

		String newPrefix = getResolvedString(properties, this.prefix);
		if (!StringUtils.equals(newPrefix, this.prefix)) {
			logger.info("Resolved prefix [{}]->[{}]", this.prefix, newPrefix);
			this.prefix = newPrefix;
		}
		String newEncryptionPassword = getResolvedString(properties, this.encryptionPassword);
		if (!StringUtils.equals(newEncryptionPassword, this.encryptionPassword)) {
			logger.info("Resolved encryption password");
			this.encryptionPassword = newEncryptionPassword;
		}
		resolveInternalList(properties, includes);
		resolveInternalList(properties, excludes);
	}

	protected void resolveInternalList(Properties properties, List<String> list) {
		if (list == null) {
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			String original = list.get(i);
			String resolved = helper.replacePlaceholders(original, properties);
			if (!StringUtils.equals(original, resolved)) {
				logger.info("Resolved [{}] -> [{}]", original, resolved);
				list.set(i, resolved);
			}
		}
	}

	protected String getResolvedString(Properties properties, String original) {
		if (original == null) {
			return null;
		} else {
			return helper.replacePlaceholders(original, properties);
		}
	}

	@Override
	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public List<String> getIncludes() {
		return includes;
	}

	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
	}

	public boolean isAddEnvironmentVariables() {
		return addEnvironmentVariables;
	}

	public void setAddEnvironmentVariables(boolean includeEnvironmentVariables) {
		this.addEnvironmentVariables = includeEnvironmentVariables;
	}

	public boolean isAddSystemProperties() {
		return addSystemProperties;
	}

	public void setAddSystemProperties(boolean includeSystemProperties) {
		this.addSystemProperties = includeSystemProperties;
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

	public PropertyStyle getStyle() {
		return style;
	}

	public void setStyle(PropertyStyle style) {
		this.style = style;
	}

	public PropertyPlaceholderHelper getHelper() {
		return helper;
	}

	public void setHelper(PropertyPlaceholderHelper helper) {
		this.helper = helper;
	}

	public PropertyEncryptionMode getEncryptionMode() {
		return encryptionMode;
	}

	public void setEncryptionMode(PropertyEncryptionMode encryptionMode) {
		this.encryptionMode = encryptionMode;
	}

	public EncryptionStrength getEncryptionStrength() {
		return encryptionStrength;
	}

	public void setEncryptionStrength(EncryptionStrength encryptionStrength) {
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

	public void setProcessors(List<PropertyProcessor> modifiers) {
		this.processors = modifiers;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public GlobalPropertiesMode getGlobalPropertiesOverrideMode() {
		return globalPropertiesOverrideMode;
	}

	public void setGlobalPropertiesOverrideMode(GlobalPropertiesMode globalPropertiesOverrideMode) {
		this.globalPropertiesOverrideMode = globalPropertiesOverrideMode;
	}

	public String getVersionProperty() {
		return versionProperty;
	}

	public void setVersionProperty(String versionProperty) {
		this.versionProperty = versionProperty;
	}

	public String getPathProperty() {
		return pathProperty;
	}

	public void setPathProperty(String pathProperty) {
		this.pathProperty = pathProperty;
	}

}
