package org.kuali.common.util.property.processor;

import java.util.List;
import java.util.Properties;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.PropertyUtils;

public class EncryptProcessor implements PropertyProcessor {

	TextEncryptor encryptor;

	public EncryptProcessor() {
		this(null);
	}

	public EncryptProcessor(TextEncryptor encryptor) {
		super();
		this.encryptor = encryptor;
	}

	@Override
	public void process(Properties properties) {
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
