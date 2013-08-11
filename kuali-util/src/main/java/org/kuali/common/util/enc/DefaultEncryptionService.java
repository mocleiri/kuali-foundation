package org.kuali.common.util.enc;

import java.util.Properties;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.Assert;

public final class DefaultEncryptionService implements EncryptionService {

	private final TextEncryptor encryptor;

	public DefaultEncryptionService(TextEncryptor encryptor) {
		Assert.noNulls(encryptor);
		this.encryptor = encryptor;
	}

	@Override
	public String encrypt(String string) {
		return encryptor.encrypt(string);
	}

	@Override
	public String decrypt(String string) {
		return encryptor.decrypt(string);
	}

	@Override
	public void decrypt(Properties properties) {
	}

	@Override
	public void encrypt(Properties properties) {
	}

}
