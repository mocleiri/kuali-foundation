package org.kuali.common.util.property.processor;

import java.util.List;
import java.util.Properties;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndsWithDecryptProcessor extends DecryptProcessor {

	private static final Logger logger = LoggerFactory.getLogger(EndsWithDecryptProcessor.class);

	String suffix = Constants.DEFAULT_ENCRYPTED_SUFFIX;
	boolean removeEncryptedProperties = true;
	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;

	public EndsWithDecryptProcessor() {
		this(null);
	}

	public EndsWithDecryptProcessor(TextEncryptor encryptor) {
		super(encryptor);
	}

	@Override
	public void process(Properties properties) {
		List<String> keys = PropertyUtils.getEndsWithKeys(properties, suffix);
		for (String key : keys) {
			String encryptedValue = properties.getProperty(key);
			String decryptedValue = encryptor.decrypt(encryptedValue);
			int endIndex = key.length() - suffix.length();
			String newKey = key.substring(0, endIndex);
			PropertyUtils.addOrOverwriteProperty(properties, newKey, decryptedValue, propertyOverwriteMode);
			if (removeEncryptedProperties) {
				logger.debug("Removing {}", key);
				properties.remove(key);
			}
		}
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public boolean isRemoveEncryptedProperties() {
		return removeEncryptedProperties;
	}

	public void setRemoveEncryptedProperties(boolean removeEncryptedProperties) {
		this.removeEncryptedProperties = removeEncryptedProperties;
	}

	public Mode getPropertyOverwriteMode() {
		return propertyOverwriteMode;
	}

	public void setPropertyOverwriteMode(Mode propertyOverwriteMode) {
		this.propertyOverwriteMode = propertyOverwriteMode;
	}

}
