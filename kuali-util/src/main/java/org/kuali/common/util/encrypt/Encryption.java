package org.kuali.common.util.encrypt;

import static java.lang.String.format;
import static org.kuali.common.util.encrypt.openssl.OpenSSLContext.buildOpenSSLContext;
import static org.kuali.common.util.log.Loggers.newLogger;

import org.kuali.common.util.encrypt.openssl.OpenSSLContext;
import org.kuali.common.util.encrypt.openssl.OpenSSLEncryptor;
import org.kuali.common.util.encrypt.provider.ChainProviderContext;
import org.kuali.common.util.encrypt.provider.DefaultEncryptionContextProviderChain;
import org.slf4j.Logger;

import com.google.common.base.Optional;

public final class Encryption {

	private static final String ENCRYPTION_PASSWORD_KEY = "enc.pwd";
	private static final String ENCRYPTION_STRENGTH_KEY = "enc.strength";

	private static final String ENCRYPTION_PASSWORD_REMOVE_KEY = "enc.pwd.remove";
	private static final Logger logger = newLogger();

	private static Encryptor encryptor;

	public synchronized static Encryptor buildDefaultEncryptor(boolean quiet) {
		if (encryptor == null) {
			DefaultEncryptionContextProviderChain chain = new DefaultEncryptionContextProviderChain(ENCRYPTION_PASSWORD_KEY, ENCRYPTION_STRENGTH_KEY);
			Optional<ChainProviderContext> chainContext = chain.getChainContext();
			if (chainContext.isPresent()) {
				EncryptionContext context = chainContext.get().getContext();
				String providerClassName = chainContext.get().getProvider().getClass().getSimpleName();
				info(quiet, "encryption enabled - [%s, key=%s, strength=%s]", providerClassName, ENCRYPTION_PASSWORD_KEY, context.getStrength());
				OpenSSLContext osc = buildOpenSSLContext(context.getStrength());
				encryptor = new OpenSSLEncryptor(osc, context.getPassword());
			} else {
				info(quiet, "encryption disabled - [%s] is not set", ENCRYPTION_PASSWORD_KEY);
				encryptor = NoOpEncryptor.INSTANCE;
			}
			if (Boolean.getBoolean(ENCRYPTION_PASSWORD_REMOVE_KEY) && System.getProperty(ENCRYPTION_PASSWORD_KEY) != null) {
				info(quiet, "removing system property [%s]", ENCRYPTION_PASSWORD_KEY);
				System.getProperties().remove(ENCRYPTION_PASSWORD_KEY);
			}
		}
		return encryptor;
	}

	private static void info(boolean quiet, String msg, Object... args) {
		if (quiet) {
			return;
		}
		if (args == null || args.length == 0) {
			logger.info(msg);
		} else {
			logger.info(format(msg, args));
		}
	}

	public synchronized static Encryptor buildDefaultEncryptor() {
		return buildDefaultEncryptor(false);
	}
}
