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
import org.springframework.util.Assert;

/**
 * @deprecated Use ContextDecryptingProcessor instead
 */
@Deprecated
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
