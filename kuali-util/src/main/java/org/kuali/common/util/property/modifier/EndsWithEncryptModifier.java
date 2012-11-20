package org.kuali.common.util.property.modifier;

import java.util.List;
import java.util.Properties;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;

public class EndsWithEncryptModifier extends DecryptModifier {

	String suffix = Constants.DEFAULT_ENCRYPTED_SUFFIX;
	boolean removeUnencryptedProperties = true;
	Mode propertyOverwriteMode = Mode.INFORM;

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
			PropertyUtils.setProperty(properties, newKey, encryptedValue, propertyOverwriteMode);
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

	public boolean isRemoveUnencryptedProperties() {
		return removeUnencryptedProperties;
	}

	public void setRemoveUnencryptedProperties(boolean removeUnencryptedProperties) {
		this.removeUnencryptedProperties = removeUnencryptedProperties;
	}

	public Mode getPropertyOverwriteMode() {
		return propertyOverwriteMode;
	}

	public void setPropertyOverwriteMode(Mode propertyOverwriteMode) {
		this.propertyOverwriteMode = propertyOverwriteMode;
	}

}
