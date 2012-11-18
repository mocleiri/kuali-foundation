package org.kuali.common.util.property.modifier;

import java.util.List;
import java.util.Properties;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.PropertyOverwriteMode;

public class EndsWithDecryptModifier extends DecryptModifier {

	public static final String DEFAULT_ENCRYPTED_SUFFIX = ".encrypted";

	String suffix = DEFAULT_ENCRYPTED_SUFFIX;
	boolean removeEncryptedProperties;
	PropertyOverwriteMode propertyOverwriteMode = PropertyOverwriteMode.INFORM;

	public EndsWithDecryptModifier() {
		this(null);
	}

	public EndsWithDecryptModifier(TextEncryptor encryptor) {
		super(encryptor);
	}

	@Override
	public void modify(Properties properties) {
		List<String> keys = PropertyUtils.getEndsWithKeys(properties, suffix);
		for (String key : keys) {
			String encryptedValue = properties.getProperty(key);
			String decryptedValue = encryptor.decrypt(encryptedValue);
			int endIndex = key.length() - suffix.length();
			String newKey = key.substring(0, endIndex);
			PropertyUtils.checkExistingProperty(properties, newKey, propertyOverwriteMode);
			properties.setProperty(newKey, decryptedValue);
			if (removeEncryptedProperties) {
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

	public PropertyOverwriteMode getPropertyOverwriteMode() {
		return propertyOverwriteMode;
	}

	public void setPropertyOverwriteMode(PropertyOverwriteMode propertyOverwriteMode) {
		this.propertyOverwriteMode = propertyOverwriteMode;
	}

}
