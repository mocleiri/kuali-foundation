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
import org.kuali.common.util.property.processor.GroupCodeProcessor;
import org.kuali.common.util.property.processor.NoOpProcessor;
import org.kuali.common.util.property.processor.PathProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.property.processor.ReformatKeysAsEnvVarsProcessor;
import org.kuali.common.util.property.processor.ResolvePlaceholdersProcessor;
import org.kuali.common.util.property.processor.VersionProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultPropertyContext implements PropertyContext {

	private static final Logger logger = LoggerFactory.getLogger(DefaultPropertyContext.class);

	PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	GlobalPropertiesMode globalPropertiesOverrideMode = GlobalPropertiesMode.BOTH;
	String style = PropertyStyle.NORMAL.name();
	String encryptionMode = PropertyEncryptionMode.NONE.name();
	String encryptionStrength = EncryptionStrength.BASIC.name();
	String organizationGroupIdProperty = Constants.DEFAULT_ORGANIZATION_GROUP_ID_PROPERTY;
	String groupIdProperty = Constants.DEFAULT_GROUP_ID_PROPERTY;
	String versionProperty = Constants.DEFAULT_VERSION_PROPERTY;
	String encoding;
	boolean resolvePlaceholders;
	String prefix;
	String encryptionPassword;
	List<PropertyProcessor> processors;
	Properties properties;

	protected List<PropertyProcessor> getDefaultProcessors() {
		List<PropertyProcessor> processors = new ArrayList<PropertyProcessor>();

		// Add any properties supplied directly to this bean
		if (properties != null) {
			processors.add(new AddPropertiesProcessor(properties));
		}

		// Add GAV related processing
		processors.addAll(getGavProcessors());

		// Decrypt/encrypt as appropriate
		if (encryptionMode != null) {
			processors.add(getEncProcessor(encryptionMode, encryptionStrength, encryptionPassword));
		}

		// Make sure system/environment properties override everything else
		if (globalPropertiesOverrideMode != null) {
			processors.add(new GlobalOverrideProcessor(globalPropertiesOverrideMode));
		}

		// None of the processors below this should add new properties
		if (resolvePlaceholders) {
			Assert.notNull(helper, "helper is null");
			processors.add(new ResolvePlaceholdersProcessor(helper));
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

	protected List<PropertyProcessor> getGavProcessors() {
		List<PropertyProcessor> processors = new ArrayList<PropertyProcessor>();
		if (organizationGroupIdProperty != null && groupIdProperty != null) {
			processors.add(new GroupCodeProcessor(organizationGroupIdProperty, groupIdProperty));
		}

		if (groupIdProperty != null) {
			processors.add(new PathProcessor(groupIdProperty));
		}

		if (versionProperty != null) {
			processors.add(new VersionProcessor(versionProperty));
		}
		return processors;
	}

	protected PropertyProcessor getStyleProcessor(String style) {
		switch (PropertyStyle.valueOf(style)) {
		case NORMAL:
			return new NoOpProcessor();
		case ENVIRONMENT_VARIABLE:
			return new ReformatKeysAsEnvVarsProcessor();
		default:
			throw new IllegalArgumentException("Property style " + style + " is unknown");
		}
	}

	protected PropertyProcessor getEncProcessor(String mode, String strength, String password) {
		switch (PropertyEncryptionMode.valueOf(mode)) {
		case NONE:
			return new NoOpProcessor();
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

	public String getOrganizationGroupIdProperty() {
		return organizationGroupIdProperty;
	}

	public void setOrganizationGroupIdProperty(String organizationGroupIdProperty) {
		this.organizationGroupIdProperty = organizationGroupIdProperty;
	}

	public String getGroupIdProperty() {
		return groupIdProperty;
	}

	public void setGroupIdProperty(String groupIdProperty) {
		this.groupIdProperty = groupIdProperty;
	}

	public String getVersionProperty() {
		return versionProperty;
	}

	public void setVersionProperty(String versionProperty) {
		this.versionProperty = versionProperty;
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

	public GlobalPropertiesMode getGlobalPropertiesOverrideMode() {
		return globalPropertiesOverrideMode;
	}

	public void setGlobalPropertiesOverrideMode(GlobalPropertiesMode globalPropertiesOverrideMode) {
		this.globalPropertiesOverrideMode = globalPropertiesOverrideMode;
	}

}
