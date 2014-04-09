package org.kuali.common.util.encrypt.provider;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

public final class EnvironmentVariableEncryptionContextProvider extends AbstractEncryptionContextProvider {

	@Override
	protected String getValueFromSource(String key) {
		String environmentVariableKey = checkNotBlank(key, "key").replace('.', '_').toUpperCase();
		return System.getenv(environmentVariableKey);
	}

}
