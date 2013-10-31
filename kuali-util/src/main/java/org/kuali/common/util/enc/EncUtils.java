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
package org.kuali.common.util.enc;

import java.util.Properties;

import org.codehaus.plexus.util.StringUtils;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;

import com.google.common.base.Optional;

public class EncUtils {

	private static final String ENCRYPTED_PREFIX = "ENC(";
	private static final String ENCRYPTED_SUFFIX = ")";

	private static final String PASSWORD_KEY = "enc.password";
	private static final String STRENGTH_KEY = "enc.strength";
	private static final String PASSWORD_REQUIRED_KEY = "enc.password.required";

	// Old key's
	private static final String LEGACY_PASSWORD_KEY = "properties.enc.password";
	private static final String LEGACY_STRENGTH_KEY = "properties.enc.strength";
	private static final String LEGACY_PASSWORD_REQUIRED_KEY = "properties.decrypt";

	public static EncryptionContext getEncryptionContext(Properties properties) {
		Optional<String> password = PropertyUtils.getString(properties, PASSWORD_KEY, EncryptionContext.DEFAULT.getPassword());
		Optional<String> legacyPassword = PropertyUtils.getString(properties, LEGACY_PASSWORD_KEY, EncryptionContext.DEFAULT.getPassword());
		String passwordKey = PASSWORD_KEY;

		// Only use the legacy property if "enc.password" is not set
		if (properties.getProperty(PASSWORD_KEY) == null && properties.getProperty(LEGACY_PASSWORD_KEY) != null) {
			password = legacyPassword;
			passwordKey = LEGACY_PASSWORD_KEY;
		}

		boolean passwordRequired = isPasswordRequired(properties, EncryptionContext.DEFAULT);

		EncStrength strength = getStrength(properties, EncryptionContext.DEFAULT);

		return new EncryptionContext.Builder().passwordRequired(passwordRequired).password(NullUtils.trimToNull(password.orNull())).strength(strength).passwordKey(passwordKey)
				.build();
	}

	public static EncryptionContext getEncryptionContext(EnvironmentService env) {
		Optional<String> password = SpringUtils.getString(env, PASSWORD_KEY, EncryptionContext.DEFAULT.getPassword());
		Optional<String> legacyPassword = SpringUtils.getString(env, LEGACY_PASSWORD_KEY, EncryptionContext.DEFAULT.getPassword());
		String passwordKey = PASSWORD_KEY;

		// Always use the new property if it exists, but support using the old property as well
		if (!env.containsProperty(PASSWORD_KEY) && env.containsProperty(LEGACY_PASSWORD_KEY)) {
			password = legacyPassword;
			passwordKey = LEGACY_PASSWORD_KEY;
		}

		boolean passwordRequired = isPasswordRequired(env, EncryptionContext.DEFAULT);

		EncStrength strength = getStrength(env, EncryptionContext.DEFAULT);

		return new EncryptionContext.Builder().passwordRequired(passwordRequired).password(password.orNull()).strength(strength).passwordKey(passwordKey).build();
	}

	protected static boolean isPasswordRequired(Properties properties, EncryptionContext provided) {
		boolean required = PropertyUtils.getBoolean(PASSWORD_REQUIRED_KEY, properties, provided.isPasswordRequired());
		boolean legacyRequired = PropertyUtils.getBoolean(LEGACY_PASSWORD_REQUIRED_KEY, properties, provided.isPasswordRequired());
		return required || legacyRequired;
	}

	protected static boolean isPasswordRequired(EnvironmentService env, EncryptionContext provided) {
		boolean required = env.getBoolean(PASSWORD_REQUIRED_KEY, provided.isPasswordRequired());
		boolean legacyRequired = env.getBoolean(LEGACY_PASSWORD_REQUIRED_KEY, provided.isPasswordRequired());
		return required || legacyRequired;
	}

	protected static EncStrength getStrength(Properties properties, EncryptionContext provided) {
		String strength = properties.getProperty(STRENGTH_KEY, provided.getStrength().name());
		String legacyStrength = properties.getProperty(LEGACY_STRENGTH_KEY, provided.getStrength().name());

		// Only use the legacy property if "enc.strength" is not set
		if (properties.getProperty(STRENGTH_KEY) == null && properties.getProperty(LEGACY_STRENGTH_KEY) != null) {
			strength = legacyStrength;
		}

		// Convert the string value to an enum
		return EncStrength.valueOf(strength.toUpperCase());
	}

	protected static EncStrength getStrength(EnvironmentService env, EncryptionContext provided) {
		String strength = env.getString(STRENGTH_KEY, provided.getStrength().name());
		String legacyStrength = env.getString(LEGACY_STRENGTH_KEY, provided.getStrength().name());

		// Always use the new property if it exists, but support using the old property as well
		if (!env.containsProperty(STRENGTH_KEY) && env.containsProperty(LEGACY_STRENGTH_KEY)) {
			strength = legacyStrength;
		}

		// Convert the string value to an enum
		return EncStrength.valueOf(strength.toUpperCase());
	}

	public static boolean isEncrypted(String text) {
		Assert.noBlanks(text);
		return text.startsWith(ENCRYPTED_PREFIX) && text.endsWith(ENCRYPTED_SUFFIX);
	}

	public static String unwrap(String wrappedText) {
		Assert.noBlanks(wrappedText);
		Assert.isTrue(isEncrypted(wrappedText), "Text is not wrapped");
		return StringUtils.substring(wrappedText, ENCRYPTED_PREFIX.length(), ENCRYPTED_SUFFIX.length());
	}

	public static String wrap(String unwrappedText) {
		Assert.noBlanks(unwrappedText);
		Assert.isFalse(isEncrypted(unwrappedText), "Text is already wrapped");
		return ENCRYPTED_PREFIX + unwrappedText + ENCRYPTED_SUFFIX;
	}

	/**
	 * Returns a <code>BasicTextEncryptor</code> that uses <code>password</code> to encrypt/decrypt.
	 */
	public static TextEncryptor getTextEncryptor(String password) {
		return getTextEncryptor(password, EncStrength.DEFAULT_VALUE);
	}

	/**
	 * Return a <code>BasicTextEncryptor</code> or <code>StrongTextEncryptor</code> depending on what <code>strength</code> is set to
	 */
	public static TextEncryptor getTextEncryptor(String password, EncStrength strength) {
		Assert.noBlanks(password);
		switch (strength) {
		case BASIC:
			BasicTextEncryptor basic = new BasicTextEncryptor();
			basic.setPassword(password);
			return basic;
		case STRONG:
			StrongTextEncryptor strong = new StrongTextEncryptor();
			strong.setPassword(password);
			return strong;
		default:
			throw new IllegalArgumentException("Encryption strength [" + strength + "] is unknown");
		}
	}
}
