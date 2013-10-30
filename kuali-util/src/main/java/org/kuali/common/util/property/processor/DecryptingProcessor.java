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

import org.apache.commons.lang3.StringUtils;
import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.enc.EncStrength;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.nullify.NullUtils;

public final class DecryptingProcessor implements PropertyProcessor {

	public static final String DEFAULT_DECRYPT_KEY = "properties.decrypt";
	public static final String DEFAULT_PASSWORD_KEY = "properties.enc.password";
	public static final String DEFAULT_STRENGTH_KEY = "properties.enc.strength";

	private static final String GLOBAL_PASSWORD_KEY = "enc.password";
	private static final String GLOBAL_STRENGTH_KEY = "enc.strength";

	public DecryptingProcessor() {
		this(DEFAULT_DECRYPT_KEY, DEFAULT_PASSWORD_KEY, DEFAULT_STRENGTH_KEY);
	}

	public DecryptingProcessor(String passwordKey) {
		this(DEFAULT_DECRYPT_KEY, passwordKey, DEFAULT_STRENGTH_KEY);
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
		boolean decrypt = isDecrypt(properties);
		if (decrypt) {
			TextEncryptor encryptor = getTextEncryptor(properties);
			PropertyUtils.decrypt(properties, encryptor);
		}
	}

	protected boolean isDecrypt(Properties properties) {
		boolean explicitPropertiesDecryptRequest = PropertyUtils.getBoolean(decryptKey, properties, false);
		if (explicitPropertiesDecryptRequest) {
			return true;
		} else {
			return hasEncryptionPassword(properties);
		}
	}

	protected String getRequiredNoneSensitiveValue(Properties properties, String key) {
		String value = PropertyUtils.getRequiredResolvedProperty(properties, key, NullUtils.NONE);
		return NullUtils.trimToNull(value);
	}

	protected boolean hasEncryptionPassword(Properties properties) {
		return !StringUtils.isBlank(getEncryptionPassword(properties));
	}

	protected String getEncryptionPassword(Properties properties) {
		String password = getRequiredNoneSensitiveValue(properties, passwordKey);
		String globalPassword = getRequiredNoneSensitiveValue(properties, GLOBAL_PASSWORD_KEY);

		if (!StringUtils.isBlank(globalPassword)) {
			return globalPassword;
		} else {
			return password;
		}
	}

	protected EncStrength getEncryptionStrength(Properties properties) {
		String strength = getRequiredNoneSensitiveValue(properties, strengthKey);
		String globalStrength = getRequiredNoneSensitiveValue(properties, GLOBAL_STRENGTH_KEY);

		if (!StringUtils.isBlank(globalStrength)) {
			return EncStrength.valueOf(globalStrength.toUpperCase());
		} else if (!StringUtils.isBlank(strength)) {
			return EncStrength.valueOf(strength.toUpperCase());
		} else {
			return EncStrength.DEFAULT_VALUE;
		}
	}

	protected TextEncryptor getTextEncryptor(Properties properties) {
		// If they asked to decrypt, a password is required
		String password = getEncryptionPassword(properties);

		// Strength is optional (defaults to BASIC)
		EncStrength strength = getEncryptionStrength(properties);

		// Setup a TextEncryptor with the appropriate password and strength
		return EncUtils.getTextEncryptor(password, strength);
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
