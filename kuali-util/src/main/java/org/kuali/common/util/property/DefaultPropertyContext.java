package org.kuali.common.util.property;

import java.util.List;

import org.kuali.common.util.EncryptionStrength;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultPropertyContext implements PropertyContext {

	String encoding;
	List<String> includes;
	List<String> excludes;
	boolean includeEnvironmentVariables;
	boolean includeSystemProperties;
	boolean resolvePlaceholders;
	String prefix;
	PropertyStyle style = PropertyStyle.NORMAL;
	PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
	PropertyEncMode encryptionMode = PropertyEncMode.NONE;
	EncryptionStrength encryptionStrength = EncryptionStrength.BASIC;
	String encryptionPassword;
	PropertyEncryptor encryptor = new EndsWithPropertyEncryptor();

	@Override
	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	@Override
	public List<String> getIncludes() {
		return includes;
	}

	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}

	@Override
	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
	}

	@Override
	public boolean isIncludeEnvironmentVariables() {
		return includeEnvironmentVariables;
	}

	public void setIncludeEnvironmentVariables(boolean includeEnvironmentVariables) {
		this.includeEnvironmentVariables = includeEnvironmentVariables;
	}

	@Override
	public boolean isIncludeSystemProperties() {
		return includeSystemProperties;
	}

	public void setIncludeSystemProperties(boolean includeSystemProperties) {
		this.includeSystemProperties = includeSystemProperties;
	}

	@Override
	public boolean isResolvePlaceholders() {
		return resolvePlaceholders;
	}

	public void setResolvePlaceholders(boolean resolvePlaceholders) {
		this.resolvePlaceholders = resolvePlaceholders;
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public PropertyStyle getStyle() {
		return style;
	}

	public void setStyle(PropertyStyle style) {
		this.style = style;
	}

	@Override
	public PropertyPlaceholderHelper getHelper() {
		return helper;
	}

	public void setHelper(PropertyPlaceholderHelper helper) {
		this.helper = helper;
	}

	@Override
	public PropertyEncMode getEncryptionMode() {
		return encryptionMode;
	}

	public void setEncryptionMode(PropertyEncMode encryptionMode) {
		this.encryptionMode = encryptionMode;
	}

	@Override
	public EncryptionStrength getEncryptionStrength() {
		return encryptionStrength;
	}

	public void setEncryptionStrength(EncryptionStrength encryptionStrength) {
		this.encryptionStrength = encryptionStrength;
	}

	@Override
	public String getEncryptionPassword() {
		return encryptionPassword;
	}

	public void setEncryptionPassword(String encryptionPassword) {
		this.encryptionPassword = encryptionPassword;
	}

	@Override
	public PropertyEncryptor getEncryptor() {
		return encryptor;
	}

	public void setEncryptor(PropertyEncryptor encryptor) {
		this.encryptor = encryptor;
	}

}
