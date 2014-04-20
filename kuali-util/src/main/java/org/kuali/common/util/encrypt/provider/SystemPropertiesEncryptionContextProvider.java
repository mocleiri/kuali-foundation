package org.kuali.common.util.encrypt.provider;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

public final class SystemPropertiesEncryptionContextProvider extends AbstractEncryptionContextProvider {

	public SystemPropertiesEncryptionContextProvider(String passwordKey, String strengthKey) {
		super(passwordKey, strengthKey);
	}

	@Override
	protected String getValueFromSource(String key) {
		return System.getProperty(checkNotBlank(key, "key"));
	}
}
