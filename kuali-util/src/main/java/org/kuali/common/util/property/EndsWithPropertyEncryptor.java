package org.kuali.common.util.property;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndsWithPropertyEncryptor extends DefaultPropertyEncryptor {

	private static final Logger logger = LoggerFactory.getLogger(EndsWithPropertyEncryptor.class);

	private static final String DEFAULT_ENCRYPTED_PROPERTY_SUFFIX = ".encrypted";

	String encryptedSuffix = DEFAULT_ENCRYPTED_PROPERTY_SUFFIX;
	boolean quiet;

	@Override
	protected List<String> getDecryptKeys(Properties properties) {
		List<String> keys = super.getDecryptKeys(properties);
		Iterator<String> itr = keys.iterator();
		for (String key : keys) {
			if (!key.endsWith(encryptedSuffix)) {
				itr.remove();
			}
		}
		return keys;
	}

	@Override
	protected void setDecryptedProperty(Properties properties, String key, String decryptedValue) {
		int beginIndex = 0;
		int endIndex = key.length() - encryptedSuffix.length();
		String newKey = key.substring(beginIndex, endIndex);
		String originalValue = properties.getProperty(newKey);
		if (!StringUtils.isBlank(originalValue)) {
			logger.warn("Overwriting existing property value '{}'", newKey);
		}
		if (!quiet) {
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

}
