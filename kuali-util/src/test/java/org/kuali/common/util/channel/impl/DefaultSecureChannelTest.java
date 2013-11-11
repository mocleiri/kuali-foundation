package org.kuali.common.util.channel.impl;

import org.jasypt.util.text.TextEncryptor;
import org.junit.Test;
import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.enc.DefaultEncryptionService;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionService;

public class DefaultSecureChannelTest {

	@Test
	public void doIt() {
		try {
			String password = PropertyUtils.getGlobalProperty("enc.password");
			Assert.noBlanks(password);
			TextEncryptor encryptor = EncUtils.getTextEncryptor(password);
			EncryptionService enc = new DefaultEncryptionService(encryptor);
			System.out.println(enc.encrypt("foo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
