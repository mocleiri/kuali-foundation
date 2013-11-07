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
