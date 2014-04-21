package org.kuali.common.util.encrypt;

// Singleton enum pattern
public enum NoOpEncryptor implements Encryptor {
	INSTANCE;

	@Override
	public String encrypt(String plaintext) {
		return plaintext;
	}

	@Override
	public String decrypt(String encrypted) {
		return encrypted;
	}

}
