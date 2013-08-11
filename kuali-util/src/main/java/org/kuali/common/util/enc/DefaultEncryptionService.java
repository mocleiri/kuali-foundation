package org.kuali.common.util.enc;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.Assert;

public class DefaultEncryptionService implements EncryptionService {

	private final TextEncryptor encryptor;

	public DefaultEncryptionService(EncContext context) {
		this(EncUtils.getTextEncryptor(context.getPassword(), context.getStrength()));
	}

	public DefaultEncryptionService(TextEncryptor encryptor) {
		Assert.noNulls(encryptor);
		this.encryptor = encryptor;
	}

	public DefaultEncryptionService(String password) {
		this(password, EncContext.DEFAULT_ENC_STRENGTH);
	}

	public DefaultEncryptionService(String password, EncStrength strength) {
		this(new EncContext(password, strength));
	}

	@Override
	public String encrypt(String string) {
		return encryptor.encrypt(string);
	}

	@Override
	public String decrypt(String string) {
		return encryptor.decrypt(string);
	}

	public TextEncryptor getEncryptor() {
		return encryptor;
	}

}
