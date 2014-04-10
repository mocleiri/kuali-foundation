package org.kuali.common.util.encrypt.provider;

import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.encrypt.Encryption.ENCRYPTION_STRENGTH_KEY;

public final class EnvironmentVariableEncryptionContextProvider extends AbstractEncryptionContextProvider {

	public EnvironmentVariableEncryptionContextProvider(String encryptionPasswordKey) {
		super(encryptionPasswordKey, ENCRYPTION_STRENGTH_KEY);
	}

	@Override
	protected String getValueFromSource(String key) {
		String environmentVariableKey = checkNotBlank(key, "key").replace('.', '_').toUpperCase();
		return System.getenv(environmentVariableKey);
	}

}
