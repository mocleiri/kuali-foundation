package org.kuali.common.util.enc;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.Assert;

public final class DefaultEncryptionService implements EncryptionService {

	private final EncContext context;
	private final TextEncryptor encryptor;

	public DefaultEncryptionService(EncContext context) {
		Assert.noNulls(context);
		this.context = context;
		this.encryptor = EncUtils.getTextEncryptor(context.getPassword(), context.getStrength());
	}

	@Override
	public String encrypt(String string) {
		if (context.isEnabled()) {
			return encryptor.encrypt(string);
		} else {
			return string;
		}
	}

	@Override
	public String decrypt(String string) {
		if (context.isEnabled()) {
			return encryptor.decrypt(string);
		} else {
			return string;
		}
	}

}
