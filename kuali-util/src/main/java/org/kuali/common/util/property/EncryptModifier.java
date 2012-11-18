package org.kuali.common.util.property;

import java.util.List;
import java.util.Properties;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.PropertyUtils;

public class EncryptModifier implements PropertyModifier {

	TextEncryptor encryptor;

	public EncryptModifier() {
		this(null);
	}

	public EncryptModifier(TextEncryptor encryptor) {
		super();
		this.encryptor = encryptor;
	}

	@Override
	public void modify(Properties properties) {
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String clearTextValue = properties.getProperty(key);
			String encryptedValue = encryptor.encrypt(clearTextValue);
			properties.setProperty(key, encryptedValue);
		}
	}

	public TextEncryptor getEncryptor() {
		return encryptor;
	}

	public void setEncryptor(TextEncryptor encryptor) {
		this.encryptor = encryptor;
	}

}
