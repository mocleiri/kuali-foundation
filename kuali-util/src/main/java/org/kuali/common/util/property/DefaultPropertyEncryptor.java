package org.kuali.common.util.property;

import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.jasypt.util.text.BasicTextEncryptor;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class DefaultPropertyEncryptor implements PropertyEncryptor {

	private static final Logger logger = LoggerFactory.getLogger(DefaultPropertyEncryptor.class);

	private static final String DEFAULT_ENCRYPTED_PROPERTY_SUFFIX = ".encrypted";

	String encryptedSuffix = DEFAULT_ENCRYPTED_PROPERTY_SUFFIX;
	List<String> propertiesToEncrypt;

	String password;
	boolean quiet;

	@Override
	public void decrypt(Properties properties) {
		Assert.notNull("Password is null", password);
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(password);
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			if (!key.endsWith(encryptedSuffix)) {
				continue;
			}
			String encryptedValue = properties.getProperty(key);
			String decryptedValue = encryptor.decrypt(encryptedValue);
			int beginIndex = 0;
			int endIndex = key.length() - encryptedSuffix.length();
			String newKey = key.substring(beginIndex, endIndex);
			setProperty(properties, newKey, decryptedValue);
		}
	}

	protected void setProperty(Properties properties, String key, String value) {
		String existingValue = properties.getProperty(key);
		if (!StringUtils.isBlank(existingValue)) {
			logger.warn("Overwriting existing value for property [{}]", key);
		}
		if (!quiet) {
			logger.info("Setting property " + key);
		}
		properties.setProperty(key, value);
	}

	@Override
	public void encrypt(Properties properties) {
		Assert.notNull("Password is null", password);
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(password);
		for (String key : propertiesToEncrypt) {
			String decryptedValue = properties.getProperty(key);
			String encryptedValue = encryptor.encrypt(decryptedValue);
			String newKey = key + encryptedSuffix;
			setProperty(properties, newKey, encryptedValue);
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
