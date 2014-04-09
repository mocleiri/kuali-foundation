package org.kuali.common.util.encrypt;

public class NoOpEncryptor implements Encryptor {

	@Override
	public String encrypt(String text) {
		return text;
	}

	@Override
	public String decrypt(String text) {
		return text;
	}

}
