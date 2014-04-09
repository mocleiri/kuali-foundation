package org.kuali.common.util.encrypt;

import org.kuali.common.util.encrypt.jasypt.DefaultJasyptEncryptor;

public final class Encryption {

	public static final String ENCRYPTION_PASSWORD_KEY = "enc.password";
	public static final String ENCRYPTION_STRENGTH_KEY = "enc.strength";

	private static Encryptor encryptor;

	public synchronized static Encryptor buildDefaultEncryptor() {
		if (encryptor == null) {
			encryptor = new DefaultJasyptEncryptor();
		}
		return encryptor;
	}
}
