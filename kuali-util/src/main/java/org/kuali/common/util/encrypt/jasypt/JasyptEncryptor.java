package org.kuali.common.util.encrypt.jasypt;

import static org.kuali.common.util.enc.EncStrength.DEFAULT_ENCRYPTION_STRENGTH;
import static org.kuali.common.util.encrypt.jasypt.Jasypt.buildTextEncryptor;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.enc.EncStrength;
import org.kuali.common.util.encrypt.Encryptor;

public final class JasyptEncryptor implements Encryptor {

	public JasyptEncryptor(String password) {
		this(password, DEFAULT_ENCRYPTION_STRENGTH);
	}

	public JasyptEncryptor(String password, EncStrength strength) {
		this.encryptor = buildTextEncryptor(password, strength);
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
