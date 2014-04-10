package org.kuali.common.util.encrypt;

import static java.lang.String.format;
import static org.kuali.common.util.log.Loggers.newLogger;

import org.kuali.common.util.encrypt.jasypt.DefaultJasyptEncryptor;
import org.kuali.common.util.encrypt.provider.DefaultEncryptionContextProviderChain;
import org.slf4j.Logger;

import com.google.common.base.Optional;

public final class Encryption {

	private static final String ENCRYPTION_PASSWORD_KEY = "enc.pwd";
	private static final String ENCRYPTION_STRENGTH_KEY = "enc.strength";

	private static final String ENCRYPTION_PASSWORD_REMOVE_KEY = "enc.pwd.remove";
	private static final Logger logger = newLogger();

	private static Encryptor encryptor;

	public synchronized static Encryptor buildDefaultEncryptor() {
		if (encryptor == null) {
			DefaultEncryptionContextProviderChain provider = new DefaultEncryptionContextProviderChain(ENCRYPTION_PASSWORD_KEY, ENCRYPTION_STRENGTH_KEY);
			Optional<EncryptionContext> context = provider.getEncryptionContext();
			if (context.isPresent()) {
				String providerClassName = provider.getProvider().get().getClass().getSimpleName();
				logger.info(format("encryption enabled - [class=%s, key=%s, strength=%s]", providerClassName, ENCRYPTION_PASSWORD_KEY, context.get().getStrength()));
				encryptor = new DefaultJasyptEncryptor(context.get());
			} else {
				logger.info(format("encryption disabled - [%s] is not set", ENCRYPTION_PASSWORD_KEY));
				encryptor = NoOpEncryptor.INSTANCE;
			}
			if (Boolean.getBoolean(ENCRYPTION_PASSWORD_REMOVE_KEY) && System.getProperty(ENCRYPTION_PASSWORD_KEY) != null) {
				logger.info(format("removing system property [%s]", ENCRYPTION_PASSWORD_KEY));
				System.getProperties().remove(ENCRYPTION_PASSWORD_KEY);
			}
		}
		return encryptor;
	}
}
