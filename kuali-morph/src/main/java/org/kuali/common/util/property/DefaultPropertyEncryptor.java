package org.kuali.common.util.property;

import java.util.List;
import java.util.Properties;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultPropertyEncryptor implements PropertyEncryptor {

	private static final Logger logger = LoggerFactory.getLogger(DefaultPropertyEncryptor.class);

	TextEncryptor encryptor;

	@Override
	public void decrypt(Properties properties) {
		List<String> keys = getDecryptKeys(properties);
		for (String key : keys) {
			String encryptedValue = properties.getProperty(key);
			logger.debug("Decrypting [{}={}]", key, encryptedValue);
			String decryptedValue = encryptor.decrypt(encryptedValue);
			setDecryptedProperty(properties, key, decryptedValue);
		}
	}

	@Override
	public void encrypt(Properties properties) {
		List<String> keys = getEncryptKeys(properties);
		for (String key : keys) {
			String decryptedValue = properties.getProperty(key);
			logger.debug("Decrypting [{}={}]", key, decryptedValue);
			String encryptedValue = encryptor.encrypt(decryptedValue);
			setEncryptedProperty(properties, key, encryptedValue);
		}
	}

	protected void setEncryptedProperty(Properties properties, String key, String encryptedValue) {
		properties.setProperty(key, encryptedValue);
	}

	protected void setDecryptedProperty(Properties properties, String key, String decryptedValue) {
		properties.setProperty(key, decryptedValue);
	}

	protected List<String> getEncryptKeys(Properties properties) {
		return PropertyUtils.getSortedKeys(properties);
	}

	protected List<String> getDecryptKeys(Properties properties) {
		return PropertyUtils.getSortedKeys(properties);
	}

	public TextEncryptor getEncryptor() {
		return encryptor;
	}

	public void setEncryptor(TextEncryptor encryptor) {
		this.encryptor = encryptor;
	}
}
