package org.kuali.common.util.encrypt.provider;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

public final class SystemPropertiesEncryptionContextProvider extends AbstractEncryptionContextProvider {

	public SystemPropertiesEncryptionContextProvider() {
		super("enc.password", "enc.strength");
	}

	@Override
	protected String getValueFromSource(String key) {
		return System.getProperty(checkNotBlank(key, "key"));
	}
}
