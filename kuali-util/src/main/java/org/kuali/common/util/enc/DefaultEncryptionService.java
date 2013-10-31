package org.kuali.common.util.enc;

import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.SetUtils;

public final class DefaultEncryptionService implements EncryptionService {

	private final TextEncryptor encryptor;

	public DefaultEncryptionService(TextEncryptor encryptor) {
		Assert.noNulls(encryptor);
		this.encryptor = encryptor;
	}

	@Override
	public String encrypt(String plainText) {
		String encryptedText = encryptor.encrypt(plainText);
		return EncUtils.wrap(encryptedText);
	}

	@Override
	public String decrypt(String encryptedText) {
		String unwrapped = EncUtils.unwrap(encryptedText);
		return encryptor.decrypt(unwrapped);
	}

	/**
	 * Decrypt any encrypted property values.
	 */
	@Override
	public void decrypt(Properties properties) {
		List<String> keys = PropertyUtils.getEncryptedKeys(properties);
		for (String key : keys) {
			String encrypted = properties.getProperty(key);
			String decrypted = decrypt(encrypted);
			properties.setProperty(key, decrypted);
		}
	}

	/**
	 * Encrypt any property values that are not already encrypted.
	 */
	@Override
	public void encrypt(Properties properties) {
		Set<String> allKeys = new HashSet<String>(PropertyUtils.getSortedKeys(properties));
		Set<String> encKeys = new HashSet<String>(PropertyUtils.getEncryptedKeys(properties));
		Set<String> keys = SetUtils.difference(allKeys, encKeys);
		for (String key : keys) {
			String plaintext = properties.getProperty(key);
			String encrypted = encrypt(plaintext);
			properties.setProperty(key, encrypted);
		}
	}

	public TextEncryptor getEncryptor() {
		return encryptor;
	}

}
