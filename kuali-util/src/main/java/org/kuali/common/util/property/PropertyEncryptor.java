package org.kuali.common.util.property;

import java.util.Properties;

public interface PropertyEncryptor {

	void encrypt(Properties properties);

	void decrypt(Properties properties);

}
