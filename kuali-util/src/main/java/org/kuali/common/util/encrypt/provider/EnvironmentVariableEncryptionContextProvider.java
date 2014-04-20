package org.kuali.common.util.encrypt.provider;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

public final class EnvironmentVariableEncryptionContextProvider extends AbstractEncryptionContextProvider {

	public EnvironmentVariableEncryptionContextProvider() {
		super("ENC_PASSWORD","ENC_STRENGTH");
	}

	@Override
	protected String getValueFromSource(String key) {
		return System.getenv(checkNotBlank(key, "key"));
	}

}
