package org.kuali.common.util.encrypt.jasypt;

import static org.kuali.common.util.encrypt.EncryptionStrength.DEFAULT_ENCRYPTION_STRENGTH;
import static org.kuali.common.util.encrypt.jasypt.Jasypt.buildTextEncryptor;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.encrypt.EncryptionContext;
import org.kuali.common.util.encrypt.EncryptionStrength;
import org.kuali.common.util.encrypt.Encryptor;

public final class DefaultJasyptEncryptor implements Encryptor {

	public DefaultJasyptEncryptor(String password) {
		this(password, DEFAULT_ENCRYPTION_STRENGTH);
	}

	public DefaultJasyptEncryptor(String password, EncryptionStrength strength) {
		this(new EncryptionContext(password, strength));
	}

	public DefaultJasyptEncryptor(EncryptionContext context) {
		this.encryptor = buildTextEncryptor(context.getPassword(), context.getStrength());
	}

	private final TextEncryptor encryptor;

	@Override
	public String encrypt(String text) {
		return encryptor.encrypt(text);
	}

	@Override
	public String decrypt(String text) {
		return encryptor.decrypt(text);
	}

}
