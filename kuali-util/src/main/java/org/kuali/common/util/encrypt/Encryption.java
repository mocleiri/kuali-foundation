package org.kuali.common.util.encrypt;

import static org.kuali.common.util.encrypt.openssl.OpenSSL.buildOpenSSLEncryptor;

import org.kuali.common.util.encrypt.provider.DefaultEncryptionContextProviderChain;
import org.kuali.common.util.encrypt.provider.EncryptionContextProvider;

import com.google.common.base.Optional;

public final class Encryption {

	private static Encryptor encryptor;

	public static Encryptor buildEncryptor() {
		EncryptionContextProvider provider = new DefaultEncryptionContextProviderChain();
		Optional<EncryptionContext> context = provider.getEncryptionContext();
		if (context.isPresent()) {
			return buildOpenSSLEncryptor(context.get());
		} else {
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
