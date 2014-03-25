package org.kuali.common.util.enc;

import java.util.List;
import java.util.Properties;
import java.util.SortedSet;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;

import com.google.common.collect.Sets;

public final class DefaultEncryptionService implements EncryptionService {

	private final TextEncryptor encryptor;

	public DefaultEncryptionService(TextEncryptor encryptor) {
		Assert.noNulls(encryptor);
		this.encryptor = encryptor;
	}

	@Override
	public String encrypt(String text) {
		if (EncUtils.isEncrypted(text)) {
			return text; // It's already encrypted, just return it
		}
		Assert.notEncrypted(text);
		String encryptedText = encryptor.encrypt(text);
		return EncUtils.wrap(encryptedText);
	}

	@Override
	public String decrypt(String text) {
		if (!EncUtils.isEncrypted(text)) {
			return text; // It's not encrypted, just return it
		}
		Assert.encrypted(text);
		String unwrapped = EncUtils.unwrap(text);
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
		SortedSet<String> allKeys = Sets.newTreeSet(properties.stringPropertyNames());
		SortedSet<String> encKeys = Sets.newTreeSet(PropertyUtils.getEncryptedKeys(properties));
		SortedSet<String> keys = Sets.newTreeSet(Sets.difference(allKeys, encKeys));
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
