package org.kuali.common.util.encrypt;

import org.junit.Test;

public class EncryptionTest {

	@Test
	public void test() {
		try {
			Encryptor encryptor = Encryption.getDefaultEncryptor();
			String text = "foo";
			String encrypted = encryptor.encrypt(text);
			String decrypted = encryptor.decrypt(encrypted);
			System.out.println(text);
			System.out.println(encrypted);
			System.out.println(decrypted);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
