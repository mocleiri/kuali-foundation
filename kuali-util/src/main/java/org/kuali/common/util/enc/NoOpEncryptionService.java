package org.kuali.common.util.enc;


public final class NoOpEncryptionService implements EncryptionService {

	@Override
	public String encrypt(String string) {
		return string;
	}

	@Override
	public String decrypt(String string) {
		return string;
	}

}
