package org.kuali.common.util.encrypt;

import static java.lang.String.format;
import static org.kuali.common.util.log.Loggers.newLogger;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.common.util.encrypt.openssl.OpenSSLEncryptor;
import org.slf4j.Logger;

public class OpenSSLTest {

	private static final Logger logger = newLogger();

	@Test
	public void testDecryption() {
		String password = "password";
		String plaintext = "hello world";
		// echo "hello world" | openssl enc -a -k password -aes-128-cbc
		String encrypted = "U2FsdGVkX18vbF5M7WNqNkLl+md21FjM+zdcQmdvKgg=";
		Encryptor encryptor = new OpenSSLEncryptor(password);
		String decrypted = encryptor.decrypt(encrypted);
		Assert.assertEquals(plaintext, decrypted);
	}

	protected static void info(String msg, Object... args) {
		if (args == null) {
			logger.info(msg);
		} else {
			logger.info(format(msg, args));
		}
	}
}
