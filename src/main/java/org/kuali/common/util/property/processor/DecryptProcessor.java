package org.kuali.common.util.property.processor;

import java.util.List;
import java.util.Properties;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.PropertyUtils;
import org.springframework.util.Assert;

public class DecryptProcessor implements PropertyProcessor {

	TextEncryptor encryptor;

	public DecryptProcessor() {
		this(null);
	}

	public DecryptProcessor(TextEncryptor encryptor) {
		super();
		this.encryptor = encryptor;
	}

	@Override
	public void process(Properties properties) {
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
