package org.kuali.common.util.encrypt;

// Singleton enum pattern
public enum NoOpEncryptor implements Encryptor {
	INSTANCE;

	@Override
	public String encrypt(String text) {
		return text;
	}

	@Override
	public String decrypt(String text) {
		return text;
	}

}
