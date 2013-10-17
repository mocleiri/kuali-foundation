package org.kuali.common.util.enc;

import java.util.Properties;

public interface EncryptionService {

	String encrypt(String string);

	String decrypt(String string);

	void decrypt(Properties properties);

	void encrypt(Properties properties);

}
