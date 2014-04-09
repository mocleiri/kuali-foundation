package org.kuali.common.util.encrypt;

import org.junit.Ignore;
import org.junit.Test;

public class EncryptionTest {

	@Test
	@Ignore
	public void test() {
		try {
			Encryptor encryptor = Encryption.buildDefaultEncryptor();
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
