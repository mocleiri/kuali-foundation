package org.kuali.common.util.property;

import java.util.List;
import java.util.Properties;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.EncUtils;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class DefaultPropertyEncryptor implements PropertyEncryptor {

	private static final Logger logger = LoggerFactory.getLogger(DefaultPropertyEncryptor.class);

	TextEncryptor encryptor;

	@Override
	public void decrypt(Properties properties) {
		Assert.notNull(encryptor, "encryptor is null");
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String encryptedValue = properties.getProperty(key);
			logger.debug("Decrypting [{}={}]", key, encryptedValue);
			String decryptedValue = encryptor.decrypt(encryptedValue);
			properties.setProperty(key, decryptedValue);
		}
	}

	@Override
	public void encrypt(Properties properties) {
		Assert.notNull(encryptor, "encryptor is null");
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String decryptedValue = properties.getProperty(key);
			logger.debug("Encrypting [{}={}]", key, decryptedValue);
			String encryptedValue = encryptor.encrypt(decryptedValue);
			properties.setProperty(key, encryptedValue);
		}
	}

	@Override
	public void initialize(PropertyEncryptorContext context) {
		this.encryptor = EncUtils.getTextEncryptor(context.getStrength(), context.getPassword());
	}
}
