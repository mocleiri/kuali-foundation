package org.kuali.common.util.enc;

import org.jasypt.util.text.TextEncryptor;
import org.junit.Test;

public class EncContextTest {

	@Test
	public void test() {
		String password = "foo";
		TextEncryptor encryptor = EncUtils.getTextEncryptor(password);
		EncryptionService enc1 = new DefaultEncryptionService(encryptor);
		String encrypted = enc1.encrypt("bar.baz");
		System.setProperty("enc.password", password);
		EncContext ctx = new EncContext.Builder().build();
	}

}
