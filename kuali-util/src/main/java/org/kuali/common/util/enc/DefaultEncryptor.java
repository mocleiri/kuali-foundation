package org.kuali.common.util.enc;

import static org.kuali.common.util.enc.EncStrength.DEFAULT_ENCRYPTION_STRENGTH;
import static org.kuali.common.util.enc.EncUtils.getTextEncryptor;

import org.jasypt.util.text.TextEncryptor;

public final class DefaultEncryptor implements Encryptor {

	public DefaultEncryptor(String password) {
		this(password, DEFAULT_ENCRYPTION_STRENGTH);
	}

	public DefaultEncryptor(String password, EncStrength strength) {
		this.encryptor = getTextEncryptor(password, strength);
	}

	private final TextEncryptor encryptor;

	@Override
	public String encrypt(String text) {
		return encryptor.encrypt(text);
	}

	@Override
	public String decrypt(String text) {
		return encryptor.decrypt(text);
	}

}
