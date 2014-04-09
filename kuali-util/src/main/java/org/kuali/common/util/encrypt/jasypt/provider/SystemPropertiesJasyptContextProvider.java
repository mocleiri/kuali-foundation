package org.kuali.common.util.encrypt.jasypt.provider;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

public final class SystemPropertiesJasyptContextProvider extends AbstractJasyptContextProvider {

	@Override
	protected String getValueFromSource(String key) {
		return System.getProperty(checkNotBlank(key, "key"));
	}
}
