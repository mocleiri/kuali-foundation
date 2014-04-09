package org.kuali.common.util.encrypt.jasypt;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

public final class EnvironmentVariableJasyptContextProvider extends AbstractJasyptContextProvider {

	@Override
	protected String getValueFromSource(String key) {
		String environmentVariableKey = checkNotBlank(key, "key").replace('.', '_').toUpperCase();
		return System.getenv(environmentVariableKey);
	}

}
