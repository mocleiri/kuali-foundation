package org.kuali.common.util.property;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.EncUtils;
import org.kuali.common.util.EncryptionStrength;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.modifier.AddEnvPropertiesModifier;
import org.kuali.common.util.property.modifier.AddPrefixModifier;
import org.kuali.common.util.property.modifier.AddPropertiesModifier;
import org.kuali.common.util.property.modifier.AddSystemPropertiesModifier;
import org.kuali.common.util.property.modifier.EndsWithDecryptModifier;
import org.kuali.common.util.property.modifier.EndsWithEncryptModifier;
import org.kuali.common.util.property.modifier.PathModifier;
import org.kuali.common.util.property.modifier.PropertyModifier;
import org.kuali.common.util.property.modifier.ReformatKeysAsEnvVarsModifier;
import org.kuali.common.util.property.modifier.ResolvePlaceholdersModifier;
import org.kuali.common.util.property.modifier.TrimModifier;
import org.kuali.common.util.property.modifier.VersionModifier;
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
	PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(Constants.DEFAULT_PLACEHOLDER_PREFIX, Constants.DEFAULT_PLACEHOLDER_SUFFIX);
	PropertyEncryptionMode encryptionMode = PropertyEncryptionMode.NONE;
	EncryptionStrength encryptionStrength = EncryptionStrength.BASIC;
	String encryptionPassword;
	List<PropertyModifier> modifiers;
	List<String> pathProperties;
	List<String> versionProperties;
	Properties properties;
	GlobalPropertiesMode globalPropertiesOverrideMode = GlobalPropertiesMode.BOTH;

	protected List<PropertyModifier> getDefaultModifiers() {
		List<PropertyModifier> defaultModifiers = new ArrayList<PropertyModifier>();

		if (properties != null) {
			defaultModifiers.add(new AddPropertiesModifier(properties));
		}

		if (addEnvironmentVariables) {
			defaultModifiers.add(new AddEnvPropertiesModifier());
		}

		if (addSystemProperties) {
			defaultModifiers.add(new AddSystemPropertiesModifier());
		}

		if (resolvePlaceholders) {
			Assert.notNull(helper, "helper is null");
			defaultModifiers.add(new ResolvePlaceholdersModifier(helper));
		}

		if (!CollectionUtils.isEmpty(pathProperties)) {
			defaultModifiers.add(new PathModifier(pathProperties));
		}

		if (!CollectionUtils.isEmpty(versionProperties)) {
			defaultModifiers.add(new VersionModifier(versionProperties));
		}

		addEncModifier(defaultModifiers);

		if (!StringUtils.isBlank(prefix)) {
			defaultModifiers.add(new AddPrefixModifier(prefix));
		}

		addStyleModifier(defaultModifiers);

		boolean trim = !CollectionUtils.isEmpty(includes) || !CollectionUtils.isEmpty(excludes);
		if (trim) {
			defaultModifiers.add(new TrimModifier(includes, excludes));
		}

		return defaultModifiers;
	}

	protected void addStyleModifier(List<PropertyModifier> defaultModifiers) {
		if (style == null) {
			return;
		}
		switch (style) {
		case NORMAL:
			return;
		case ENVIRONMENT_VARIABLE:
			defaultModifiers.add(new ReformatKeysAsEnvVarsModifier());
			return;
		default:
			throw new IllegalArgumentException(style + " is unknown");
		}
	}

	protected void addEncModifier(List<PropertyModifier> defaultModifiers) {
		if (encryptionMode == null) {
			return;
		}
		switch (encryptionMode) {
		case NONE:
			return;
		case ENCRYPT:
			TextEncryptor encryptor = EncUtils.getTextEncryptor(encryptionStrength, encryptionPassword);
			defaultModifiers.add(new EndsWithEncryptModifier(encryptor));
			return;
		case DECRYPT:
			TextEncryptor decryptor = EncUtils.getTextEncryptor(encryptionStrength, encryptionPassword);
			defaultModifiers.add(new EndsWithDecryptModifier(decryptor));
			return;
		default:
			throw new IllegalArgumentException(encryptionMode + " is unknown");
		}
	}

	@Override
	public void beforeModify(Properties properties) {
		Properties global = PropertyUtils.getProperties(globalPropertiesOverrideMode);
		resolveInternalStrings(global);
		List<PropertyModifier> defaultModifiers = getDefaultModifiers();
		if (this.modifiers == null) {
			this.modifiers = defaultModifiers;
		} else {
			this.modifiers.addAll(0, defaultModifiers);
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
		resolveInternalList(properties, pathProperties);
		resolveInternalList(properties, versionProperties);
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
	public List<PropertyModifier> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<PropertyModifier> modifiers) {
		this.modifiers = modifiers;
	}

	public List<String> getPathProperties() {
		return pathProperties;
	}

	public void setPathProperties(List<String> pathProperties) {
		this.pathProperties = pathProperties;
	}

	public List<String> getVersionProperties() {
		return versionProperties;
	}

	public void setVersionProperties(List<String> versionProperties) {
		this.versionProperties = versionProperties;
	}

}
