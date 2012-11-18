package org.kuali.common.util.property.modifier;

import java.util.List;
import java.util.Properties;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.PropertyUtils;
import org.springframework.util.Assert;

public class DecryptModifier implements PropertyModifier {

	TextEncryptor encryptor;

	public DecryptModifier() {
		this(null);
	}

	public DecryptModifier(TextEncryptor encryptor) {
		super();
		this.encryptor = encryptor;
	}

	@Override
	public void modify(Properties properties) {
		Assert.notNull(encryptor, "encryptor is null");
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String encryptedValue = properties.getProperty(key);
			String decryptedValue = encryptor.decrypt(encryptedValue);
			properties.setProperty(key, decryptedValue);
		}
	}

	public TextEncryptor getEncryptor() {
		return encryptor;
	}

	public void setEncryptor(TextEncryptor encryptor) {
		this.encryptor = encryptor;
	}

}
