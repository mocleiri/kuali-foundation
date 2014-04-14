package org.kuali.common.util.encrypt;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.kuali.common.util.log.Loggers.newLogger;

import org.junit.Ignore;
import org.junit.Test;
import org.kuali.common.util.encrypt.openssl.OpenSSLEncryptor;
import org.slf4j.Logger;

public class OpenSSLTest {

	private static final Logger logger = newLogger();

	@Test
	public void testEncryption() {
		String password = "password";
		String plaintext = "hello world";
		Encryptor encryptor = new OpenSSLEncryptor(password);
		String encrypted = encryptor.encrypt(plaintext);
		String decrypted = encryptor.decrypt(encrypted);
		assertEquals(plaintext, decrypted);
		info("encrypted=%s", encrypted);
	}

	@Test
	@Ignore
	public void testDecryption() {
		String password = "password";
		String plaintext = "hello world";
		// echo -n "hello world" | openssl enc -a -k password -aes-128-cbc
		String encrypted = "U2FsdGVkX1+uljwXw0UbWA5bCxt6vUOZowNobg99FWY=";
		Encryptor encryptor = new OpenSSLEncryptor(password);
		String decrypted = encryptor.decrypt(encrypted);
		assertEquals(plaintext, decrypted);
	}

	protected static void info(String msg, Object... args) {
		if (args == null) {
			logger.info(msg);
		} else {
			logger.info(format(msg, args));
		}
	}
}
