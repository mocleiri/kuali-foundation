package org.kuali.common.util.encrypt.provider;

import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.encrypt.Encryption.ENCRYPTION_STRENGTH_KEY;

public final class SystemPropertiesEncryptionContextProvider extends AbstractEncryptionContextProvider {

	public SystemPropertiesEncryptionContextProvider(String encryptionPasswordKey) {
		super(encryptionPasswordKey, ENCRYPTION_STRENGTH_KEY);
	}

	@Override
	protected String getValueFromSource(String key) {
		return System.getProperty(checkNotBlank(key, "key"));
	}
}
