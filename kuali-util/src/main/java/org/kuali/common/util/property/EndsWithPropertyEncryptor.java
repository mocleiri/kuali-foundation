package org.kuali.common.util.property;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class EndsWithPropertyEncryptor extends DefaultPropertyEncryptor {

	private static final Logger logger = LoggerFactory.getLogger(EndsWithPropertyEncryptor.class);

	private static final String DEFAULT_ENCRYPTED_PROPERTY_SUFFIX = ".encrypted";

	String encryptedSuffix = DEFAULT_ENCRYPTED_PROPERTY_SUFFIX;
	boolean removeUnencrypted = true;
	boolean removeEncrypted = true;

	@Override
	public void decrypt(Properties properties) {
		Assert.notNull(encryptor, "encryptor is null");
		int suffixLength = encryptedSuffix.length();
		List<String> encryptedKeys = PropertyUtils.getEndsWithKeys(properties, encryptedSuffix);
		for (String encryptedKey : encryptedKeys) {
			String encryptedValue = properties.getProperty(encryptedKey);
			logger.debug("Decrypting [{}={}]", encryptedKey, encryptedValue);
			String decryptedValue = encryptor.decrypt(encryptedValue);
			String newKey = encryptedKey.substring(0, encryptedKey.length() - suffixLength);
			properties.setProperty(newKey, decryptedValue);
			if (removeEncrypted) {
				properties.remove(encryptedKey);
			}
		}
	}

	@Override
	public void encrypt(Properties properties) {
		Assert.notNull(encryptor, "encryptor is null");
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String value = properties.getProperty(key);
			logger.debug("Encrypting [{}]", key);
			String encryptedValue = encryptor.encrypt(value);
			String newKey = key + encryptedSuffix;
			properties.setProperty(newKey, encryptedValue);
			if (removeUnencrypted) {
				properties.remove(key);
			}
		}
	}

	public String getEncryptedSuffix() {
		return encryptedSuffix;
	}

	public void setEncryptedSuffix(String encryptedSuffix) {
		this.encryptedSuffix = encryptedSuffix;
	}

	public boolean isRemoveUnencrypted() {
		return removeUnencrypted;
	}

	public void setRemoveUnencrypted(boolean removeUnencrypted) {
		this.removeUnencrypted = removeUnencrypted;
	}

	public boolean isRemoveEncrypted() {
		return removeEncrypted;
	}

	public void setRemoveEncrypted(boolean removeEncrypted) {
		this.removeEncrypted = removeEncrypted;
	}

}
