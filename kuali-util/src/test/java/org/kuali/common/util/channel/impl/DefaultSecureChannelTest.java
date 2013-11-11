package org.kuali.common.util.channel.impl;

import org.jasypt.util.text.TextEncryptor;
import org.junit.Test;
import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.channel.model.CommandContext;
import org.kuali.common.util.channel.model.CommandResult;
import org.kuali.common.util.enc.DefaultEncryptionService;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionService;

public class DefaultSecureChannelTest {

	private static final String PK = "ENC(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)";

	@Test
	public void doIt() {
		try {
			String privateKey = getPrivateKey();
			ChannelContext context = new ChannelContext.Builder("ec2-54-242-254-25.compute-1.amazonaws.com").username("root").privateKey(privateKey).build();
			StreamingSecureChannel channel = new StreamingSecureChannel(context);
			CommandContext cc = new CommandContext.Builder("man ls").stdin("q").build();
			CommandResult result = channel.exec(cc);
			if (result.getStdout().isPresent()) {
				System.out.println(result.getStdout().get());
			}
			if (result.getStderr().isPresent()) {
				System.out.println(result.getStderr().get());
			}
			channel.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String getPrivateKey() {
		String password = PropertyUtils.getGlobalProperty("enc.password");
		Assert.noBlanks(password);
		TextEncryptor encryptor = EncUtils.getTextEncryptor(password);
		EncryptionService enc = new DefaultEncryptionService(encryptor);
		return enc.decrypt(PK);
	}

}
