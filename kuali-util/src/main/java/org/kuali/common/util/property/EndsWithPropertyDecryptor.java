package org.kuali.common.util.property;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndsWithPropertyDecryptor extends DefaultPropertyEncryptor {

	private static final Logger logger = LoggerFactory.getLogger(EndsWithPropertyDecryptor.class);

	private static final String DEFAULT_ENCRYPTED_PROPERTY_SUFFIX = ".encrypted";

	String encryptedSuffix = DEFAULT_ENCRYPTED_PROPERTY_SUFFIX;

	@Override
	public void decrypt(Properties properties) {
		int suffixLength = encryptedSuffix.length();
		List<String> encryptedKeys = getEncryptedKeys(properties);
		for (String encryptedKey : encryptedKeys) {
			String encryptedValue = properties.getProperty(encryptedKey);
			logger.debug("Decrypting [{}={}]", encryptedKey, encryptedValue);
			String decryptedValue = encryptor.decrypt(encryptedValue);
			String newKey = encryptedKey.substring(0, encryptedKey.length() - suffixLength);
			properties.setProperty(newKey, decryptedValue);
			properties.remove(encryptedKey);
		}
	}

	@Override
	public void encrypt(Properties properties) {
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String value = properties.getProperty(key);
			logger.debug("Encrypting [{}={}]", key, value);
			String encryptedValue = encryptor.encrypt(value);
			String newKey = key + encryptedSuffix;
			properties.setProperty(newKey, encryptedValue);
			properties.remove(key);
		}
	}

	protected List<String> getEncryptedKeys(Properties properties) {
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		List<String> encryptedKeys = new ArrayList<String>();
		for (String key : keys) {
			if (isEncrypted(key)) {
				encryptedKeys.add(key);
			}
		}
		return encryptedKeys;
	}

	protected boolean isEncrypted(String key) {
		return StringUtils.endsWith(key, encryptedSuffix);
	}

	public String getEncryptedSuffix() {
		return encryptedSuffix;
	}

	public void setEncryptedSuffix(String encryptedSuffix) {
		this.encryptedSuffix = encryptedSuffix;
	}

}
