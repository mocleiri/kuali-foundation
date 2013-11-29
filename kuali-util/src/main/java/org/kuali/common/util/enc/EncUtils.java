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

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.DefaultEnvironmentService;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.env.PropertiesEnvironment;
import org.springframework.core.env.Environment;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;

public class EncUtils {

	private static final String UTF8 = "UTF8";
	private static final String ENCRYPTED_PREFIX = "ENC(";
	private static final String ENCRYPTED_SUFFIX = ")";

	private static final String PASSWORD_KEY = "enc.password";
	private static final String STRENGTH_KEY = "enc.strength";
	private static final String PASSWORD_REQUIRED_KEY = "enc.password.required";
	private static final String PASSWORD_REMOVE_KEY = "enc.password.removeSystemProperty";

	// Old key's
	private static final String LEGACY_PASSWORD_KEY = "properties.enc.password";
	private static final String LEGACY_STRENGTH_KEY = "properties.enc.strength";
	private static final String LEGACY_PASSWORD_REQUIRED_KEY = "properties.decrypt";

	/**
	 * If enc is present and the string is encrypted, return the decrypted string. Otherwise do nothing.
	 */
	public static Optional<String> decrypt(Optional<EncryptionService> enc, Optional<String> string) {
		if (string.isPresent()) {
			return Optional.fromNullable(decrypt(enc, string.get()));
		} else {
			return string;
		}
	}

	/**
	 * If enc is present and the string is encrypted, return the decrypted string. Otherwise do nothing.
	 */
	public static String decrypt(Optional<EncryptionService> enc, String string) {
		if (!enc.isPresent()) {
			return string;
		} else {
			return decrypt(enc.get(), string);
		}
	}

	/**
	 * Decrypt the string if it's encrypted, otherwise do nothing
	 */
	public static String decrypt(EncryptionService enc, String string) {
		Assert.noBlanks(string);
		if (isEncrypted(string)) {
			return enc.decrypt(string);
		} else {
			return string;
		}
	}

	/**
	 * Return a new list containing the same elements in the same order only with any encrypted strings having been decrypted.
	 */
	public static List<String> decrypt(EncryptionService enc, List<String> strings) {
		List<String> list = new ArrayList<String>();
		for (String string : strings) {
			if (EncUtils.isEncrypted(string)) {
				list.add(enc.decrypt(string));
			} else {
				list.add(string);
			}
		}
		return ImmutableList.copyOf(list);
	}

	public static KeyPair decrypt(EncryptionService enc, KeyPair provided) {
		String name = provided.getName();
		Optional<String> publicKey = decrypt(enc, provided.getPublicKey());
		Optional<String> privateKey = decrypt(enc, provided.getPrivateKey());
		Optional<String> fingerprint = decrypt(enc, provided.getFingerprint());
		return new KeyPair.Builder(name).publicKey(publicKey.orNull()).privateKey(privateKey.orNull()).fingerprint(fingerprint.orNull()).build();
	}

	public static Optional<String> decrypt(EncryptionService enc, Optional<String> optional) {
		if (optional.isPresent()) {
			String decrypted = decrypt(enc, optional.get());
			return Optional.of(decrypted);
		} else {
			return Optional.absent();
		}
	}

	public static KeyPair getKeyPair(String name, int size, Algorithm algorithm) {
		int type = (Algorithm.RSA == algorithm) ? com.jcraft.jsch.KeyPair.RSA : com.jcraft.jsch.KeyPair.DSA;
		JSch jsch = new JSch();
		com.jcraft.jsch.KeyPair keyPair = getKeyPair(jsch, type, size);
		String publicKey = getPublicKey(keyPair, name).trim();
		String privateKey = getPrivateKey(keyPair);
		String fingerprint = keyPair.getFingerPrint();
		return new KeyPair.Builder(name).publicKey(publicKey).privateKey(privateKey).fingerprint(fingerprint).build();
	}

	protected static com.jcraft.jsch.KeyPair getKeyPair(JSch jsch, int type, int size) {
		try {
			return com.jcraft.jsch.KeyPair.genKeyPair(jsch, type, size);
		} catch (JSchException e) {
			throw new IllegalStateException(e);
		}
	}

	protected static String getPrivateKey(com.jcraft.jsch.KeyPair keyPair) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		keyPair.writePrivateKey(out);
		return toStringUTF8(out);
	}

	protected static String getPublicKey(com.jcraft.jsch.KeyPair keyPair, String name) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		keyPair.writePublicKey(out, name);
		return toStringUTF8(out);
	}

	protected static String toStringUTF8(ByteArrayOutputStream out) {
		try {
			return out.toString(UTF8);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	public static EncryptionContext getEncryptionContext(Properties properties) {
		Environment environment = new PropertiesEnvironment(properties);
		EnvironmentService env = new DefaultEnvironmentService(environment);
		return getEncryptionContext(env);
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
		boolean removePasswordSystemProperty = env.getBoolean(PASSWORD_REMOVE_KEY, EncryptionContext.DEFAULT.isRemovePasswordSystemProperty());

		EncStrength strength = getStrength(env, EncryptionContext.DEFAULT);

		EncryptionContext context = new EncryptionContext.Builder().passwordRequired(passwordRequired).password(NullUtils.trimToNull(password.orNull())).strength(strength)
				.passwordKey(passwordKey).removePasswordSystemProperty(removePasswordSystemProperty).build();
		return context;
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

	/**
	 * Return true if the text is enclosed with <code>ENC()</code>
	 */
	public static boolean isEncrypted(String text) {
		return StringUtils.startsWith(text, ENCRYPTED_PREFIX) && StringUtils.endsWith(text, ENCRYPTED_SUFFIX);
	}

	public static String unwrap(String wrappedText) {
		Assert.noBlanks(wrappedText);
		Assert.encrypted(wrappedText);
		int start = ENCRYPTED_PREFIX.length();
		int end = wrappedText.length() - ENCRYPTED_SUFFIX.length();
		return wrappedText.substring(start, end);
	}

	public static String wrap(String unwrappedText) {
		Assert.noBlanks(unwrappedText);
		Assert.decrypted(unwrappedText);
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
