package org.kuali.common.util.encrypt;

import static org.kuali.common.util.encrypt.openssl.OpenSSLContext.buildOpenSSLContext;

import org.kuali.common.util.encrypt.openssl.OpenSSLContext;
import org.kuali.common.util.encrypt.openssl.OpenSSLEncryptor;
import org.kuali.common.util.encrypt.provider.DefaultEncryptionContextProviderChain;
import org.kuali.common.util.encrypt.provider.EncryptionContextProvider;

import com.google.common.base.Optional;

public final class Encryption {

	private static Encryptor encryptor;

	public static Encryptor buildEncryptor() {
		EncryptionContextProvider provider = new DefaultEncryptionContextProviderChain();
		Optional<EncryptionContext> optional = provider.getEncryptionContext();
		if (optional.isPresent()) {
			EncryptionContext context = optional.get();
			OpenSSLContext osc = buildOpenSSLContext(context.getStrength());
			return new OpenSSLEncryptor(osc, context.getPassword());
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
