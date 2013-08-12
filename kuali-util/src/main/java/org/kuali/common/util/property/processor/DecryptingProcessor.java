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

import java.util.Properties;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.enc.EncStrength;
import org.kuali.common.util.enc.EncUtils;

public class DecryptingProcessor implements PropertyProcessor {

	public static final String DEFAULT_DECRYPT_KEY = "properties.decrypt";
	public static final String DEFAULT_PASSWORD_KEY = "properties.enc.password";
	public static final String DEFAULT_STRENGTH_KEY = "properties.enc.strength";

	public DecryptingProcessor() {
		this(DEFAULT_DECRYPT_KEY, DEFAULT_PASSWORD_KEY, DEFAULT_STRENGTH_KEY);
	}

	public DecryptingProcessor(String decryptKey, String passwordKey, String strengthKey) {
		this.decryptKey = decryptKey;
		this.passwordKey = passwordKey;
		this.strengthKey = strengthKey;
	}

	private final String decryptKey;
	private final String passwordKey;
	private final String strengthKey;

	@Override
	public void process(Properties properties) {
		boolean decrypt = PropertyUtils.getBoolean(decryptKey, properties, false);
		if (decrypt) {
			// If they asked to decrypt, a password is required
			String password = PropertyUtils.getRequiredResolvedProperty(properties, passwordKey);

			// Strength is optional (defaults to BASIC)
			String defaultStrength = EncStrength.BASIC.name();
			String strengthString = PropertyUtils.getRequiredResolvedProperty(properties, strengthKey, defaultStrength);
			EncStrength strength = EncStrength.valueOf(strengthString.toUpperCase());
			TextEncryptor decryptor = EncUtils.getTextEncryptor(password, strength);
			PropertyUtils.decrypt(properties, decryptor);
		}
	}

	public String getDecryptKey() {
		return decryptKey;
	}

	public String getPasswordKey() {
		return passwordKey;
	}

	public String getStrengthKey() {
		return strengthKey;
	}

}
