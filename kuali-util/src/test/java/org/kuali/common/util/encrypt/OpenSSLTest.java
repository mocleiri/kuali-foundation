package org.kuali.common.util.encrypt;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.kuali.common.util.log.Loggers.newLogger;

import org.junit.Test;
import org.kuali.common.util.encrypt.openssl.OpenSSLEncryptor;
import org.slf4j.Logger;

public class OpenSSLTest {

	private static final Logger logger = newLogger();

	@Test
	public void testEncryption() {
		String password = "foo";
		String plaintext = "bar";
		Encryptor encryptor = new OpenSSLEncryptor(password);
		String encrypted = encryptor.encrypt(plaintext);
		String decrypted = encryptor.decrypt(encrypted);
		assertEquals(plaintext, decrypted);
		info("password=%s", password);
		info("plaintext=%s", plaintext);
		info("encrypted=%s", encrypted);
		info("decrypted=%s", decrypted);
	}

	protected static void info(String msg, Object... args) {
		if (args == null) {
			logger.info(msg);
		} else {
			logger.info(format(msg, args));
		}
	}
}
