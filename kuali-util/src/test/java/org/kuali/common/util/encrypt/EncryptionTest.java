package org.kuali.common.util.encrypt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.kuali.common.util.encrypt.Encryption.getDefaultEncryptor;

import org.junit.Test;

public class EncryptionTest {

	@Test
	public void test() {
		Encryptor encryptor = getDefaultEncryptor();
		String plaintext = "foo";
		String encrypted = encryptor.encrypt(plaintext);
		String decrypted = encryptor.decrypt(encrypted);
		assertNotEquals(plaintext, encrypted);
		assertEquals(plaintext, decrypted);
	}

}
