package org.kuali.common.util.enc;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.Assert;

public class DefaultEncryptionService implements EncryptionService {

	private final TextEncryptor encryptor;

	public DefaultEncryptionService(TextEncryptor encryptor) {
		Assert.noNulls(encryptor);
		this.encryptor = encryptor;
	}

	public DefaultEncryptionService(String password) {
		this(password, EncUtils.DEFAULT_ENC_STRENGTH);
	}

	public DefaultEncryptionService(String password, EncStrength strength) {
		this(EncUtils.getTextEncryptor(strength, password));
	}

	@Override
	public String encrypt(String string) {
		return encryptor.encrypt(string);
	}

	@Override
	public String decrypt(String string) {
		return encryptor.decrypt(string);
	}

	public TextEncryptor getEncryptor() {
		return encryptor;
	}

}
