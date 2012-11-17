package org.kuali.common.util.property;

import java.util.Properties;

import org.kuali.common.util.EncryptionStrength;

public interface PropertyEncryptor {

	void encrypt(Properties properties);

	void decrypt(Properties properties);

	void initialize(EncryptionStrength strength, String password);

}
