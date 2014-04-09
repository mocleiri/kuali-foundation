package org.kuali.common.util.encrypt;

import org.junit.Test;

public class EncryptionTest {

	@Test
	public void test() {
		try {
			Encryptor encryptor = Encryption.buildDefaultEncryptor();
			String plaintext = "foo";
			String encrypted = encryptor.encrypt(plaintext);
			String decrypted = encryptor.decrypt(encrypted);
			System.out.println(plaintext);
			System.out.println(encrypted);
			System.out.println(decrypted);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
