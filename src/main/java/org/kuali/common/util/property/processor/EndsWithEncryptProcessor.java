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
public class EndsWithEncryptProcessor extends DecryptProcessor {

	private static final Logger logger = LoggerFactory.getLogger(EndsWithEncryptProcessor.class);

	String suffix = Constants.DEFAULT_ENCRYPTED_SUFFIX;
	boolean removeUnencryptedProperties = true;
	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;

	public EndsWithEncryptProcessor() {
		this(null);
	}

	public EndsWithEncryptProcessor(TextEncryptor encryptor) {
		super(encryptor);
	}

	@Override
	public void process(Properties properties) {
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String decryptedValue = properties.getProperty(key);
			String encryptedValue = encryptor.encrypt(decryptedValue);
			String newKey = key + suffix;
			PropertyUtils.addOrOverrideProperty(properties, newKey, encryptedValue, propertyOverwriteMode);
			if (removeUnencryptedProperties) {
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
