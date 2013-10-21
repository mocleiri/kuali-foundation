/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.util.property.processor;

import java.util.List;
import java.util.Properties;

import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @deprecated
 */
@Deprecated
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
		logger.info("Decrypting {} property values", keys.size());
		for (String key : keys) {
			logger.debug("Decrypting [{}]", key);
			String encryptedValue = properties.getProperty(key);
			String decryptedValue = decrypt(key, encryptedValue, encryptor);
			int endIndex = key.length() - suffix.length();
			String newKey = key.substring(0, endIndex);
			PropertyUtils.addOrOverrideProperty(properties, newKey, decryptedValue, propertyOverwriteMode);
			if (removeEncryptedProperties) {
				logger.debug("Removing {}", key);
				properties.remove(key);
			}
		}
	}

	protected String decrypt(String key, String encryptedValue, TextEncryptor encryptor) {
		try {
			return encryptor.decrypt(encryptedValue);
		} catch (EncryptionOperationNotPossibleException e) {
			throw new IllegalStateException("Unexpected error decrypting [" + key + "]=[" + encryptedValue + "]");
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
