package org.kuali.common.util.encrypt;

import org.kuali.common.util.encrypt.jasypt.DefaultJasyptEncryptor;
import org.kuali.common.util.encrypt.provider.DefaultEncryptionContextProviderChain;

import com.google.common.base.Optional;

public final class Encryption {

	public static final String ENCRYPTION_PASSWORD_KEY = "enc.password";
	public static final String ENCRYPTION_STRENGTH_KEY = "enc.strength";

	private static Encryptor encryptor;

	public synchronized static Encryptor buildDefaultEncryptor() {
		if (encryptor == null) {
			Optional<EncryptionContext> context = new DefaultEncryptionContextProviderChain().getEncryptionContext();
			if (context.isPresent()) {
				encryptor = new DefaultJasyptEncryptor(context.get());
			} else {
				encryptor = NoOpEncryptor.INSTANCE;
			}
		}
		return encryptor;
	}
}
