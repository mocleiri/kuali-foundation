package org.kuali.common.util.property.modifier;

import java.util.List;
import java.util.Properties;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.PropertyOverwriteMode;

public class EndsWithEncryptModifier extends DecryptModifier {

	String suffix = Constants.DEFAULT_ENCRYPTED_SUFFIX;
	boolean removeUnencryptedProperties = true;
	PropertyOverwriteMode propertyOverwriteMode = PropertyOverwriteMode.INFORM;

	public EndsWithEncryptModifier() {
		this(null);
	}

	public EndsWithEncryptModifier(TextEncryptor encryptor) {
		super(encryptor);
	}

	@Override
	public void modify(Properties properties) {
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String decryptedValue = properties.getProperty(key);
			String encryptedValue = encryptor.encrypt(decryptedValue);
			String newKey = key + suffix;
			PropertyUtils.checkExistingProperty(properties, newKey, propertyOverwriteMode);
			properties.setProperty(newKey, encryptedValue);
			if (removeUnencryptedProperties) {
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

	public PropertyOverwriteMode getPropertyOverwriteMode() {
		return propertyOverwriteMode;
	}

	public void setPropertyOverwriteMode(PropertyOverwriteMode propertyOverwriteMode) {
		this.propertyOverwriteMode = propertyOverwriteMode;
	}

	public boolean isRemoveUnencryptedProperties() {
		return removeUnencryptedProperties;
	}

	public void setRemoveUnencryptedProperties(boolean removeUnencryptedProperties) {
		this.removeUnencryptedProperties = removeUnencryptedProperties;
	}

}
