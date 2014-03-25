package org.kuali.common.util.enc;

import java.util.Properties;

public final class NoOpEncryptionService implements EncryptionService {

	public static final NoOpEncryptionService INSTANCE = new NoOpEncryptionService();

	private NoOpEncryptionService() {
	}

	@Override
	public String encrypt(String string) {
		return string;
	}

	@Override
	public String decrypt(String string) {
		return string;
	}

	@Override
	public void decrypt(Properties properties) {
	}

	@Override
	public void encrypt(Properties properties) {
	}

}
