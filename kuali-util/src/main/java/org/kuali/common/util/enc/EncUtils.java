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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.Assert;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public class EncUtils {

	private static final String MAGIC_PREFIX = "ENC--"; // Handy for cli. Parenthesis tend to confuse shells
	private static final String PREFIX = "ENC(";
	private static final String SUFFIX = ")";

	public static final Optional<EncryptionService> ABSENT = Optional.absent();

	/**
	 * Return true if the text is enclosed with <code>ENC()</code> or starts with <code>ENC--</code>
	 */
	public static boolean isEncrypted(String text) {
		if (StringUtils.isBlank(text)) {
			return false;
		} else {
			return (text.startsWith(PREFIX) && text.endsWith(SUFFIX)) || text.startsWith(MAGIC_PREFIX);
		}
	}

	public static String unwrap(String wrappedText) {
		Assert.noBlanks(wrappedText);
		Assert.encrypted(wrappedText);
		if (wrappedText.startsWith(MAGIC_PREFIX)) {
			return wrappedText.substring(MAGIC_PREFIX.length());
		} else {
			int beg = PREFIX.length();
			int end = wrappedText.length() - SUFFIX.length();
			return wrappedText.substring(beg, end);
		}
	}

	public static String wrap(String unwrappedText) {
		Assert.noBlanks(unwrappedText);
		Assert.notEncrypted(unwrappedText);
		return PREFIX + unwrappedText + SUFFIX;
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

	/**
	 * If enc and string are both present and the string is encrypted, return the decrypted string. Otherwise do nothing.
	 */
	public static Optional<String> decrypt(Optional<EncryptionService> enc, Optional<String> string) {
		if (enc.isPresent() && string.isPresent()) {
			return Optional.of(enc.get().decrypt(string.get()));
		} else {
			return string;
		}
	}

	/**
	 * If enc is present and the string is encrypted, return the decrypted string. Otherwise do nothing.
	 */
	public static String decrypt(Optional<EncryptionService> enc, String string) {
		return decrypt(enc, Optional.of(string)).get();
	}

	/**
	 * If enc is present, return a new list containing the same elements in the same order only with any encrypted strings having been decrypted.
	 */
	public static List<String> decrypt(Optional<EncryptionService> enc, List<String> strings) {
		if (!enc.isPresent()) {
			return strings;
		}
		List<String> decrypted = new ArrayList<String>();
		for (String string : strings) {
			decrypted.add(enc.get().decrypt(string));
		}
		return ImmutableList.copyOf(decrypted);
	}

	/**
	 * Return a new list containing the same elements in the same order only with any encrypted strings having been decrypted.
	 */
	public static List<String> decrypt(EncryptionService enc, List<String> strings) {
		return decrypt(Optional.of(enc), strings);
	}

	public static Optional<String> decrypt(EncryptionService enc, Optional<String> optional) {
		return decrypt(Optional.of(enc), optional);
	}

}
