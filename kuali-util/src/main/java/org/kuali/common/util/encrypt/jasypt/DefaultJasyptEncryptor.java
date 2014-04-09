package org.kuali.common.util.encrypt.jasypt;

import static org.kuali.common.util.enc.EncStrength.DEFAULT_ENCRYPTION_STRENGTH;
import static org.kuali.common.util.encrypt.jasypt.Jasypt.buildTextEncryptor;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.enc.EncStrength;
import org.kuali.common.util.encrypt.EncContext;
import org.kuali.common.util.encrypt.Encryptor;
import org.kuali.common.util.encrypt.jasypt.provider.DefaultJasyptContextProviderChain;

public final class DefaultJasyptEncryptor implements Encryptor {

	public DefaultJasyptEncryptor() {
		this(new DefaultJasyptContextProviderChain().getEncryptionContext().get());
	}

	public DefaultJasyptEncryptor(String password) {
		this(password, DEFAULT_ENCRYPTION_STRENGTH);
	}

	public DefaultJasyptEncryptor(String password, EncStrength strength) {
		this(new EncContext(password, strength));
	}

	public DefaultJasyptEncryptor(EncContext context) {
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
