package org.kuali.common.util.encrypt;

import static java.lang.String.format;
import static org.kuali.common.util.encrypt.openssl.OpenSSL.buildOpenSSLEncryptor;
import static org.kuali.common.util.log.Loggers.newLogger;

import org.kuali.common.util.encrypt.provider.DefaultEncryptionContextProviderChain;
import org.kuali.common.util.encrypt.provider.EncryptionContextProvider;
import org.slf4j.Logger;

import com.google.common.base.Optional;

public final class Encryption {

	private static final Logger logger = newLogger();

	private static Encryptor encryptor;

	public static Encryptor buildEncryptor() {
		EncryptionContextProvider provider = new DefaultEncryptionContextProviderChain();
		Optional<EncryptionContext> context = provider.getEncryptionContext();
		if (context.isPresent()) {
			return buildOpenSSLEncryptor(context.get());
		} else {
			logger.info(format("encryption disabled. no encryption password was located"));
			return NoOpEncryptor.INSTANCE;
		}
	}

	public synchronized static Encryptor getDefaultEncryptor() {
		if (encryptor == null) {
			encryptor = buildEncryptor();
		}
		return encryptor;
	}

}
