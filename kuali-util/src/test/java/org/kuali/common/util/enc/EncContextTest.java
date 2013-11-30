package org.kuali.common.util.enc;

import org.jasypt.util.text.TextEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.common.util.spring.env.BasicEnvironmentService;
import org.kuali.common.util.spring.env.EnvironmentService;

public class EncContextTest {

	@Test
	public void test1() {
		EnvironmentService env = new BasicEnvironmentService();
		String password = "foo";
		String text = "bar.baz";
		TextEncryptor encryptor = EncUtils.getTextEncryptor(password);
		EncryptionService enc1 = new DefaultEncryptionService(encryptor);
		String encrypted1 = enc1.encrypt(text);
		System.setProperty("enc.password", password);
		EncContext ctx = new EncContext.Builder(env, "bar").removeSystemProperties(true).build();
		Assert.assertTrue(ctx.getTextEncryptor().isPresent());
		EncryptionService enc2 = new DefaultEncryptionService(encryptor);
		String encrypted2 = enc2.encrypt(text);
		String decrypted1 = enc1.decrypt(encrypted1);
		String decrypted2 = enc2.decrypt(encrypted2);
		Assert.assertEquals(text, decrypted1);
		Assert.assertEquals(text, decrypted2);
	}

	@Test
	public void test2() {
		String password = "foo";
		String text = "bar.baz";
		TextEncryptor encryptor = EncUtils.getTextEncryptor(password);
		EncryptionService enc1 = new DefaultEncryptionService(encryptor);
		String encrypted1 = enc1.encrypt(text);
		System.setProperty("properties.enc.password", password);
		EncContext ctx = new EncContext.Builder().removeSystemProperties(true).build();
		Assert.assertTrue(ctx.getTextEncryptor().isPresent());
		EncryptionService enc2 = new DefaultEncryptionService(encryptor);
		String encrypted2 = enc2.encrypt(text);
		String decrypted1 = enc1.decrypt(encrypted1);
		String decrypted2 = enc2.decrypt(encrypted2);
		Assert.assertEquals(text, decrypted1);
		Assert.assertEquals(text, decrypted2);
	}

}
