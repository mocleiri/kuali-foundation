package org.kuali.common.util.property;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndsWithPropertyDecryptor extends DefaultPropertyEncryptor {

	private static final Logger logger = LoggerFactory.getLogger(EndsWithPropertyDecryptor.class);

	private static final String DEFAULT_ENCRYPTED_PROPERTY_SUFFIX = ".encrypted";

	String encryptedSuffix = DEFAULT_ENCRYPTED_PROPERTY_SUFFIX;
	boolean quiet;

	@Override
	protected List<String> getDecryptKeys(Properties properties) {
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		List<String> encryptedKeys = new ArrayList<String>();
		for (String key : keys) {
			if (!key.endsWith(encryptedSuffix)) {
				encryptedKeys.add(key);
			}
		}
		return encryptedKeys;
	}

	@Override
	protected void setDecryptedProperty(Properties properties, String key, String decryptedValue) {
		int beginIndex = 0;
		int endIndex = key.length() - encryptedSuffix.length();
		String newKey = key.substring(beginIndex, endIndex);
		String originalValue = properties.getProperty(newKey);
		if (!StringUtils.isBlank(originalValue)) {
			logger.warn("Overwriting existing property [{}]", newKey);
		} else if (!quiet) {
			logger.info("Setting property [{}]", newKey);
		}
		properties.setProperty(newKey, decryptedValue);
	}

	public String getEncryptedSuffix() {
		return encryptedSuffix;
	}

	public void setEncryptedSuffix(String encryptedSuffix) {
		this.encryptedSuffix = encryptedSuffix;
	}

	public boolean isQuiet() {
		return quiet;
	}

	public void setQuiet(boolean quiet) {
		this.quiet = quiet;
	}

}
