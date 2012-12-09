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
	String globalPropertiesOverrideMode = Constants.DEFAULT_GLOBAL_PROPERTIES_MODE.name();
	String style = PropertyStyle.NORMAL.name();
	String encryptionMode = PropertyEncryptionMode.NONE.name();
	String encryptionStrength = EncryptionStrength.BASIC.name();
	String encryptionPassword;
	String organizationGroupId;
	String groupId;
	String version;
	String encoding;
	boolean resolvePlaceholders;
	String prefix;
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
			processors.add(new GlobalOverrideProcessor(GlobalPropertiesMode.valueOf(globalPropertiesOverrideMode)));
		}

		// None of the processors below this should add new properties
		if (resolvePlaceholders) {
			Assert.notNull(helper, "helper is null");
			processors.add(new ResolvePlaceholdersProcessor(helper, GlobalPropertiesMode.valueOf(globalPropertiesOverrideMode)));
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
		if (organizationGroupId != null && groupId != null) {
			processors.add(new GroupCodeProcessor(organizationGroupId, groupId));
		}

		if (groupId != null) {
			processors.add(new PathProcessor(groupId));
		}

		if (version != null) {
			processors.add(new VersionProcessor(version));
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

	protected GlobalPropertiesMode getResolvedGlobalPropertiesOverwriteMode(Properties properties, String globalPropertiesOverwriteMode) {
		Properties global = PropertyUtils.getProperties(properties, Constants.DEFAULT_GLOBAL_PROPERTIES_MODE);
		String newGlobalPropertiesOverwriteMode = getResolvedString(global, globalPropertiesOverwriteMode);
		if (!StringUtils.equals(newGlobalPropertiesOverwriteMode, globalPropertiesOverwriteMode)) {
			logger.info("Resolved global properties overwrite mode [{}]->[{}]", globalPropertiesOverwriteMode, newGlobalPropertiesOverwriteMode);
		}
		return GlobalPropertiesMode.valueOf(newGlobalPropertiesOverwriteMode);
	}

	@Override
	public void initialize(Properties properties) {
		GlobalPropertiesMode mode = getResolvedGlobalPropertiesOverwriteMode(properties, globalPropertiesOverrideMode);
		Properties global = PropertyUtils.getProperties(properties, mode);
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

	public String getOrganizationGroupId() {
		return organizationGroupId;
	}

	public void setOrganizationGroupId(String organizationGroupIdProperty) {
		this.organizationGroupId = organizationGroupIdProperty;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupIdProperty) {
		this.groupId = groupIdProperty;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String versionProperty) {
		this.version = versionProperty;
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
}
