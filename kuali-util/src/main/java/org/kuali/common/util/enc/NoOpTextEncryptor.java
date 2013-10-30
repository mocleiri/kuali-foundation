package org.kuali.common.util.enc;

import org.jasypt.util.text.TextEncryptor;

public final class NoOpTextEncryptor implements TextEncryptor {

	public static final NoOpTextEncryptor INSTANCE = new NoOpTextEncryptor();

	private NoOpTextEncryptor() {
	}

	@Override
	public String encrypt(String message) {
		return message;
	}

	@Override
	public String decrypt(String encryptedMessage) {
		return encryptedMessage;
	}

}
