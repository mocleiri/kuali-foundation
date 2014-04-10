package org.kuali.common.util.encrypt;

import static java.lang.String.format;
import static org.kuali.common.util.log.Loggers.newLogger;

import org.kuali.common.util.encrypt.jasypt.DefaultJasyptEncryptor;
import org.kuali.common.util.encrypt.provider.DefaultEncryptionContextProviderChain;
import org.slf4j.Logger;

import com.google.common.base.Optional;

public final class Encryption {

	public static final String ENCRYPTION_PASSWORD_KEY = "enc.pwd";
	public static final String ENCRYPTION_STRENGTH_KEY = "enc.strength";

	private static final String ENCRYPTION_PASSWORD_REMOVE_KEY = "enc.pwd.remove";
	private static final Logger logger = newLogger();

	private static Encryptor encryptor;

	public synchronized static Encryptor buildEncryptor(String encryptionPasswordKey) {
		if (encryptor == null) {
			Optional<EncryptionContext> context = new DefaultEncryptionContextProviderChain().getEncryptionContext();
			if (context.isPresent()) {
				encryptor = new DefaultJasyptEncryptor(context.get());
				logger.info(format("encryption enabled [strength=%s]", context.get().getStrength()));
			} else {
				encryptor = NoOpEncryptor.INSTANCE;
				logger.info(format("encryption disabled - [%s] is not set", ENCRYPTION_PASSWORD_KEY));
			}
			if (Boolean.getBoolean(ENCRYPTION_PASSWORD_REMOVE_KEY) && System.getProperty(ENCRYPTION_PASSWORD_KEY) != null) {
				logger.info(format("removing system property [%s]", ENCRYPTION_PASSWORD_KEY));
				System.getProperties().remove(ENCRYPTION_PASSWORD_KEY);
			}
		}
		return encryptor;
	}
	public synchronized static Encryptor buildDefaultEncryptor() {
		if (encryptor == null) {
			Optional<EncryptionContext> context = new DefaultEncryptionContextProviderChain().getEncryptionContext();
			if (context.isPresent()) {
				encryptor = new DefaultJasyptEncryptor(context.get());
				logger.info(format("encryption enabled [strength=%s]", context.get().getStrength()));
			} else {
				encryptor = NoOpEncryptor.INSTANCE;
				logger.info(format("encryption disabled - [%s] is not set", ENCRYPTION_PASSWORD_KEY));
			}
			if (Boolean.getBoolean(ENCRYPTION_PASSWORD_REMOVE_KEY) && System.getProperty(ENCRYPTION_PASSWORD_KEY) != null) {
				logger.info(format("removing system property [%s]", ENCRYPTION_PASSWORD_KEY));
				System.getProperties().remove(ENCRYPTION_PASSWORD_KEY);
			}
		}
		return encryptor;
	}
}
